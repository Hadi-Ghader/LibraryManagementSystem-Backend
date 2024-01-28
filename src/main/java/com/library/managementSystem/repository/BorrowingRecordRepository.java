package com.library.managementSystem.repository;

import com.library.managementSystem.entities.Book;
import com.library.managementSystem.entities.BorrowingRecord;
import com.library.managementSystem.entities.Patron;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BorrowingRecordRepository extends JpaRepository<BorrowingRecord, Long> {
    Optional<BorrowingRecord> findByBookAndPatronAndReturnDateIsNull(Book book, Patron patron);

    BorrowingRecord save(BorrowingRecord borrowingRecord);

}
