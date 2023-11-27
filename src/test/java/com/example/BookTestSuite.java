package com.example;

import com.example.model.Book;
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
public class BookTestSuite {

    @Autowired
    MockMvc mockMvc;

    ObjectMapper mapper = new ObjectMapper();


    @Test
    public void testFindAll() throws Exception {
        ResultActions resultActions = this.mockMvc.perform(MockMvcRequestBuilders.get("/books").
                contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk());

        MvcResult result = resultActions.andReturn();
        String contentAsString = result.getResponse().getContentAsString();
        Book[] book = mapper.readValue(contentAsString, Book[].class);

        assertAll("Testing the get all functionality", () -> assertEquals("John", book[0].getAuthor()), () -> assertEquals("Buddhism", book[3].getTitle()), () -> assertEquals(5, book.length));

    }

    @Test
    public void testFindById() throws Exception {
        ResultActions resultActions = this.mockMvc.perform(MockMvcRequestBuilders.get("/books/1").
                contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk());

        MvcResult result = resultActions.andReturn();
        String contentAsString = result.getResponse().getContentAsString();
        Book book = mapper.readValue(contentAsString, Book.class);

        assertEquals("John", book.getAuthor());

    }

    @Test
    public void testDslTitle() throws Exception {
        ResultActions resultActions = this.mockMvc.perform(MockMvcRequestBuilders.get("/books?titleFilter=KouQ").
                contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk());

        MvcResult result = resultActions.andReturn();
        String contentAsString = result.getResponse().getContentAsString();
        Book[] book = mapper.readValue(contentAsString, Book[].class);

        assertEquals(2, book.length);

    }

    @Test
    public void testDslAuthor() throws Exception {
        ResultActions resultActions = this.mockMvc.perform(MockMvcRequestBuilders.get("/books?authorFilter=Masak").
                contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk());

        MvcResult result = resultActions.andReturn();
        String contentAsString = result.getResponse().getContentAsString();
        Book[] book = mapper.readValue(contentAsString, Book[].class);

        assertEquals(1, book.length);

    }

    @Test
    public void testDslGenre() throws Exception {
        ResultActions resultActions = this.mockMvc.perform(MockMvcRequestBuilders.get("/books?genreFilter=Spor").
                contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk());

        MvcResult result = resultActions.andReturn();
        String contentAsString = result.getResponse().getContentAsString();
        Book[] book = mapper.readValue(contentAsString, Book[].class);

        assertEquals(1, book.length);

    }

    @Test
    @Rollback
    public void testCreatingABook() throws Exception {
        Book book = new Book();
        book.setAuthor("Egi");
        book.setGenre("Horror");
        book.setTitle("Spook");


        Publisher publisher = new Publisher();
        publisher.setBrand("Anime");
        publisher.setPrestige(100L);
        book.setReleaser(publisher);


        ResultActions resultActions = this.mockMvc.perform(MockMvcRequestBuilders.post("/books").content(mapper.writeValueAsString(book)).
                contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk());

        MvcResult result = resultActions.andReturn();
        String contentAsString = result.getResponse().getContentAsString();

        book = mapper.readValue(contentAsString, Book.class);

        assertEquals("Horror", book.getGenre());
    }

    @Test
    @Rollback
    public void testUpdatingABook() throws Exception {
        Book book = new Book();
        book.setId(1L);
        book.setAuthor("John");
        book.setGenre("Epic");
        book.setTitle("KouQ");


        Publisher publisher = new Publisher();
        publisher.setBrand("MashuBooks");
        publisher.setPrestige(10L);
        book.setReleaser(publisher);


        ResultActions resultActions = this.mockMvc.perform(MockMvcRequestBuilders.put("/books").content(mapper.writeValueAsString(book)).
                contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk());

        MvcResult result = resultActions.andReturn();
        String contentAsString = result.getResponse().getContentAsString();

        book = mapper.readValue(contentAsString, Book.class);

        assertEquals("Epic", book.getGenre());
    }

    @Test
    public void testDeletingBook() throws Exception {

        int expectedLength = 2;


        ResultActions resultActions = this.mockMvc.perform(MockMvcRequestBuilders.delete("/delete/books/{id}", 102).contentType(MediaType.APPLICATION_JSON)

                .accept(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk());

        MvcResult result = resultActions.andReturn();
        String contentAsString = result.getResponse().getContentAsString();

        assertEquals("record deleted!", contentAsString);

    }

}
