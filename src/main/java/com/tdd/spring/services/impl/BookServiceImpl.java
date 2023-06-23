package com.tdd.spring.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tdd.spring.entities.Book;
import com.tdd.spring.repository.BookRepository;
import com.tdd.spring.services.BookService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class BookServiceImpl implements BookService{

	@Autowired private BookRepository bookRepository;
	
	@Override
	public Book save(Book book) {
		return bookRepository.save(book);
	}

}
