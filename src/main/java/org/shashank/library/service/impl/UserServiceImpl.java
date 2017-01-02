package org.shashank.library.service.impl;

import org.shashank.library.dao.LoginDao;
import org.shashank.library.dao.UserDao;
import org.shashank.library.domain.Login;
import org.shashank.library.domain.User;
import org.shashank.library.service.UserService;
import org.shashank.library.util.LoginUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

	private UserDao userDao;
	private LoginDao loginDao;
	private LoginUtil loginUtil;

	@Autowired
	public UserServiceImpl(UserDao userDao, LoginDao loginDao, LoginUtil loginUtil) {
		this.userDao = userDao;
		this.loginDao = loginDao;
		this.loginUtil = loginUtil;
	}

	@Override
	public boolean addUser(User user, Login login) {
		if (loginDao.exists(login.getEmail()))
			return false;
		login.setUser(userDao.save(user));
		login.setSalt(BCrypt.gensalt());
		login.setPassword(loginUtil.hashPassword("password", login.getSalt()));
		loginDao.save(login);
		return true;
	}
}
