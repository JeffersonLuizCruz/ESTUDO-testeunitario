package com.testeunitario.crudperson.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.testeunitario.crudperson.dto.BookDto;
import com.testeunitario.crudperson.entities.Book;
import com.testeunitario.crudperson.service.BookService;

@RestController
@RequestMapping("/api/v1/books")
public class BookController {
	
	private BookService service;
	
	public BookController(BookService service) {
		this.service = service;
	}
	
	
	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public BookDto create(@RequestBody BookDto dto) {
		Book book = Book.builder().author(dto.getAuthor()).title(dto.getTitle()).isbn(dto.getIsbn()).build();
		book = service.save(book);
		
		return BookDto.builder().id(1L).author(book.getAuthor()).title(book.getTitle()).isbn(book.getIsbn()).build();
	}

}
