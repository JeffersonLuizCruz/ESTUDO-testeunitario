package com.tdd.spring.controller.mapper;

import org.mapstruct.Mapper;

import com.tdd.spring.controller.dto.CustomerRequestDto;
import com.tdd.spring.controller.dto.CustomerResponseDto;
import com.tdd.spring.entity.Customer;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
	Customer toModel(CustomerRequestDto dto);
	CustomerResponseDto toDTO(Customer customer);
}
