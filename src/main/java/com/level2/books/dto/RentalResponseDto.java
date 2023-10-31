package com.level2.books.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RentalResponseDto {

    // 북의 필드 값
    private Long bookId;
    private String write;
    private String title;

    // 멤버의 필드 값
    private String phone;
    private String name;

    // 렌탈의 필드 값
    private Long rentalId;
    private LocalDateTime rentalDate; // 대출일

}
