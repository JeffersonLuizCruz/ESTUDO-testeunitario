package com.tdd.spring.repository;

import org.springframework.stereotype.Repository;

import com.tdd.spring.entities.Book;

@Repository
public class BookRepositoryImplMock implements BookRepository{

	@Override
	public Book save(Book book) {
		return book;
	}

}
