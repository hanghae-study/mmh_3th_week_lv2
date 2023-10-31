package com.level2.books.dto;

import com.level2.books.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MemberResponseDto {
    // 요청을 하고 처리하는 곳을 만들기
    // 요청에는 민감한 정보를 최대한 넣지 않음 -> ex) RRN, 비밀번호
    private String name;
    private String address;
    private String gender;
    private String phone;

    public MemberResponseDto(Member member) {
        this.name = member.getName();
        this.address = member.getAddress();
        this.phone = member.getPhone();
        this.gender = member.getGender();
    }

}