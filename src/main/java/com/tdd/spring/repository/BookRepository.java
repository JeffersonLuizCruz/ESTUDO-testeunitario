package com.tdd.spring.repository;

import com.tdd.spring.entities.Book;

public interface BookRepository{

	Book save(Book book);
}
