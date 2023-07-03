package com.tdd.spring.service;

import java.util.List;

import com.tdd.spring.entity.Customer;

public interface CustomerService {
	Customer save(Customer customer);
	Customer findById(Long id);
	List<Customer> findAll();
	Customer update(Long id, Customer customer);
	void deleteById(Long id);
}
