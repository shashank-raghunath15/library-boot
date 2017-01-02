package org.shashank.library.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.Data;

@Data
@Entity
public class Login {

	private String password;
	private @Id String email;
	private String salt;
	private @OneToOne User user;

}
