package com.example;

import com.example.model.Periodicals;
import com.example.model.Publisher;
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
public class PublisherTestSuite {

    @Autowired
    MockMvc mockMvc;

    ObjectMapper mapper = new ObjectMapper();


    @Test
    public void testingFindAll() throws Exception{
        ResultActions resultActions = this.mockMvc.perform(MockMvcRequestBuilders.get("/publishers").
                contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk());

        MvcResult result = resultActions.andReturn();
        String contentAsString = result.getResponse().getContentAsString();
        Publisher[] publishers = mapper.readValue(contentAsString,Publisher[].class);

        assertAll("Testing the get all functionality", () -> assertEquals("MashuBooks", publishers[0].getBrand()), () -> assertEquals(10, publishers[0].getPrestige()), () -> assertEquals(2, publishers.length));


    }

    @Test
    public void testFindById() throws Exception {
        ResultActions resultActions = this.mockMvc.perform(MockMvcRequestBuilders.get("/publishers/33").
                contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk());

        MvcResult result = resultActions.andReturn();
        String contentAsString = result.getResponse().getContentAsString();
        Publisher publisher = mapper.readValue(contentAsString, Publisher.class);

        assertEquals("PurcellPaper", publisher.getBrand());

    }

    @Test
    @Rollback
    public void testCreatingABook() throws Exception {
        Publisher publishers = new Publisher();
        publishers.setBrand("yup");
        publishers.setPrestige(1L);




        ResultActions resultActions = this.mockMvc.perform(MockMvcRequestBuilders.post("/publishers").content(mapper.writeValueAsString(publishers)).
                contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk());

        MvcResult result = resultActions.andReturn();
        String contentAsString = result.getResponse().getContentAsString();

        publishers = mapper.readValue(contentAsString, Publisher.class);

        assertEquals(1, publishers.getPrestige());
    }

    @Test
    @Rollback
    public void testUpdatingABook() throws Exception {
        Publisher publishers = new Publisher();
        publishers.setBrand("PurcellPaper");
        publishers.setPrestige(1L);
        publishers.setId(33L);


        ResultActions resultActions = this.mockMvc.perform(MockMvcRequestBuilders.put("/publishers").content(mapper.writeValueAsString(publishers)).
                contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk());

        MvcResult result = resultActions.andReturn();
        String contentAsString = result.getResponse().getContentAsString();

        publishers = mapper.readValue(contentAsString, Publisher.class);

        assertEquals(1, publishers.getPrestige());
    }

    @Test
    public void testDeletingBook() throws Exception {

        ResultActions resultActions = this.mockMvc.perform(MockMvcRequestBuilders.delete("/delete/publishers/{id}", 33).contentType(MediaType.APPLICATION_JSON)

                .accept(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk());

        MvcResult result = resultActions.andReturn();
        String contentAsString = result.getResponse().getContentAsString();

        assertEquals("record deleted!", contentAsString);

    }
}
