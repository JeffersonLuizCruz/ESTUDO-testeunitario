package com.tdd.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.tdd.spring.controller.dto.CustomerRequestDto;
import com.tdd.spring.controller.dto.CustomerResponseDto;
import com.tdd.spring.controller.mapper.CustomerMapper;
import com.tdd.spring.entity.Customer;
import com.tdd.spring.service.CustomerService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/customers")
public class CustomerController {

	private @Autowired CustomerMapper customerMapper;
	private @Autowired CustomerService customerService;
	
	@PostMapping
	public ResponseEntity<CustomerResponseDto> save(@Valid @RequestBody CustomerRequestDto dto){
		Customer customerModel = customerMapper.toModel(dto);
		CustomerResponseDto customerResponse = customerMapper.toDTO(customerService.save(customerModel));
		return ResponseEntity.status(HttpStatus.CREATED).body(customerResponse);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CustomerResponseDto> findById(@PathVariable Long id){
		return ResponseEntity.ok(customerMapper.toDTO(customerService.findById(id)));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<CustomerResponseDto> update(@PathVariable Long id, @Valid @RequestBody CustomerRequestDto dto){
		Customer customerModel = customerMapper.toModel(dto);
		CustomerResponseDto customerResponse = customerMapper.toDTO(customerService.update(id, customerModel));
		return ResponseEntity.status(HttpStatus.CREATED).body(customerResponse);
	}
	
	@GetMapping
	public ResponseEntity<List<CustomerResponseDto>> findAll(){
		return ResponseEntity.ok(customerService.findAll()
				.stream()
				.map(customerMapper::toDTO)
				.toList());
	}
	
	@DeleteMapping("/{id}") @ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void deleteById(@PathVariable Long id) {
		customerService.deleteById(id);
	}
}
