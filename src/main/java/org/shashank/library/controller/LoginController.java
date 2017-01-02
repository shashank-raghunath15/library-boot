package org.shashank.library.controller;

import javax.servlet.http.HttpSession;

import org.shashank.library.domain.Login;
import org.shashank.library.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/login")
public class LoginController {

	private LoginService loginService;

	@Autowired
	public LoginController(LoginService loginService) {
		this.loginService = loginService;
	}

	@RequestMapping
	public String login() {
		return "login";
	}

	@RequestMapping(value = "/checkLogin", method = RequestMethod.POST)
	public ModelAndView checkLogin(Login login, HttpSession session, RedirectAttributes redirectAttributes) {

		Login login2 = loginService.checkLogin(login);
		if (login2 != null) {
			ModelAndView modelAndView = new ModelAndView();
			session.setAttribute("login", login2);
			switch (login2.getUser().getClass().getSimpleName()) {
			case "Admin":
				modelAndView.setView(new RedirectView("/admin"));
				return modelAndView;
			case "Student":
				modelAndView.setView(new RedirectView("/subscriber"));
				return modelAndView;
			case "Professor":
				modelAndView.setView(new RedirectView("/subscriber"));
				return modelAndView;
			default:
				modelAndView.setView(new RedirectView("/login"));
				redirectAttributes.addFlashAttribute("error", "Login Error!");
				return modelAndView;
			}
		}
		ModelAndView modelAndView = new ModelAndView(new RedirectView("/login"));
		redirectAttributes.addFlashAttribute("error", "Username and or Password is incorrect");
		return modelAndView;

	}

}
