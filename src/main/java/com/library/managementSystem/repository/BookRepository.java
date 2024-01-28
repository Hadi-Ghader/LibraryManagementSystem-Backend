package com.library.managementSystem.repository;

import com.library.managementSystem.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findAll();

    Optional<Book> findById(Long id);

    boolean existsById(Long id);

    Book save(Book book);

    void deleteById(Long id);

}
