package com.library.managementSystem.controller;

import com.library.managementSystem.entities.Book;
import com.library.managementSystem.entities.BorrowingRecord;
import com.library.managementSystem.entities.Patron;
import com.library.managementSystem.service.BookService;
import com.library.managementSystem.service.BorrowingRecordService;
import com.library.managementSystem.service.PatronService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class BorrowingRecordController {

    @Autowired
    private BookService bookService;

    @Autowired
    private PatronService patronService;

    @Autowired
    private BorrowingRecordService borrowingRecordService;

    @PostMapping("/borrow/{bookId}/patron/{patronId}")
    public ResponseEntity<String> borrowBook(@PathVariable Long bookId, @PathVariable Long patronId) {
        Optional<Book> optionalBook = bookService.findById(bookId);
        Optional<Patron> optionalPatron = patronService.findById(patronId);

        if (optionalBook.isPresent() && optionalPatron.isPresent()) {
            Book book = optionalBook.get();
            Patron patron = optionalPatron.get();

            if (!book.isBorrowed()) {
                BorrowingRecord borrowingRecord = new BorrowingRecord(book, patron, LocalDate.now().toString());
                borrowingRecordService.save(borrowingRecord);

                book.setBorrowed(true);
                bookService.save(book);

                return new ResponseEntity<>("Book borrowed successfully", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Book is already borrowed", HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>("Book or patron not found", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/return/{bookId}/patron/{patronId}")
    public ResponseEntity<String> returnBook(@PathVariable Long bookId, @PathVariable Long patronId) {
        Optional<Book> optionalBook = bookService.findById(bookId);
        Optional<Patron> optionalPatron = patronService.findById(patronId);

        if (optionalBook.isPresent() && optionalPatron.isPresent()) {
            Book book = optionalBook.get();
            Patron patron = optionalPatron.get();

            Optional<BorrowingRecord> optionalBorrowingRecord = borrowingRecordService.findByBookAndPatronAndReturnDateIsNull(book, patron);

            if (optionalBorrowingRecord.isPresent()) {
                BorrowingRecord borrowingRecord = optionalBorrowingRecord.get();
                borrowingRecord.setReturnDate(LocalDate.now().toString());
                borrowingRecordService.save(borrowingRecord);

                book.setBorrowed(false);
                bookService.save(book);

                return new ResponseEntity<>("Book returned successfully", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Patron did not borrow the specified book", HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>("Book or patron not found", HttpStatus.NOT_FOUND);
        }
    }
}
