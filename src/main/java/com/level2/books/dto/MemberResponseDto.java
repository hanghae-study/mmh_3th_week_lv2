package com.level2.books.dto;

import com.level2.books.entity.Member;
import lombok.Getter;

@Getter
public class MemberResponseDto {

    // 4. 리퀘스트디티오 -> 리스폰스디티오를 작성
    // 요청을 하고 처리하는 곳을 만들기

    // 요청에는 민감한 정보를 최대한 넣지 않음 -> ex) RRN, 비밀번호

    private String name;
    private String address;
    private String gender;
    private String phone;

    // 멤버엔티티의 값을 매개변수로 받아옴 -> 그걸로 응답을 줌
    // 응답을 주는 값에는 주민등록번호를 제줌
    public MemberResponseDto(Member member) {
        this.name = member.getName(); // 조회리스트에 가져와야하는 값
        this.gender = member.getGender();
        this.address = member.getAddress();
        this.phone = member.getPhone(); // 조회리스트에 가져와야하는 값
    }
}