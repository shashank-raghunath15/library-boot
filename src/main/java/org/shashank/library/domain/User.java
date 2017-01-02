package org.shashank.library.domain;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;

import lombok.Data;

@Data
@Entity
@Inheritance
public abstract class User {

	private @Id @GeneratedValue(strategy=GenerationType.IDENTITY) long id;
	private String firstName;
	private String lastName;
	private @Embedded Address address;

}
