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

        // 렌탈에서 가져오는 책 관련 필드들은 직접 필드를 선언해서 값을 주는게 아니라
        // 참조한 테이블의 값을 가져오기때문에
        // TODO 여기가 뭔가 문제인것같은데 아직 모르겠 - Null 값의 원인?

        this.bookId = getBookId(); // 원래 책의 고유값
        this.writer = getWriter(); // 책의 작가
        this.title = getTitle(); // 책의 제목
        this.phone = getPhone(); // 멤버의 전화번호
        this.name = getName(); // 멤버의 이름

        // 순수하게 렌탈관련 값 -> 여기는 잘 출력됨
        this.rentalId = rental.getRentalId(); // 렌탈한 책의 고유값
        this.rentalDate = LocalDateTime.now();
    }
}
