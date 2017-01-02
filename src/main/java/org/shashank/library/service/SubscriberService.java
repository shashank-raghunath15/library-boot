package org.shashank.library.service;

import java.util.Collection;

import org.shashank.library.domain.Book;
import org.shashank.library.domain.Subscriber;
import org.shashank.library.domain.Subscription;

public interface SubscriberService {

	public String subscribe(Subscription subscription, Book book);

	public Collection<Book> getAllAvailableBooks();

	public Collection<Subscription> getSubscriptions(Subscriber subscriber);

	public String returnBook(Subscription subscription);
}
