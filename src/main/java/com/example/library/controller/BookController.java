package com.example.library.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

import com.example.library.entity.Books;
import com.example.library.services.BookService;

@RestController
@CrossOrigin("*")
public class BookController {
	
	@Autowired
	BookService bookService;
	
	@PostMapping("/saveBook")
	public void saveBook(@RequestBody Books books) {
		bookService.saveBook(books);
	}

	@PutMapping("/update")
	@ResponseStatus(HttpStatus.CREATED)
	public void updateBook(@RequestBody Books books) throws IllegalArgumentException, IllegalAccessException{
		bookService.updateBook(books);
	}
	
	@GetMapping("/getAllGenre")
	public List<String> getGenre(){
		return bookService.getAllGenre();
	}
	
	@DeleteMapping("/deleteBooks/{bookId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteBook(@PathVariable Long bookId) {
		System.out.println(bookId);
		bookService.deleteMyBook(bookId);
	}
	
	@GetMapping("/booksByGenre/{bookGenre}")
	public List<Books> getAllGenreBooks(@PathVariable String bookGenre){
		return bookService.getAllGenreBooks(bookGenre);
	}
	
	@GetMapping("/getAllBooks")
	public List<Books> getAllBooks(){
		return bookService.getAllBooks();
	}

}
