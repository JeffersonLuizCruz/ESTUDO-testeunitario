package com.tdd.spring.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import com.tdd.spring.controller.CustomerController;
import com.tdd.spring.controller.dto.CustomerResponseDto;
import com.tdd.spring.controller.mapper.CustomerMapper;
import com.tdd.spring.entity.Customer;
import com.tdd.spring.service.impl.CustomerServiceImpl;

@SpringBootTest
public class CustomerControllerTest {

	private static final int INDEX 					= 0;
	private static final Long ID 					= 1L;
	private static final String EMAIL 				= "jeff.luiz.cruz@gmail.com";
	private static final String PASSWORD 			= "123";
	private static final String NOT_FOUND_EXCEPTION = "Objeto não encontrado!";
	
	@InjectMocks private CustomerController customerController;
	@Mock private CustomerServiceImpl customerServiceImpl;
	@Mock private CustomerMapper customerMapper;
	@Mock private Optional<Customer> optionalCustomer;
	@Mock private Customer customer;
	@Mock private CustomerResponseDto customerResponseDto;
	
	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		startCustomer();
	}
	
	@Test
	void whenFindByIdThenReturnSuccess() {
		when(customerMapper.toDTO(any())).thenReturn(customerResponseDto);
		when(customerServiceImpl.findById(anyLong())).thenReturn(customer);
		
		ResponseEntity<CustomerResponseDto> responseCustomer = customerController.findById(ID);
		
		assertNotNull(responseCustomer);
		assertNotNull(responseCustomer.getBody());
		assertEquals(ResponseEntity.class, responseCustomer.getClass());
		assertEquals(CustomerResponseDto.class, responseCustomer.getBody().getClass());
		
		assertEquals(ID, responseCustomer.getBody().getId());
		assertEquals(EMAIL, responseCustomer.getBody().getEmail());
		assertEquals(PASSWORD, responseCustomer.getBody().getPassword());
		
	}
	
	void startCustomer() {
		this.customer = Customer.builder()
		.id(ID)
		.email(EMAIL)
		.password(PASSWORD)
		.build();
		
		this.optionalCustomer = Optional.of(
				Customer.builder()
				.id(ID)
				.email(EMAIL)
				.password(PASSWORD)
				.build()
				);
		
		this.customerResponseDto = CustomerResponseDto.builder()
		.id(ID)
		.email(EMAIL)
		.password(PASSWORD)
		.build();
	}
}
