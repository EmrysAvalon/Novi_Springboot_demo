package com.example.demo.dto;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class BookRequestDto {

    @NotBlank
    @Size(min= 1, max=100)
    private String title;

    @NotBlank
    @Size(min= 1, max=100)
    private String author;

    @NotBlank
    @Size(min= 10, max=20)
    private String isbn;


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

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
}
