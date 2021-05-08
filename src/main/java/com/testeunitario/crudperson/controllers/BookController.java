package com.testeunitario.crudperson.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.testeunitario.crudperson.dto.BookDto;

@RestController
@RequestMapping("/api/v1/books")
public class BookController {
	
	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public BookDto create(BookDto book) {
		book.setId(11L);
		book.setTitle("Meu Livro");
		book.setAuthor("Autor");
		book.setIsbn("123456");
		
		return book;
	}

}
