package com.level2.books.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class BookRequestDto {
    private Long BookId;
    private String title;
    private String writer;
    private String company;
    private String lang;
    private LocalDateTime regDate;
}
