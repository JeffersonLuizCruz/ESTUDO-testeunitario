package com.tdd.spring.controller.mapper;

import org.mapstruct.Mapper;

import com.tdd.spring.controller.dto.BookRequestDto;
import com.tdd.spring.controller.dto.BookResponseDto;
import com.tdd.spring.entity.Book;

@Mapper(componentModel = "spring")
public interface BookMapper {
	BookResponseDto toModel(BookRequestDto dto);
	BookResponseDto toDTO(Book book);
}
