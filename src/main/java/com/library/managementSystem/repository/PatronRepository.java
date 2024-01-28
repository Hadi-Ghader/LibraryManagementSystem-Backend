package com.library.managementSystem.repository;

import com.library.managementSystem.entities.Book;
import com.library.managementSystem.entities.Patron;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PatronRepository extends JpaRepository<Patron, Long> {
    List<Patron> findAll();

    Optional<Patron> findById(Long id);

    boolean existsById(Long id);

    Patron save(Book book);

    void deleteById(Long id);

}
