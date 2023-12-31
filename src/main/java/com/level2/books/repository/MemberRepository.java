package com.level2.books.repository;

import com.level2.books.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member, String> {
    List<Member> findAllByOrderByPhoneDesc();

    // 특정 전화번호로 회원조회
    Member findByPhone(String phone);
}
