package com.level2.books.controller;

import com.level2.books.dto.MemberRequestDto;
import com.level2.books.dto.MemberResponseDto;
import com.level2.books.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class MemberController {
    private final MemberService memberService;


    // 회원 등록
    @PostMapping("/member")
    public MemberResponseDto joinMember(@RequestBody MemberRequestDto requestDto) {
        return memberService.joinMember(requestDto);
    }

    // 전체 회원 조회
    @GetMapping("/members")
    public List<MemberResponseDto> getMembers() {
        return memberService.getMembers();
    }

    // 특정 회원 조회 -> 전화번호 조회
    @GetMapping("/member/{phone}")
    public MemberResponseDto getMemberByRRN(@PathVariable String phone) {
        return memberService.getMemberByRRN(phone);
    }
}
