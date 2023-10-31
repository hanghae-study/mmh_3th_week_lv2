package com.level2.books.repository;

import com.level2.books.entity.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RentalRepository extends JpaRepository<Rental, Long> {
    List<Rental> findByMemberPhoneAndRentedIsFalse(String phone);

    List<Rental> findByMemberPhone(String phone);

}
