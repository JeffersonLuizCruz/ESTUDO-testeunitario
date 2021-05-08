package com.testeunitario.crudperson.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookDto {
	
	private Long id;
	private String title;
	private String author;
	private String isbn;

}
