package com.level2.books.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class MemberResponseDto {
    // 요청을 하고 처리하는 곳을 만들기
    // 요청에는 민감한 정보를 최대한 넣지 않음 -> ex) RRN, 비밀번호

    private String name;
    private String address;
    private String gender;
    private String phone;

}