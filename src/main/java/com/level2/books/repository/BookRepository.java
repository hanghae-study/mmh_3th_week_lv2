package com.level2.books.repository;

import com.level2.books.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    // 책 전체조회 오름차순
    List<Book> findAllByOrderByModifiedAtAsc();

    // 특정 책id로 책 조회
    Book findBookById(Long id);

}
