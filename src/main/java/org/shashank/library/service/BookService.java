package org.shashank.library.service;

import java.util.Collection;

import org.shashank.library.domain.Book;

public interface BookService {

	public void addBooks(Book book, int noOfCopies);

	public Collection<Book> getAllBooks();
	
	public Book getBook(long isbn);
}
