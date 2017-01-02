package org.shashank.library.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

	/*@Autowired
	private UserService userService;*/

	/*@RequestMapping(value = "/addUser", method = RequestMethod.POST)
	public ModelAndView addUser(Login login, User user) {

		ModelAndView modelAndView = new ModelAndView();
		if (userService.addUser(login, user)) {
			modelAndView.setStatus(HttpStatus.UNPROCESSABLE_ENTITY);
			return modelAndView;
		}
		modelAndView.setStatus(HttpStatus.CREATED);
		return modelAndView;
	}*/
}
