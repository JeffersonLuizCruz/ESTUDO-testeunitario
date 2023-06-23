package com.tdd.spring.services.impl;

import org.springframework.stereotype.Service;

import com.tdd.spring.entities.Book;
import com.tdd.spring.repository.BookRepository;
import com.tdd.spring.services.BookService;

@Service
public class BookServiceImpl implements BookService{

	private BookRepository bookRepository;
	
	public BookServiceImpl(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}
	
	@Override
	public Book save(Book book) {
		return bookRepository.save(book);
	}

}
