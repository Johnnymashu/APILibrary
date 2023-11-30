package com.example;

import com.example.model.DebitDetails;
import com.example.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@TestPropertySource(properties = {"spring.sql.init.mode=never"})
@Transactional
 class UserTests {
    @Autowired
    MockMvc mockMvc;

    ObjectMapper mapper = new ObjectMapper();


    @Test
     void testingFindAll() throws Exception{
        ResultActions resultActions = this.mockMvc.perform(MockMvcRequestBuilders.get("/users").
                contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk());

        MvcResult result = resultActions.andReturn();
        String contentAsString = result.getResponse().getContentAsString();
        User[] users = mapper.readValue(contentAsString, User[].class);

        assertAll("Testing the get all functionality", () -> assertEquals("JackWells@gmail.com", users[0].getEmail()));


    }

    @Test
     void testFindById() throws Exception {
        ResultActions resultActions = this.mockMvc.perform(MockMvcRequestBuilders.get("/users/6").
                contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk());

        MvcResult result = resultActions.andReturn();
        String contentAsString = result.getResponse().getContentAsString();
        User users = mapper.readValue(contentAsString, User.class);

        assertEquals("Johnnymashu@gmail.com", users.getEmail());

    }

    @Test
    @Rollback
     void testCreatingAUser() throws Exception {
        User users = new User();
        users.setEmail("123@gmail.com");

        DebitDetails debitDetails = new DebitDetails();
        debitDetails.setAccountNumber(123L);
        debitDetails.setCardHolder("123");
        debitDetails.setSortCode(3456L);
        users.setDebitDetails(debitDetails);




        ResultActions resultActions = this.mockMvc.perform(MockMvcRequestBuilders.post("/users").content(mapper.writeValueAsString(users)).
                contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk());

        MvcResult result = resultActions.andReturn();
        String contentAsString = result.getResponse().getContentAsString();

        users = mapper.readValue(contentAsString, User.class);

        assertEquals("123", users.getDebitDetails().getCardHolder()
        );
    }

    @Test
    @Rollback
     void testUpdatingAUser() throws Exception {
       User users = new User();
       users.setId(6L);
       users.setEmail("faker@gmail.com");


        ResultActions resultActions = this.mockMvc.perform(MockMvcRequestBuilders.put("/users").content(mapper.writeValueAsString(users)).
                contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk());

        MvcResult result = resultActions.andReturn();
        String contentAsString = result.getResponse().getContentAsString();

        users = mapper.readValue(contentAsString, User.class);

        assertEquals("faker@gmail.com", users.getEmail());
    }

    @Test
     void testDeletingUser() throws Exception {

        ResultActions resultActions = this.mockMvc.perform(MockMvcRequestBuilders.delete("/delete/users/{id}", 33).contentType(MediaType.APPLICATION_JSON)

                .accept(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk());

        MvcResult result = resultActions.andReturn();
        String contentAsString = result.getResponse().getContentAsString();

        assertEquals("record deleted!", contentAsString);

    }
}
