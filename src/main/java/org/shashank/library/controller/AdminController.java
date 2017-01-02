package org.shashank.library.controller;

import javax.servlet.http.HttpSession;

import org.shashank.library.domain.Admin;
import org.shashank.library.domain.Book;
import org.shashank.library.domain.Login;
import org.shashank.library.domain.Professor;
import org.shashank.library.domain.Student;
import org.shashank.library.service.BookService;
import org.shashank.library.service.UserService;
import org.shashank.library.util.LoginUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/admin")
public class AdminController {

	private UserService userService;
	private BookService bookService;
	private LoginUtil loginUtil;

	@Autowired
	public AdminController(UserService userService, BookService bookService, LoginUtil loginUtil) {
		this.userService = userService;
		this.bookService = bookService;
		this.loginUtil = loginUtil;
	}

	@RequestMapping
	public ModelAndView admin(HttpSession httpSession) {
		if (loginUtil.isLoggedIn(httpSession, Admin.class)) {
			ModelAndView modelAndView = new ModelAndView("admin");
			return modelAndView;
		}
		return new ModelAndView(new RedirectView("/"));
	}

	@RequestMapping("/addStudent")
	public ModelAndView student(HttpSession httpSession) {
		if (loginUtil.isLoggedIn(httpSession, Admin.class)) {
			ModelAndView modelAndView = new ModelAndView("addStudent");
			return modelAndView;
		}
		return new ModelAndView(new RedirectView("/"));
	}

	@RequestMapping("/addProfessor")
	public ModelAndView professor(HttpSession httpSession) {
		if (loginUtil.isLoggedIn(httpSession, Admin.class)) {
			ModelAndView modelAndView = new ModelAndView("addProfessor");
			return modelAndView;
		}
		return new ModelAndView(new RedirectView("/"));
	}

	@RequestMapping("/addBook")
	public ModelAndView book(HttpSession httpSession) {
		if (loginUtil.isLoggedIn(httpSession, Admin.class)) {
			ModelAndView modelAndView = new ModelAndView("addBook");
			return modelAndView;
		}
		return new ModelAndView(new RedirectView("/"));
	}

	@RequestMapping(value = "/addStudent/addStudentDo", method = RequestMethod.POST)
	public ModelAndView addStudent(Student student, Login login, RedirectAttributes redirectAttributes,
			HttpSession httpSession) {
		if (loginUtil.isLoggedIn(httpSession, Admin.class)) {
			if (userService.addUser(student, login)) {
				ModelAndView modelAndView = new ModelAndView(new RedirectView("../addStudent", false));
				redirectAttributes.addFlashAttribute("msg",
						"Student " + student.getFirstName() + " " + student.getLastName() + "added successfully");
				return modelAndView;
			}
			ModelAndView modelAndView = new ModelAndView(new RedirectView("../addStudent", false));
			redirectAttributes.addFlashAttribute("msg", "Email already exists");
			return modelAndView;
		}
		return new ModelAndView(new RedirectView("/"));
	}

	@RequestMapping(value = "/addProfessor/addProfessorDo", method = RequestMethod.POST)
	public ModelAndView addProfessor(Professor professor, Login login, RedirectAttributes redirectAttributes,
			HttpSession httpSession) {
		if (loginUtil.isLoggedIn(httpSession, Admin.class)) {
			if (userService.addUser(professor, login)) {
				ModelAndView modelAndView = new ModelAndView(new RedirectView("../addProfessor", false));
				redirectAttributes.addFlashAttribute("msg",
						"Professor " + professor.getFirstName() + " " + professor.getLastName() + "added successfully");
				return modelAndView;
			}
			ModelAndView modelAndView = new ModelAndView(new RedirectView("../addProfessor", false));
			redirectAttributes.addFlashAttribute("msg", "Email already exists");
			return modelAndView;
		}
		return new ModelAndView(new RedirectView("/"));
	}

	@RequestMapping(value = "/addBook/addBookDo", method = RequestMethod.POST)
	public ModelAndView addBook(Book book, int noOfCopies, RedirectAttributes redirectAttributes,
			HttpSession httpSession) {
		if (loginUtil.isLoggedIn(httpSession, Admin.class)) {

			bookService.addBooks(book, noOfCopies);
			ModelAndView modelAndView = new ModelAndView(new RedirectView("../addBook", false));
			redirectAttributes.addFlashAttribute("msg", "Books added successfully");
			return modelAndView;
		}
		return new ModelAndView(new RedirectView("/"));
	}
}
