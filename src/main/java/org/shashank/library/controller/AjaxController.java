package org.shashank.library.controller;

import java.util.Collection;

import org.shashank.library.domain.Book;
import org.shashank.library.service.BookService;
import org.shashank.library.service.SubscriberService;
import org.shashank.library.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ajax")
public class AjaxController {

	private UserService userService;
	private SubscriberService subscriberService;
	private BookService bookService;

	@Autowired
	public AjaxController(UserService userService, SubscriberService subscriberService, BookService bookService) {
		this.userService = userService;
		this.subscriberService = subscriberService;
		this.bookService = bookService;
	}

	@RequestMapping("/allBooks")
	public Collection<Book> getAllBooks() {
		return bookService.getAllBooks();
	}

/*	@RequestMapping("/availability")
	public boolean getAvailability(long isbn) {
		return subscriberService.getAvailability(isbn);
	}
*/
}
