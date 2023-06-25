package com.tdd.spring.controllers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tdd.spring.entities.Book;
import com.tdd.spring.services.BookService;

@ActiveProfiles("test")
@WebMvcTest
@AutoConfigureMockMvc
public class BookControllerTest {
	
	@Autowired
	MockMvc mvc;
	
	private static final String BOOK_API = "/api/v1/books";
	
	@MockBean
	BookService service;
	
	@Test
	@DisplayName("Deve criar um livro com sucesso")
	public void saveBook() throws Exception {
		
		Book book = Book.builder().id(1L).title("Aprendendo Java").author("Hugo").isbn("123456").build();
		BDDMockito.given(service.save(Mockito.any(Book.class))).willReturn(book);
		
		String json = new ObjectMapper().writeValueAsString(book);
		
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders
									.post(BOOK_API)
									.contentType(MediaType.APPLICATION_JSON)
									.accept(MediaType.APPLICATION_JSON)
									.content(json);
				mvc
					.perform(request)
					.andExpect(MockMvcResultMatchers.status().isCreated())
					.andExpect(MockMvcResultMatchers.jsonPath("id").isNotEmpty())
					.andExpect(MockMvcResultMatchers.jsonPath("title").value(book.getTitle()))
					.andExpect(MockMvcResultMatchers.jsonPath("author").value(book.getAuthor()))
					.andExpect(MockMvcResultMatchers.jsonPath("isbn").value(book.getIsbn()));
									
	}

}
