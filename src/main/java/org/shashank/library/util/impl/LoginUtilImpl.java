package org.shashank.library.util.impl;

import javax.servlet.http.HttpSession;

import org.shashank.library.domain.Admin;
import org.shashank.library.domain.Login;
import org.shashank.library.domain.Subscriber;
import org.shashank.library.domain.User;
import org.shashank.library.util.LoginUtil;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public final class LoginUtilImpl implements LoginUtil {

	public boolean matchPassword(Login loginForm, Login loginDb) {
		if (loginDb.getPassword().equals(BCrypt.hashpw(loginForm.getPassword(), loginDb.getSalt()))) {
			return true;
		}
		return false;
	}

	public String hashPassword(String password, String salt) {
		return BCrypt.hashpw(password, salt);
	}

	@Override
	public boolean isLoggedIn(HttpSession httpSession, Class<? extends User> clazz) {

		Login login = (Login) httpSession.getAttribute("login");
		if (login != null) {
			if (login.getUser().getClass().getSimpleName().equals(clazz.getSimpleName())) {
				return true;
			} else if (login.getUser() instanceof Subscriber) {
				if (clazz.getSimpleName().equals(Admin.class.getSimpleName()))
					return false;
				return true;
			}
		}
		return false;
	}

}
