package com.tdd.spring.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class BookResponseDto {
	private Long id;
	private String title;
	private String author;
	private String isbn;
}
