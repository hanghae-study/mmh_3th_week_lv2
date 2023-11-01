package com.level2.books.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class ReturnRequestDto {
    //private Long rentalId;
    private Long bookId;
    private String phone;
    private LocalDateTime returnDate;
    //private boolean returnStatus;
}
