package com.library.managementSystem.service;

import com.library.managementSystem.entities.Book;
import com.library.managementSystem.entities.BorrowingRecord;
import com.library.managementSystem.entities.Patron;
import com.library.managementSystem.repository.BorrowingRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class BorrowingRecordService {
    @Autowired
    private BorrowingRecordRepository borrowingRecordRepository;

    public Optional<BorrowingRecord> findByBookAndPatronAndReturnDateIsNull(Book book, Patron patron){
        return borrowingRecordRepository.findByBookAndPatronAndReturnDateIsNull(book, patron);
    }

    public BorrowingRecord save(BorrowingRecord borrowingRecord){
        return borrowingRecordRepository.save(borrowingRecord);
    }
}
