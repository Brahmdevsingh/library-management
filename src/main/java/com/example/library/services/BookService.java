package com.example.library.services;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.library.entity.Books;
import com.example.library.repository.BookRepository;


@Service
public class BookService {
	@Autowired
	BookRepository bookRepository;
	
	public void saveBook(Books books) {
		bookRepository.save(books);
	}

	public void updateBook(Books books) throws IllegalArgumentException, IllegalAccessException{
		Optional<Books> savedBooks = bookRepository.findById(books.getBookId());
		if(savedBooks.isPresent())
		{
			nullCheck(savedBooks.get(),books);
			bookRepository.save(savedBooks.get());
			//existingBooks.setBookGenre(books.getBookGenre());
			//existingBooks.setBookName(books.getBookName());
			//Books existingBooks = savedBooks.get();
		}
		
	}

	private void nullCheck(Books savedBook, Books newBook) throws IllegalArgumentException, IllegalAccessException {
		
		Field fields[] = Books.class.getDeclaredFields();
		
		for(Field field:fields) {
			field.setAccessible(true);
			if(field.get(newBook)!=null) {
				field.set(savedBook, field.get(newBook));
			}
		}
	}

	public List<String> getAllGenre() {
		List<Books> allBooks = bookRepository.findAll();
		List<String> allGenre = new ArrayList<>();
		
		for(Books book:allBooks) {
			String genre = book.getBookGenre();
			
			if(!allGenre.contains(genre)) {
				allGenre.add(genre);
			}
			
		}
		return allGenre;
	}

	public void deleteMyBook(Long bookId) {
		Optional<Books> deleteBooks = bookRepository.findById(bookId);
		
		if(deleteBooks.isPresent())
		{
			bookRepository.delete(deleteBooks.get());
		}
		
	}

	public List<Books> getAllGenreBooks(String bookGenre) {
		 List<Books> allBooks = bookRepository.findBybookGenre(bookGenre); 
		 return allBooks;
	}

	public List<Books> getAllBooks() {
		// TODO Auto-generated method stub
		return bookRepository.findAll();
	}
}
