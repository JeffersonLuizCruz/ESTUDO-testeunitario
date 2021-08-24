package com.tdd.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tdd.spring.entities.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>{

}
