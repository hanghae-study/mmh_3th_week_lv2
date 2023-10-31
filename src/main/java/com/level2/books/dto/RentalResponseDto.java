package com.level2.books.dto;

import com.level2.books.entity.Rental;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class RentalResponseDto {
    // 북의 필드 값
    private Long bookId;
    private String writer;
    private String title;

    // 멤버의 필드 값
    private String name;
    private String phone;

    // 렌탈의 필드 값
    private Long rentalId;
    private LocalDateTime rentalDate; // 대출일

    public RentalResponseDto(Rental rental) {
        this.bookId = getBookId(); // 원래 책의 고유값
        this.writer = getWriter(); // 책의 작가
        this.title = getTitle(); // 책의 제목
        this.phone = getPhone(); // 멤버의 전화번호
        this.name = getName(); // 멤버의 이름
        this.rentalId = rental.getRentalId(); // 렌탈한 책의 고유값
        this.rentalDate = rental.getRentalDate();
    }
}
