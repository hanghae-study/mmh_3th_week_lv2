package com.level2.books.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReturnResponseDto {

    // 책의 고유값 + 필요정보
    private Long bookId;
    private String writer;
    private String title;

    // 멤버 필드 값 + 필요한 정보
    private String name;
    private String phone;

    // 리턴 고유값 + 필요정보
    private Long returnId;
    private LocalDateTime returnDate;
    private boolean returnStatus;

}
