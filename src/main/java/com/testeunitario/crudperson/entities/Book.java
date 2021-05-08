package com.testeunitario.crudperson.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Builder
public class Book {
	
	private Long id;
	private String title;
	private String author;
	private String isbn;

}