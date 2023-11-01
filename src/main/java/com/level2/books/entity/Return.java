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
    private Long returnId; // 반납 고유값 -> 데이터베이스에는 기록이되나 따로 사용하는건 안함 -> 사용하려했으나 실패

    @ManyToOne // 책:반납 = 1:N (책 하나를 여러번 반납 가능) -> 책하고 반납도 @OneToOne이 되어야하는거 아닌지..?
    @JoinColumn(name = "book_id", nullable = false)
    private Book book; // 책에 대한 참조

    @ManyToOne // 멤버:반납 = 1:N (멤버한명이 여러개 반납 가능)
    @JoinColumn(name = "phone", nullable = false)
    private Member member; // 멤버에 대한 참조

    @Column(name = "return_date")
    private LocalDateTime returnDate; // 반납 일자

    // TODO 반납상태 추가해서 빌린상태와 반납상태가 서로 반대로 동작하게 하고싶었으나 실패

    public Return(ReturnRequestDto requestDto, Book book, Member member) {
        this.book = book;
        this.member = member;
        this.returnDate = LocalDateTime.now();
    }
}
