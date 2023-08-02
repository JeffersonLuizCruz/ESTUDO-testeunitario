package com.tdd.spring.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.tdd.spring.entity.Customer;
import com.tdd.spring.repository.CustomerRepository;
import com.tdd.spring.service.exception.NotFoundExceptionService;
import com.tdd.spring.service.impl.CustomerServiceImpl;

@SpringBootTest
public class CustomerServiceTest {

	private static final int INDEX 					= 0;
	private static final Long ID 					= 1L;
	private static final String EMAIL 				= "jeff.luiz.cruz@gmail.com";
	private static final String PASSWORD 			= "123";
	private static final String NOT_FOUND_EXCEPTION = "Objeto não encontrado!";
	
	/*
	 * @Mock cria uma simulação. @InjectMocks cria uma instância da classe e injeta os
	 * mocks que são criados com as anotações @Mock(ou @Spy) nesta instância.
	 */
	
	@InjectMocks private CustomerServiceImpl customerServiceImpl;
	@Mock private CustomerRepository customerRepository;
	@Mock private Customer customer = new Customer();
	@Mock private Optional<Customer> optionalCustomer;
	
	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		startCustomer();
	}
	
	@Test
	void whenFindByIdThenReturnAnCustomerInstance() {
		when(customerRepository.findById(anyLong())).thenReturn(optionalCustomer);
		
		Customer responseEntity = customerServiceImpl.findById(ID);

		assertNotNull(responseEntity);
		assertEquals(ID, responseEntity.getId());
		assertEquals(Customer.class, responseEntity.getClass());
	}
	
	@Test
	void whenFindByIdReturnAnObjectNotFoundException() {
		when(customerRepository.findById(anyLong())).thenThrow(new NotFoundExceptionService(NOT_FOUND_EXCEPTION));
		NotFoundExceptionService ex = null;
		
		try {
			customerServiceImpl.findById(ID);
		} catch (NotFoundExceptionService e) {
			ex = e;
		}
		assertEquals(NotFoundExceptionService.class, ex.getClass());
		assertEquals(NOT_FOUND_EXCEPTION, ex.getMessage());
	}
	
	@Test
	void whenFindAllReturnAnListOfCustomers() {
		when(customerRepository.findAll()).thenReturn(List.of(this.customer));
		
		List<Customer> customerEntities = customerServiceImpl.findAll();
		
		assertNotNull(customerEntities);
		assertEquals(1, customerEntities.size());
		assertEquals(Customer.class, customerEntities.get(INDEX).getClass());
		
		assertEquals(ID, customerEntities.get(INDEX).getId());
		assertEquals(EMAIL, customerEntities.get(INDEX).getEmail());
		assertEquals(PASSWORD, customerEntities.get(INDEX).getPassword());
	}
	
	@Test
	void whenSaveThenReturnAnCustomer() {
		when(customerRepository.save(any())).thenReturn(this.customer);
		
		Customer responseEntity = customerServiceImpl.save(customer);
		
		assertNotNull(responseEntity);
		assertEquals(Customer.class, responseEntity.getClass());
		
		assertEquals(ID, responseEntity.getId());
		assertEquals(EMAIL, responseEntity.getEmail());
		assertEquals(PASSWORD, responseEntity.getPassword());
	}
	
	@Test
	void whenUpdateThenReturnAnCustomer() {
		when(customerRepository.findById(anyLong())).thenReturn(optionalCustomer);
		when(customerRepository.save(any())).thenReturn(this.customer);
		
		Customer responseEntity = customerServiceImpl.update(ID, customer);
		
		assertNotNull(responseEntity);
		assertEquals(Customer.class, responseEntity.getClass());
		
		assertEquals(ID, responseEntity.getId());
		assertEquals(EMAIL, responseEntity.getEmail());
		assertEquals(PASSWORD, responseEntity.getPassword());
	}
	
	@Test
	void whenDeleteByIdWithSuccess() {
		when(customerRepository.findById(anyLong())).thenReturn(optionalCustomer);
		doNothing().when(customerRepository).deleteById(anyLong());
		
		customerServiceImpl.deleteById(ID);
		
		verify(customerRepository, times(1)).deleteById(anyLong());
	}
	
	@Test
	void deleteByIdWithNotFoundException() {
		when(customerRepository.findById(anyLong()))
		.thenThrow(new NotFoundExceptionService("Objeto não encontrado!"));
		
		NotFoundExceptionService ex = null;
		
		try {
			customerServiceImpl.deleteById(ID);
		} catch (NotFoundExceptionService e) {
			ex = e;
		}
		
		assertEquals(NotFoundExceptionService.class, ex.getClass());
		assertEquals("Objeto não encontrado!", ex.getMessage());
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
	}
}
