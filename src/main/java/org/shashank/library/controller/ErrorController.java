package org.shashank.library.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ErrorController {

	@ExceptionHandler
	public ModelAndView exception(Exception ex){
		ModelAndView modelAndView = new ModelAndView("error");
		modelAndView.addObject("error", ex.getStackTrace().toString());
		return modelAndView;
	}
	
	/*@RequestMapping("/error")
	public ModelAndView error(ModelAndView modelAndView){
		modelAndView.setViewName("error");
	}*/
}
