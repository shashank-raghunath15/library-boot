package org.shashank.library.dao;

import java.util.Collection;

import org.shashank.library.domain.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriptionDao extends JpaRepository<Subscription, Long> {
	public Collection<Subscription> findByActualReturnDateIsNull();
	public Collection<Subscription> findByIssueDateIsNotNullAndActualReturnDateIsNull();
}
