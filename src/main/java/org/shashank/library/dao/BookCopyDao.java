package org.shashank.library.dao;

import org.shashank.library.domain.BookCopy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookCopyDao extends JpaRepository<BookCopy, Long>{

}
