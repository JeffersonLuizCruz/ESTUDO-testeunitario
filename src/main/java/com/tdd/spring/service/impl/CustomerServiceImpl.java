package com.tdd.spring.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tdd.spring.entity.Customer;
import com.tdd.spring.repository.CustomerRepository;
import com.tdd.spring.service.CustomerService;
import com.tdd.spring.service.exception.BadRequestExceptionService;
import com.tdd.spring.service.exception.NotFoundExceptionService;

@Service
public class CustomerServiceImpl implements CustomerService{

	@Autowired private CustomerRepository customerRepository;
	
	@Override
	public Customer save(Customer customer) {
		verifyIfExistEmail(customer);
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
	
	private void verifyIfExistEmail(Customer customer) {
		Optional<Customer> responseEntity = customerRepository.findByEmail(customer.getEmail());
		if(responseEntity.isPresent() && !responseEntity.get().equals(customer)) {
			throw new BadRequestExceptionService("Email já existe");
		}
	}

}
