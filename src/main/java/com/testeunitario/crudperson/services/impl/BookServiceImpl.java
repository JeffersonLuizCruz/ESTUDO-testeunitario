package com.testeunitario.crudperson.services.impl;

import org.springframework.stereotype.Service;

import com.testeunitario.crudperson.entities.Book;
import com.testeunitario.crudperson.repositories.BookRepository;
import com.testeunitario.crudperson.services.BookService;


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
