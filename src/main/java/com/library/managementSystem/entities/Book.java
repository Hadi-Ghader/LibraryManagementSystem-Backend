package com.library.managementSystem.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    @NotBlank(message = "Title is required")
    private String title;

    @Column
    @NotBlank(message = "Author is required")
    private String author;

    @Column(name="publication_year")
    @Pattern(regexp = "^\\d{4}$", message = "Publication year should be a 4-digit number")
    private String publicationYear;

    @Column
    @NotBlank(message = "ISBN is required")
    private String isbn;

    @Column(name="is_borrowed")
    private boolean isBorrowed;

    public Book() {
    }

    public Book(long id, String title, String author, String publicationYear, String isbn, boolean isBorrowed) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
        this.isbn = isbn;
        this.isBorrowed = isBorrowed;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(String publicationYear) {
        this.publicationYear = publicationYear;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public boolean isBorrowed() {
        return isBorrowed;
    }

    public void setBorrowed(boolean borrowed) {
        isBorrowed = borrowed;
    }
}
