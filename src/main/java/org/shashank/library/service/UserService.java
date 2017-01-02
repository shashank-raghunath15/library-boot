package org.shashank.library.service;

import org.shashank.library.domain.Login;
import org.shashank.library.domain.User;

public interface UserService {

	public boolean addUser(User user, Login login);
	
}
