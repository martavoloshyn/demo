package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.annotation.MyTransactional;
import com.example.demo.entity.Book;
import com.example.demo.repository.BookRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookService {

	private final BookRepository bookRepository;

	public Book getBookById(Long bookId) {
		return bookRepository.findById(bookId).get();
	}

	public List<Book> getAllBooks() {
		return bookRepository.findAll();
	}

	public void createBook(Book book) {
		bookRepository.save(book);
	}

	@MyTransactional
	public void createBooks(List<Book> books) {
		for (Book book: books) {
			bookRepository.save(book);
		}
	}
}
