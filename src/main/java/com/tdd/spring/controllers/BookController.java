package com.tdd.spring.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.tdd.spring.entities.Book;
import com.tdd.spring.services.BookService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/books")
public class BookController {
	
	private BookService bookService;
	
	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public Book create(@RequestBody Book book) {

		return bookService.save(book);
	}
}
