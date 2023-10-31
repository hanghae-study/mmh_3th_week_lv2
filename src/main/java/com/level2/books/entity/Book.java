package com.level2.books.entity;

import com.level2.books.dto.BookRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.mapping.ToOne;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "book")
@NoArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookId; // 데이터베이스 생성 순서대로 등록id 부여하기(다른 값들은 pk가 될수없음)

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "writer", nullable = false)
    private String writer;

    @Column(name = "company",  nullable = false)
    private String company;

    @Column(name = "lang",  nullable = false)
    private String lang;

    @Column(name = "regDate",  nullable = false)
    private LocalDateTime regDate; // 등록일

    @Column(name = "available", nullable = false)
    private boolean available; // 대출 가능 여부


    public Book(BookRequestDto requestDto) {
        this.bookId = requestDto.getBookId();
        this.title = requestDto.getTitle();
        this.company = requestDto.getCompany();
        this.writer = requestDto.getWriter();
        this.lang = requestDto.getLang();
        this.regDate = LocalDateTime.now();
        this.available = true; // 초기 상태로 대출 가능하도록 설정
    }

}
