package com.tdd.spring.services;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.tdd.spring.entity.Book;
import com.tdd.spring.repository.BookRepository;
import com.tdd.spring.service.BookService;
import com.tdd.spring.service.impl.BookServiceImpl;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class BookServiceTest {
	
	BookService bookService;
	
	@MockBean 
	BookRepository bookRepository;
	
	@BeforeEach
	public void setUp() {
		this.bookService = new BookServiceImpl(bookRepository);
	}
	
	@Test
	@DisplayName("Deve salvar um livro")
	public void saveBookTest() {
		
		Book book = Book.builder().id(1L).title("Programando Java").author("Hugo").isbn("123").build();
		Mockito.when(bookRepository.save(book)).thenReturn(book);
		
		Book saveBook = bookService.save(book);
		
		Assertions.assertThat(saveBook.getId()).isNotNull();
		Assertions.assertThat(saveBook.getIsbn()).isEqualTo("123");
		Assertions.assertThat(saveBook.getTitle()).isEqualTo("Programando Java");
		Assertions.assertThat(saveBook.getAuthor()).isEqualTo("Hugo");
		
	}

}