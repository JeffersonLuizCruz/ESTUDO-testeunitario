package com.testeunitario.crudperson.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.testeunitario.crudperson.entities.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>{

}
