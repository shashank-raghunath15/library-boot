package org.shashank.library.dao;

import org.shashank.library.domain.Subscriber;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriberDao extends JpaRepository<Subscriber, Long> {

}
