package com.level2.books.entity;

import com.level2.books.dto.ReturnRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity // JPA 관리할 엔티티 클래스 지정
@Getter
@Setter
@Table(name = "return")
@NoArgsConstructor
public class Return {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long returnId; // 반납 고유값

    @ManyToOne // 책:반납 = 1:N (책 하나를 여러번 반납 가능)
    @JoinColumn(name = "book_id", nullable = false)
    private Book book; // 반납한 책에 대한 참조

    @ManyToOne // 멤버:반납 = 1:N (멤버한명이 여러개 반납 가능)
    @JoinColumn(name = "phone", nullable = false)
    private Member member; // 멤버 엔티티 참조

    @Column(name = "return_date")
    private LocalDateTime returnDate; // 반납 일자

    // 반납상태 대신 -> 렌탈필드에서 대출가능상태로 해결해야할듯
    // @Column(name = "return_status")
    // private boolean returnStatus; // 반납상태

    public Return(ReturnRequestDto requestDto, Book book, Member member) {
        this.book = book;
        this.member = member;
        this.returnDate = LocalDateTime.now();
    }
}
