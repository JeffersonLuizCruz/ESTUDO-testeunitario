package com.testeunitario.crudperson.servicetest;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.testeunitario.crudperson.entities.Book;
import com.testeunitario.crudperson.repositories.BookRepository;
import com.testeunitario.crudperson.services.BookService;
import com.testeunitario.crudperson.services.impl.BookServiceImpl;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class BookServiceTest {
	
	BookService service;
	
	@MockBean
	BookRepository bookRepository;
	
	@BeforeEach
	public void setUp() {
		this.service = new BookServiceImpl(bookRepository);
	}
	
	@Test
	@DisplayName("Deve salvar um livro")
	public void saveBookTest() {
		
		Book book = Book.builder().title("Programando Java").author("Hugo").isbn("123").build();
		Mockito.when(bookRepository.save(book)).thenReturn(
												Book.builder().id(1L).title("Programando Java").author("Hugo").isbn("123").build());
		
		Book saveBook = service.save(book);
		
		Assertions.assertThat(saveBook.getId()).isNotNull();
		Assertions.assertThat(saveBook.getIsbn()).isEqualTo("123");
		Assertions.assertThat(saveBook.getTitle()).isEqualTo("Programando Java");
		Assertions.assertThat(saveBook.getAuthor()).isEqualTo("Hugo");
		
	}

}
