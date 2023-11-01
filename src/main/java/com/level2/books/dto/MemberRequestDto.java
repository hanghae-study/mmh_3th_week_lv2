package com.level2.books.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MemberRequestDto {

    // 회원등록을할 때 요청되는 정보들
    private String name;
    private String address;
    private String gender;
    private String RRN;
    private String phone;
}
