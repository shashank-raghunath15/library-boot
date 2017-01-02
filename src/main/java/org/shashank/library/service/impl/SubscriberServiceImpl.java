package org.shashank.library.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.shashank.library.dao.BookDao;
import org.shashank.library.dao.SubscriberDao;
import org.shashank.library.dao.SubscriptionDao;
import org.shashank.library.domain.Book;
import org.shashank.library.domain.BookCopy;
import org.shashank.library.domain.Professor;
import org.shashank.library.domain.Student;
import org.shashank.library.domain.Subscriber;
import org.shashank.library.domain.Subscription;
import org.shashank.library.service.SubscriberService;
import org.shashank.library.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubscriberServiceImpl implements SubscriberService {

	private static final int STUDENT_ISSUE_DAYS_DEFAULT = 15;
	private static final int STUDENT_ISSUE_COUNT = 3;
	private static final int PROFESSOR_ISSUE_DAYS_DEFAULT = 30;
	private static final int PROFESSOR_ISSUE_COUNT = 5;

	private SubscriptionDao subscriptionDao;
	private DateUtil dateUtil;
	private BookDao bookDao;
	private SubscriberDao subscriberDao;

	@Autowired
	public SubscriberServiceImpl(SubscriptionDao subscriptionDao, DateUtil dateUtil, BookDao bookDao,
			SubscriberDao subscriberDao) {
		this.subscriptionDao = subscriptionDao;
		this.dateUtil = dateUtil;
		this.bookDao = bookDao;
		this.subscriberDao = subscriberDao;
	}

	public String subscribe(Subscription subscription, Book book) {

		if (isIssuesLimitReached(subscription.getSubscriber()))
			return "Max number of books subscribed already!";
		subscription = buildDates(subscription);
		subscription.setBookCopy(getAvailableBookCopy(book));
		subscriptionDao.save(subscription);
		return "Book Issued Successfully";
	}

	private BookCopy getAvailableBookCopy(Book book) {

		book = bookDao.getOne(book.getIsbn());
		Collection<Subscription> subscriptions = subscriptionDao.findByIssueDateIsNotNullAndActualReturnDateIsNull();

		for (Subscription subscription : subscriptions) {
			if (subscription.getBookCopy().getBook().getIsbn() == book.getIsbn()) {
				book.getBookCopies().remove(subscription.getBookCopy());
			}
		}
		return ((List<BookCopy>) book.getBookCopies()).get(0);
	}

	@Override
	public Collection<Book> getAllAvailableBooks() {

		Collection<Book> books = bookDao.findAll();
		Collection<Subscription> subscriptions = subscriptionDao.findByActualReturnDateIsNull();
		Collection<Book> booksAvailable = new ArrayList<>();
		for (Book book : books) {
			int totalNoOfCopies = book.getBookCopies().size();
			int notReturnedCount = 0;
			for (Subscription subscription : subscriptions) {

				if (subscription.getBookCopy().getBook().getIsbn() == book.getIsbn()) {
					notReturnedCount++;
				}
			}
			if (notReturnedCount < totalNoOfCopies) {
				booksAvailable.add(book);
			}
			notReturnedCount = 0;
		}
		return booksAvailable;
	}

	@Override
	public Collection<Subscription> getSubscriptions(Subscriber subscriber) {
		subscriber = subscriberDao.getOne(subscriber.getId());
		return subscriber.getSubscriptions();
	}

	@Override
	public String returnBook(Subscription subscription) {
		subscription = subscriptionDao.getOne(subscription.getId());
		subscription.setActualReturnDate(new Date());
		subscriptionDao.save(subscription);
		return "Book Returned Successfully";
	}

	private boolean isIssuesLimitReached(Subscriber subscriber) {
		int count = 0;
		subscriber.setSubscriptions(subscriberDao.getOne(subscriber.getId()).getSubscriptions());
		for (Subscription subscription : subscriber.getSubscriptions()) {
			if (subscription.getActualReturnDate() == null) {
				count++;
			}
		}
		if (subscriber instanceof Student && count >= STUDENT_ISSUE_COUNT) {
			return true;
		}
		if (subscriber instanceof Professor && count >= PROFESSOR_ISSUE_COUNT) {
			return true;
		}
		return false;
	}

	private Subscription buildDates(Subscription subscription) {
		if (subscription.getIssueDate() == null)
			subscription.setIssueDate(new Date());
		if (subscription.getExpectedReturnDate() == null)
			subscription.setExpectedReturnDate(
					dateUtil.addDays(subscription.getIssueDate(), subscription.getSubscriber() instanceof Student
							? STUDENT_ISSUE_DAYS_DEFAULT : PROFESSOR_ISSUE_DAYS_DEFAULT));
		return subscription;
	}

}
