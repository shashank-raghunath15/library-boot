package org.shashank.library.util;

import javax.servlet.http.HttpSession;

import org.shashank.library.domain.Login;
import org.shashank.library.domain.User;

public interface LoginUtil {

	public boolean matchPassword(Login loginForm, Login loginDb);

	public String hashPassword(String password, String salt);

	public boolean isLoggedIn(HttpSession httpSession, Class<? extends User> clazz);

}
