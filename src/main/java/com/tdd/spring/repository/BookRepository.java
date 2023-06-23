package com.tdd.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tdd.spring.entities.Book;

public interface BookRepository extends JpaRepository<Book, Long>{

}
