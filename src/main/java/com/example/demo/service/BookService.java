package com.example.demo.service;

import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.RecordNotFoundException;
import com.example.demo.model.Book;
import com.example.demo.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    BookRepository bookRepository;

    public Iterable<Book> getBooks(String title) {
        if (title.isEmpty()) {
            return bookRepository.findAll();
        }
        else {
            return bookRepository.findAllByTitleContainingIgnoreCase(title);
        }
    }

    public Book getBook(int id) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if (optionalBook.isPresent()) {
            return optionalBook.get();
        } else {
            throw new RecordNotFoundException("No book found for the provided ID.");
        }
    }

    public void deleteBook(int id) {
        if (bookRepository.existsById(id)) {
            bookRepository.deleteById(id);
        } else {
            throw new RecordNotFoundException("ID does not exist.");
        }
    }

    public int addBook(Book book) {
        String isbn = book.getIsbn();
        List<Book> books = (List<Book>)bookRepository.findAllByIsbn(isbn);
        if (books.size() > 0) {
            throw new BadRequestException("Isbn already exists.");
        }
        Book newBook = bookRepository.save(book);
        return newBook.getId();
    }

    public void updateBook(int id, Book book) {
        Optional<Book> optionalBook = bookRepository.findById(id);

        if (optionalBook.isPresent()) {
            Book storedBook = optionalBook.get();

            book.setId(storedBook.getId());
            bookRepository.save(book);
        }
        else {
            throw new RecordNotFoundException("ID does not exist!!!");
        }
    }

    public void partialUpdateBook(int id, Book book) {
        Optional<Book> optionalBook = bookRepository.findById(id);

        if (optionalBook.isPresent()) {
            Book storedBook = bookRepository.findById(id).orElse(null);

            if (book.getTitle() != null && !book.getTitle().isEmpty()) {
                storedBook.setTitle(book.getTitle());
            }
            if (book.getAuthor() != null && !book.getAuthor().isEmpty()) {
                storedBook.setAuthor(book.getAuthor());
            }
            if (book.getIsbn() != null && !book.getIsbn().isEmpty()) {
                storedBook.setIsbn(book.getIsbn());
            }
            bookRepository.save(storedBook);

        } else {
            throw new RecordNotFoundException("ID does not exist!!!");
        }
    }
}
