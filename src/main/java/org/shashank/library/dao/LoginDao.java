package org.shashank.library.dao;

import org.shashank.library.domain.Login;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginDao extends JpaRepository<Login, String>{

}
