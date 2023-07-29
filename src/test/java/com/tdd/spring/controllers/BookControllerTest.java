package com.tdd.spring.controllers;

import org.junit.jupiter.api.BeforeEach;
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
import com.tdd.spring.entity.Book;
import com.tdd.spring.service.BookService;
import com.tdd.spring.service.exception.BadRequestExceptionService;


@ActiveProfiles("test")
@WebMvcTest
@AutoConfigureMockMvc
public class BookControllerTest {
	
	@Autowired
	MockMvc mvc;
	
	private static final String BOOK_API = "/api/v1/books";
	
	@MockBean
	BookService service;
	
	private Book book = new Book();
	
	@BeforeEach
	public void setUp() {
		this.book = Book.builder().id(1L).title("Aprendendo Java").author("Hugo").isbn("123456").build();
	}
	
	@Test
	@DisplayName("Deve criar um livro com sucesso!")
	public void saveBook() throws Exception {
		BDDMockito.given(service.save(this.book)).willReturn(this.book);
		String json = new ObjectMapper().writeValueAsString(this.book);

		MockHttpServletRequestBuilder request = MockMvcRequestBuilders
				.post(BOOK_API)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(json);
		
		mvc.perform(request)
				.andExpect(MockMvcResultMatchers.status().isCreated())
				.andExpect(MockMvcResultMatchers.jsonPath("id").isNotEmpty())
				.andExpect(MockMvcResultMatchers.jsonPath("title").value(this.book.getTitle()))
				.andExpect(MockMvcResultMatchers.jsonPath("author").value(this.book.getAuthor()))
				.andExpect(MockMvcResultMatchers.jsonPath("isbn").value(this.book.getIsbn()));
	}
	
	@Test
	@DisplayName("Deve retornar uma exceção de de requisição ruim!")
	public void returnExeceptionBadRequest() throws Exception {
		Book book = Book.builder().id(7L).title("Aprendendo Java").author("Hugo").isbn("123456").build();
		BDDMockito.given(service.save(Mockito.any(Book.class))).willThrow(new BadRequestExceptionService("Corpo da requisição ruim"));
		String json = new ObjectMapper().writeValueAsString(book);
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders
				.post(BOOK_API)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(json);
		
		mvc.perform(request)
		.andExpect(MockMvcResultMatchers.status().isBadRequest());
	}

}
