package org.shashank.library.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Entity
@Component
@Scope("prototype")
public class Subscription {

	private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) long id;
	private @ManyToOne Subscriber subscriber;
	private @ManyToOne BookCopy bookCopy;
	private Date issueDate;
	private Date expectedReturnDate;
	private Date actualReturnDate;
}
