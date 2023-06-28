package com.tdd.spring.services;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import com.tdd.spring.entity.Book;
import com.tdd.spring.repository.BookRepository;
import com.tdd.spring.service.BookService;
import com.tdd.spring.service.impl.BookServiceImpl;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {
	
	@Autowired
	BookService bookService;
	
	@Mock
	BookRepository bookRepository;
	
	private Book book;
	
	@BeforeEach
	public void setUp() {
		this.bookService = new BookServiceImpl(bookRepository);
		this.book = Book.builder()
				.id(1L)
				.title("Programando Java")
				.author("Hugo")
				.isbn("123")
				.build();
	}
	
	@Test
	@DisplayName("Deve salvar um livro")
	public void saveBookTest() {
		/*
		 * O trecho de código configura o comportamento do método save() do objeto
		 * bookService para retornar um objeto Book com valores específicos quando é
		 * chamado com um argumento book. Isso permite simular o comportamento desse
		 * método durante a execução dos testes.
		 */
		Mockito.when(bookService.save(book)).thenReturn(Book.builder()
				.id(1L)
				.title("Programando Java")
				.author("Hugo")
				.isbn("123")
				.build());
		
		Book saveBook = bookService.save(book);
		
		Assertions.assertThat(saveBook.getId()).isNotNull();
		Assertions.assertThat(saveBook.getIsbn()).isEqualTo("123");
		Assertions.assertThat(saveBook.getTitle()).isEqualTo("Programando Java");
		Assertions.assertThat(saveBook.getAuthor()).isEqualTo("Hugo");
	}

}