package com.example;

import com.example.model.Movie;
import com.example.model.Periodicals;
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
public class PeriodicalsTestSuite {

    @Autowired
    MockMvc mockMvc;

    ObjectMapper mapper = new ObjectMapper();


    @Test
    public void testingFindAll() throws Exception{
        ResultActions resultActions = this.mockMvc.perform(MockMvcRequestBuilders.get("/periodicals").
                contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk());

        MvcResult result = resultActions.andReturn();
        String contentAsString = result.getResponse().getContentAsString();
        Periodicals[] periodicals = mapper.readValue(contentAsString, Periodicals[].class);

        assertAll("Testing the get all functionality", () -> assertEquals("me", periodicals[0].getAuthor()), () -> assertEquals("cooking", periodicals[1].getGenre()), () -> assertEquals(3, periodicals.length));


    }

    @Test
    public void testFindById() throws Exception {
        ResultActions resultActions = this.mockMvc.perform(MockMvcRequestBuilders.get("/periodicals/1").
                contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk());

        MvcResult result = resultActions.andReturn();
        String contentAsString = result.getResponse().getContentAsString();
        Periodicals periodicals = mapper.readValue(contentAsString, Periodicals.class);

        assertEquals("me", periodicals.getAuthor());

    }

    @Test
    public void testDslTitle() throws Exception {
        ResultActions resultActions = this.mockMvc.perform(MockMvcRequestBuilders.get("/periodicals?titleFilter=pizza").
                contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk());

        MvcResult result = resultActions.andReturn();
        String contentAsString = result.getResponse().getContentAsString();
        Periodicals[] periodicals = mapper.readValue(contentAsString, Periodicals[].class);

        assertEquals(1, periodicals.length);

    }

    @Test
    public void testDslAuthor() throws Exception {
        ResultActions resultActions = this.mockMvc.perform(MockMvcRequestBuilders.get("/periodicals?authorFilter=e").
                contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk());

        MvcResult result = resultActions.andReturn();
        String contentAsString = result.getResponse().getContentAsString();
        Periodicals[] periodicals = mapper.readValue(contentAsString, Periodicals[].class);

        assertEquals(3, periodicals.length);

    }

    @Test
    public void testDslGenre() throws Exception {
        ResultActions resultActions = this.mockMvc.perform(MockMvcRequestBuilders.get("/periodicals?genreFilter=away").
                contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk());

        MvcResult result = resultActions.andReturn();
        String contentAsString = result.getResponse().getContentAsString();
        Periodicals[] periodicals = mapper.readValue(contentAsString,Periodicals[].class);

        assertEquals(1, periodicals.length);

    }

    @Test
    @Rollback
    public void testCreatingABook() throws Exception {
        Periodicals periodicals = new Periodicals();
        periodicals.setDates("yup");
        periodicals.setAuthor("Egi");
        periodicals.setGenre("Horror");
        periodicals.setTitle("Spook");



        ResultActions resultActions = this.mockMvc.perform(MockMvcRequestBuilders.post("/periodicals").content(mapper.writeValueAsString(periodicals)).
                contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk());

        MvcResult result = resultActions.andReturn();
        String contentAsString = result.getResponse().getContentAsString();

        periodicals = mapper.readValue(contentAsString, Periodicals.class);

        assertEquals("Horror", periodicals.getGenre());
    }

    @Test
    @Rollback
    public void testUpdatingABook() throws Exception {
        Periodicals periodicals = new Periodicals();
        periodicals.setId(1L);
        periodicals.setAuthor("me");
        periodicals.setGenre("cooking");
        periodicals.setTitle("onion");
        periodicals.setDates("Monday");


        ResultActions resultActions = this.mockMvc.perform(MockMvcRequestBuilders.put("/periodicals").content(mapper.writeValueAsString(periodicals)).
                contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk());

        MvcResult result = resultActions.andReturn();
        String contentAsString = result.getResponse().getContentAsString();

        periodicals = mapper.readValue(contentAsString, Periodicals.class);

        assertEquals("Monday", periodicals.getDates());
    }

    @Test
    public void testDeletingBook() throws Exception {

        ResultActions resultActions = this.mockMvc.perform(MockMvcRequestBuilders.delete("/delete/periodicals/{id}", 2).contentType(MediaType.APPLICATION_JSON)

                .accept(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk());

        MvcResult result = resultActions.andReturn();
        String contentAsString = result.getResponse().getContentAsString();

        assertEquals("record deleted!", contentAsString);

    }
}
