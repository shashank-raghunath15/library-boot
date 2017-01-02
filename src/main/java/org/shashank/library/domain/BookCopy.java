package org.shashank.library.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Entity
@Component()
@Scope(value = "prototype")
public class BookCopy {

	private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) long id;
	private Date addedDate;
	private @ManyToOne @JoinColumn(name = "isbn") Book book;
}
