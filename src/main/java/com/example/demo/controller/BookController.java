package com.example.demo.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.example.demo.service.BookService;
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

	@Autowired
	private BookService bookService;

	@GetMapping(value = "/books")
	public ResponseEntity<Object> getBooks() {
		return ResponseEntity.ok(bookService.getBooks());
	}

	@GetMapping(value = "/books/{id}")
	public ResponseEntity<Object> getBook(@PathVariable int id) {
		return ResponseEntity.ok(bookService.getBook(id));
	}

	@DeleteMapping(value = "/books/{id}")
	public ResponseEntity<Object> removeBook(@PathVariable int id) {
		bookService.deleteBook(id);
		return ResponseEntity.noContent().build();
	}

	@PostMapping(value = "/books")
	public ResponseEntity<Object> addBook(@RequestBody Book book) {
		int newBookId = bookService.addBook(book);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newBookId).toUri();

		return ResponseEntity.created(location).build();
	}

	@PutMapping(value = "/books/{id}")
	public ResponseEntity<Object> updateBook(@PathVariable int id, @RequestBody Book book) {
		bookService.updateBook(id, book);

		return ResponseEntity.noContent().build();
	}

	@PatchMapping(value = "/books/{id}")
	public ResponseEntity<Object> updateBookPartially(@PathVariable int id, @RequestBody Book book) {
		bookService.partialUpdateBook(id, book);

		return ResponseEntity.noContent().build();
	}
}
