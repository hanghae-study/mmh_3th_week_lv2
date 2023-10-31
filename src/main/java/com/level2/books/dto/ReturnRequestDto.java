package com.level2.books.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ReturnRequestDto {
    private Long bookId;
    private String phone;
    private LocalDateTime returnDate;
    private boolean returnStatus;
}
