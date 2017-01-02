package org.shashank.library.service.impl;

import java.util.Collection;
import java.util.Date;

import org.shashank.library.dao.BookCopyDao;
import org.shashank.library.dao.BookDao;
import org.shashank.library.domain.Book;
import org.shashank.library.domain.BookCopy;
import org.shashank.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {

	private BookDao bookDao;
	private BookCopyDao bookCopyDao;
	private ApplicationContext context;

	@Autowired
	public BookServiceImpl(BookDao bookDao, BookCopyDao bookCopyDao, ApplicationContext context) {
		this.bookDao = bookDao;
		this.bookCopyDao = bookCopyDao;
		this.context = context;
	}

	@Override
	public void addBooks(Book book, int noOfCopies) {
		if (!bookDao.exists(book.getIsbn()))
			bookDao.save(book);
		else
			book = bookDao.getOne(book.getIsbn());
		int i = 1;
		while (i <= noOfCopies) {
			BookCopy bookCopy = context.getBean(BookCopy.class);
			bookCopy.setBook(book);
			bookCopy.setAddedDate(new Date());
			bookCopyDao.save(bookCopy);
			if (i < 100) {
				if (i % 20 == 0)
					bookCopyDao.flush();
			} else if (i % 100 == 0)
				bookCopyDao.flush();
			i++;
		}
		bookCopyDao.flush();
	}

	@Override
	public Collection<Book> getAllBooks() {
		return bookDao.findAll();
	}

	@Override
	public Book getBook(long isbn) {
		return bookDao.getOne(isbn);
	}

}
