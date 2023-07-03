package com.tdd.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tdd.spring.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long>{

}
