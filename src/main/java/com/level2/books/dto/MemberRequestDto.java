package com.level2.books.dto;

import lombok.Getter;

@Getter
public class MemberRequestDto {

    // 회원등록을할 때 요청되는 정보들
    private String name;
    private String address;
    private String gender;
    private String RRN; // 식별을 위해 중복값이 없는 주민등록번호
    private String phone; // 전화번호도 중복값이 없음
}
