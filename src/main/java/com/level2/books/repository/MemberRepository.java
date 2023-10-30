package com.level2.books.repository;

import com.level2.books.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, String> {
    List<Member> findAllByOrderByModifiedAtDesc();

    // 특정 주민번호로 회원조회
    Member findByRRN(String RRN);
}
