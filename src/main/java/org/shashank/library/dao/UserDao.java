package org.shashank.library.dao;

import org.shashank.library.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Long>{

}
