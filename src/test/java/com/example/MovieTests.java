package com.example;

import com.example.model.Movie;
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
 class MovieTests {



    @Autowired
    MockMvc mockMvc;

    ObjectMapper mapper = new ObjectMapper();


    @Test
     void testingFindAll() throws Exception{
        ResultActions resultActions = this.mockMvc.perform(MockMvcRequestBuilders.get("/movies").
                contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk());

        MvcResult result = resultActions.andReturn();
        String contentAsString = result.getResponse().getContentAsString();
        Movie[] movie = mapper.readValue(contentAsString, Movie[].class);

        assertAll("Testing the get all functionality", () -> assertEquals("Rice", movie[0].getAuthor()), () -> assertEquals("Shouyu", movie[1].getGenre()), () -> assertEquals(2, movie.length));


    }

    @Test
     void testFindById() throws Exception {
        ResultActions resultActions = this.mockMvc.perform(MockMvcRequestBuilders.get("/movies/1").
                contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk());

        MvcResult result = resultActions.andReturn();
        String contentAsString = result.getResponse().getContentAsString();
        Movie movie = mapper.readValue(contentAsString, Movie.class);

        assertEquals("Rice", movie.getAuthor());

    }

    @Test
     void testDslTitle() throws Exception {
        ResultActions resultActions = this.mockMvc.perform(MockMvcRequestBuilders.get("/movies?titleFilter=Mir").
                contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk());

        MvcResult result = resultActions.andReturn();
        String contentAsString = result.getResponse().getContentAsString();
        Movie[] movie = mapper.readValue(contentAsString, Movie[].class);

        assertEquals(1, movie.length);

    }

    @Test
     void testDslAuthor() throws Exception {
        ResultActions resultActions = this.mockMvc.perform(MockMvcRequestBuilders.get("/movies?authorFilter=u").
                contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk());

        MvcResult result = resultActions.andReturn();
        String contentAsString = result.getResponse().getContentAsString();
        Movie[] movie = mapper.readValue(contentAsString, Movie[].class);

        assertEquals(0, movie.length);

    }

    @Test
     void testDslGenre() throws Exception {
        ResultActions resultActions = this.mockMvc.perform(MockMvcRequestBuilders.get("/movies?genreFilter=S").
                contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk());

        MvcResult result = resultActions.andReturn();
        String contentAsString = result.getResponse().getContentAsString();
        Movie[] movie = mapper.readValue(contentAsString, Movie[].class);

        assertEquals(2, movie.length);

    }

    @Test
    @Rollback
     void testCreatingAMovie() throws Exception {
        Movie movie = new Movie();
        movie.setAuthor("Egi");
        movie.setGenre("Horror");
        movie.setTitle("Spook");
        movie.setActors("TFBF");
        movie.setDirector("President");


        ResultActions resultActions = this.mockMvc.perform(MockMvcRequestBuilders.post("/movies").content(mapper.writeValueAsString(movie)).
                contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk());

        MvcResult result = resultActions.andReturn();
        String contentAsString = result.getResponse().getContentAsString();

        movie = mapper.readValue(contentAsString, Movie.class);

        assertEquals("Horror", movie.getGenre());
    }

    @Test
    @Rollback
     void testUpdatingAMovie() throws Exception {
        Movie movie = new Movie();
        movie.setId(1L);
        movie.setAuthor("Rice");
        movie.setGenre("Seaweed");
        movie.setTitle("Spam");
        movie.setDirector("President");
        movie.setActors("TFBF");


        ResultActions resultActions = this.mockMvc.perform(MockMvcRequestBuilders.put("/movies").content(mapper.writeValueAsString(movie)).
                contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk());

        MvcResult result = resultActions.andReturn();
        String contentAsString = result.getResponse().getContentAsString();

        movie = mapper.readValue(contentAsString, Movie.class);

        assertEquals("Spam", movie.getTitle());
    }

    @Test
     void testDeletingMovie() throws Exception {

        ResultActions resultActions = this.mockMvc.perform(MockMvcRequestBuilders.delete("/delete/movies/{id}", 2).contentType(MediaType.APPLICATION_JSON)

                .accept(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk());

        MvcResult result = resultActions.andReturn();
        String contentAsString = result.getResponse().getContentAsString();

        assertEquals("record deleted!", contentAsString);

    }
}
