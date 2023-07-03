package com.tdd.spring.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tdd.spring.entity.Customer;
import com.tdd.spring.repository.CustomerRepository;
import com.tdd.spring.service.CustomerService;
import com.tdd.spring.service.exception.NotFoundExceptionService;

import lombok.AllArgsConstructor;

@Service @AllArgsConstructor
public class CustomerServiceImpl implements CustomerService{

	private final CustomerRepository customerRepository;
	
	@Override
	public Customer save(Customer customer) {
		return customerRepository.save(customer);
	}

	@Override
	public Customer findById(Long id) {
		return customerRepository.findById(id)
				.orElseThrow(() -> new NotFoundExceptionService("Objeto não encontrado!"));
	}

	@Override
	public List<Customer> findAll() {
		return customerRepository.findAll();
	}

	@Override
	public Customer update(Long id, Customer customer) {
		findById(id);
		customer.setId(id);
		return customerRepository.save(customer);
	}

	@Override
	public void deleteById(Long id) {
		findById(id);
		customerRepository.deleteById(id);
	}

}
