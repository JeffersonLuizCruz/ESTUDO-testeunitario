package com.tdd.spring.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @Builder
@AllArgsConstructor @NoArgsConstructor
public class CustomerRequestDto {
	private String email;
	@JsonProperty(access = Access.WRITE_ONLY)
	private String password;
}
