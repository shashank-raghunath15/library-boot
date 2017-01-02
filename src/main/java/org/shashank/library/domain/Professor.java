package org.shashank.library.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
public class Professor extends Subscriber {

	private @Column(unique=true) long employeeId;
}
