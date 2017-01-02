package org.shashank.library.domain;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
public abstract class Subscriber extends User {

	private String department;
	private @OneToMany(mappedBy = "subscriber") Collection<Subscription> subscriptions = new ArrayList<Subscription>();
}
