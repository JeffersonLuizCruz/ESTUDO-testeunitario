package com.tdd.spring.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tdd.spring.entity.Book;
import com.tdd.spring.repository.BookRepository;
import com.tdd.spring.service.BookService;

@Service
public class BookServiceImpl implements BookService{

	@Autowired private BookRepository bookRepository;
	
	
	public BookServiceImpl(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}


	@Override
	public Book save(Book book) {
		return bookRepository.save(book);
	}

}
