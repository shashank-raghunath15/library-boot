package org.shashank.library.service.impl;

import org.shashank.library.dao.LoginDao;
import org.shashank.library.domain.Login;
import org.shashank.library.service.LoginService;
import org.shashank.library.util.LoginUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

	private LoginDao loginDao;
	private LoginUtil loginUtil;

	@Autowired
	public LoginServiceImpl(LoginDao loginDao, LoginUtil loginUtil) {
		this.loginDao = loginDao;
		this.loginUtil = loginUtil;
	}

	@Override
	public Login checkLogin(Login login) {

		if (loginDao.exists(login.getEmail())) {
			Login login2 = loginDao.getOne(login.getEmail());
			if (loginUtil.matchPassword(login, login2)) {
				return login2;
			}
		}
		return null;
	}

}
