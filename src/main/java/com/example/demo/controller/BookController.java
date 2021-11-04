package com.example.demo.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.model.Book;
import com.example.demo.repository.BookRepository;

@RestController
public class BookController {

	private List<Book> books = new ArrayList<>();

	public BookController() {
		Book b1 = new Book();
		b1.setTitle("Harry Potter");
		b1.setAuthor("J.K. Rowling");
		b1.setIsbn("123456");

		books.add(b1);

		Book b2 = new Book();
		b2.setTitle("Harry Potter2");
		b2.setAuthor("J.K. Rowling");
		b2.setIsbn("654321");

		books.add(b2);
	}
	@Autowired
	private BookRepository bookRepository;

	@GetMapping(value = "/books")
	public ResponseEntity<Object> getBooks() {
		return ResponseEntity.ok(bookRepository.findAll());
	}

	@GetMapping(value = "/books/{id}")
	public ResponseEntity<Object> getBook(@PathVariable int id) {
		return ResponseEntity.ok(bookRepository.findById(id));
	}

	@DeleteMapping(value = "/books/{id}")
	public ResponseEntity<Object> removeBook(@PathVariable int id) {
		books.remove(id);
		return ResponseEntity.noContent().build();
	}

	@PostMapping(value = "/books")
	public ResponseEntity<Object> addBook(@RequestBody Book book) {
		books.add(book);
		int newBookId = books.size()-1;

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newBookId).toUri();

		return ResponseEntity.created(location).build();
	}

	@PutMapping(value = "/books/{id}")
	public ResponseEntity<Object> updateBook(@PathVariable int id, @RequestBody Book book) {
		books.set(id, book);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();

		return ResponseEntity.created(location).build();
	}

	@PatchMapping(value = "/books/{id}")
	public ResponseEntity<Object> updateBookPartially(@PathVariable int id, @RequestBody Book book) {
		Book existingBook = books.get(id);
		if (!book.getTitle().isEmpty()){
			existingBook.setTitle(book.getTitle());
		}
		if (!book.getAuthor().isEmpty()){
			existingBook.setAuthor(book.getAuthor());
		}
		if (!book.getIsbn().isEmpty()){
			existingBook.setIsbn(book.getIsbn());
		}
		books.set(id, existingBook);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
		return ResponseEntity.created(location).build();
	}
}
