package com.example.demo.resource;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Book;
import com.example.demo.service.BookService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("books")
@RequiredArgsConstructor
public class BookResource {

	private final BookService bookService;

	@GetMapping("{bookId}")
	public Book getBook(@PathVariable Long bookId) {
		return bookService.getBookById(bookId);
	}

	@GetMapping
	public List<Book> getAllBooks() {
		return bookService.getAllBooks();
	}

	@PostMapping
	public void createBook(@Valid @RequestBody Book book) {
		bookService.createBook(book);
	}

	@PostMapping("list")
	public void createBooks(@RequestBody List<Book> books) {
		bookService.createBooks(books);
	}
}
