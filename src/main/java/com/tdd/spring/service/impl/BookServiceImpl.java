package com.tdd.spring.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.tdd.spring.entity.Book;
import com.tdd.spring.repository.BookRepository;
import com.tdd.spring.service.BookService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class BookServiceImpl implements BookService{

	@Autowired private BookRepository bookRepository;
	
	@Override
	public Book save(Book book) {
		return bookRepository.save(book);
	}

}
