package org.shashank.library.domain;

import javax.persistence.Embeddable;

import lombok.Data;

@Data
@Embeddable
public class Address {

	private String street;
	private String city;
	private String state;
	private int pincode;

}
