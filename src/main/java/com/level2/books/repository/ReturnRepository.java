package com.level2.books.repository;

import com.level2.books.entity.Return;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReturnRepository extends JpaRepository<Return, Long> {

    // ReturnRepository는 지금 별다른 쓰임이 없어보임.. TODO 확인필요
}
