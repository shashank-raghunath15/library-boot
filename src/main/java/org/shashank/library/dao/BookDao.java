package org.shashank.library.dao;

import org.shashank.library.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookDao extends JpaRepository<Book, Long>{

}
