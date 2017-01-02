package org.shashank.library.domain;

import javax.persistence.Embeddable;

import lombok.Data;

@Data
@Embeddable
public class Author {

	private String name;
}
