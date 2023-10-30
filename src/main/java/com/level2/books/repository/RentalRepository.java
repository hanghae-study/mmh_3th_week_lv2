package com.level2.books.repository;

import com.level2.books.entity.Rental;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RentalRepository extends JpaRepository<Rental, Long> {
    List<Rental> findByMemberIdAndReturnStatusIsFalse(String RRN);
}
