package com.example;

import com.example.model.Book;
import com.example.model.Publisher;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Sql("classpath:data.sql")
@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@TestPropertySource(properties = {"spring.sql.init.mode=never"})
public class BookTestswithMockHTTPRequest {

    @Autowired
    MockMvc mockMvc;

    ObjectMapper mapper = new ObjectMapper();

    @Test
    public void testCreatingABook() throws Exception {
        Book book = new Book();
        book.setAuthor("Yay");
        book.setGenre("Horror");
        book.setTitle("Spook");


        Publisher publisher = new Publisher();
        publisher.setBrand("Anime");
        publisher.setPrestige(100);
        book.setReleaser(publisher);


        ResultActions resultActions = this.mockMvc.perform(MockMvcRequestBuilders.post("/books").content(mapper.writeValueAsString(book)).
                contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk());

        MvcResult result = resultActions.andReturn();
        String contentAsString = result.getResponse().getContentAsString();

        book = mapper.readValue(contentAsString, Book.class);

        assertEquals(1, book.getId());
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
