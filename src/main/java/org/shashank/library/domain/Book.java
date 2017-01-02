package org.shashank.library.domain;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
public class Book {

	private @Id long isbn;
	private String title;
	private @ElementCollection Collection<Author> authors = new ArrayList<Author>();
	private String publisher;
	private String language;
	private @ElementCollection Collection<Genre> genres = new ArrayList<Genre>();
	private @JsonIgnore @OneToMany(mappedBy = "book") Collection<BookCopy> bookCopies = new ArrayList<BookCopy>();
}
