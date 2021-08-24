package com.tdd.spring.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.tdd.spring.dto.BookDTO;
import com.tdd.spring.entities.Book;
import com.tdd.spring.services.BookService;

@RestController
@RequestMapping("/api/v1/books")
public class BookController {
	
	private BookService service;
	private ModelMapper modelMapper;
	
	public BookController(BookService service, ModelMapper modelMapper) {
		this.service = service;
		this.modelMapper = modelMapper;
	}
	
	
	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public BookDTO create(@RequestBody BookDTO dto) {
		Book book = modelMapper.map(dto, Book.class);
		book = service.save(book);
		
		return modelMapper.map(book, BookDTO.class);
	}
}
