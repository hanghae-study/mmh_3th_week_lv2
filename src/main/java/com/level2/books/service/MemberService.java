package com.level2.books.service;

import com.level2.books.dto.MemberRequestDto;
import com.level2.books.dto.MemberResponseDto;
import com.level2.books.entity.Member;
import com.level2.books.repository.MemberRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberResponseDto joinMember(MemberRequestDto requestDto) {

        // RequestDto -> Entity
        Member member = new Member(requestDto);

        // DB 저장
        Member saveMember = memberRepository.save(member);

        // Entity -> ResponseDto
        MemberResponseDto memberResponseDto = new MemberResponseDto(saveMember);
        return memberResponseDto;
    }

    // 전체 회원조회 -> 요구사항에는 전체회원조회는 없었던듯
    public List<MemberResponseDto> getMembers() {
        // DB 조회 -> 등록한 모든 회원 조회 -> 수정일 기준으로 내림차순 조회
        return memberRepository.findAllByOrderByModifiedAtDesc().stream().map(MemberResponseDto::new).toList();
    }

    // 특정 주민등록번호로 회원조회
    public MemberResponseDto getMemberByRRN(String phone) {
        Member member = memberRepository.findByPhone(phone);

        if (member == null) {
            // 주민등록번호와 일치하는 회원이 없을 경우 예외 처리
            throw new IllegalArgumentException("입력하신 주민등록번호에 해당하는 회원을 찾을 수 없습니다.");
        }

        // Entity -> ResponseDto
        return new MemberResponseDto(member);
    }
}
