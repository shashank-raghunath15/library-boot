package org.shashank.library.controller;

import javax.servlet.http.HttpSession;

import org.shashank.library.domain.Book;
import org.shashank.library.domain.Login;
import org.shashank.library.domain.Subscriber;
import org.shashank.library.domain.Subscription;
import org.shashank.library.service.BookService;
import org.shashank.library.service.SubscriberService;
import org.shashank.library.util.LoginUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/subscriber")
public class SubscriberController {

	private LoginUtil loginUtil;
	private SubscriberService subscriberService;
	private BookService bookService;
	private ApplicationContext context;

	@Autowired
	public SubscriberController(LoginUtil loginUtil, SubscriberService subscriberService, BookService bookService,
			ApplicationContext context) {
		this.loginUtil = loginUtil;
		this.subscriberService = subscriberService;
		this.bookService = bookService;
		this.context = context;
	}

	@RequestMapping
	public ModelAndView subscriber(HttpSession httpSession) {
		ModelAndView modelAndView = new ModelAndView();
		if (loginUtil.isLoggedIn(httpSession, Subscriber.class)) {
			modelAndView.setViewName("subscriber");
			return modelAndView;
		}
		modelAndView.setView(new RedirectView("/login"));
		return modelAndView;
	}

	@RequestMapping("/subscribe")
	public ModelAndView subscribe(HttpSession httpSession) {
		ModelAndView modelAndView = new ModelAndView();
		if (loginUtil.isLoggedIn(httpSession, Subscriber.class)) {
			modelAndView.setViewName("subscribe");
			modelAndView.addObject("books", subscriberService.getAllAvailableBooks());
			return modelAndView;
		}
		modelAndView.setView(new RedirectView("/login"));
		return modelAndView;
	}

	@RequestMapping("/returnBook")
	public ModelAndView returnBook(HttpSession httpSession) {
		ModelAndView modelAndView = new ModelAndView();
		if (loginUtil.isLoggedIn(httpSession, Subscriber.class)) {
			modelAndView.setViewName("returnBook");
			modelAndView.addObject("subscriptions",
					subscriberService.getSubscriptions((Subscriber) getSubscriber(httpSession)));
			return modelAndView;
		}
		modelAndView.setView(new RedirectView("/login"));
		return modelAndView;
	}

	@RequestMapping("/subscribe/subscribeDo")
	@PostMapping
	public ModelAndView subscribeDo(HttpSession httpSession, Subscription subscription, Book book,
			RedirectAttributes redirectAttributes) {
		ModelAndView modelAndView = new ModelAndView();
		if (loginUtil.isLoggedIn(httpSession, Subscriber.class)) {
			modelAndView.setView(new RedirectView("../../subscriber", false));
			subscription.setSubscriber((Subscriber) ((Login) httpSession.getAttribute("login")).getUser());
			redirectAttributes.addFlashAttribute("msg", subscriberService.subscribe(subscription, book));
			return modelAndView;
		}
		modelAndView.setView(new RedirectView("/login"));
		return modelAndView;
	}

	@RequestMapping("/returnBook/returnBookDo")
	@PostMapping
	public ModelAndView returnBookDo(HttpSession httpSession, Subscription subscription,
			RedirectAttributes redirectAttributes) {
		ModelAndView modelAndView = new ModelAndView();
		if (loginUtil.isLoggedIn(httpSession, Subscriber.class)) {
			modelAndView.setView(new RedirectView("../returnBook", false));
			subscription.setSubscriber((Subscriber) ((Login) httpSession.getAttribute("login")).getUser());
			redirectAttributes.addFlashAttribute("msg", subscriberService.returnBook(subscription));
			return modelAndView;
		}
		modelAndView.setView(new RedirectView("/login"));
		return modelAndView;
	}

	private Subscriber getSubscriber(HttpSession httpSession) {
		return (Subscriber) ((Login) httpSession.getAttribute("login")).getUser();
	}
}
