package com.level2.books.entity;

import com.level2.books.dto.MemberRequestDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


// 1. 우선 데이터베이스에 생성될 엔티티 테이블부터 설계
// 2. 엔티티는 사용자(member)와 책(book) 엔티티로 설계

@Entity // JPA 관리할 엔티티 클래스 지정
@Getter
@Setter
@Table(name = "member") // 매핑없이도 똑같이 구동되야한다 했지만 우선 함
@NoArgsConstructor
public class Member {

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "gender", nullable = false)
    private String gender;

    @Column(name = "RRN", nullable = false, unique = true)
    private String RRN; // 주민등록번호

    @Id
    @Column(name = "phone", nullable = false, unique = true)
    private String phone;

    // 요청에는 멤버의 모든 값을 넘겨줌
    public Member(MemberRequestDto requestDto) {
        // 회원으로 등록할때 필요한 내용
        this.name = requestDto.getName();
        this.address = requestDto.getAddress();
        this.gender = requestDto.getGender();
        this.RRN = requestDto.getRRN();
        this.phone = requestDto.getPhone();
    }
}
