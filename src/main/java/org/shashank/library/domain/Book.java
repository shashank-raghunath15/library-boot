package org.shashank.library.domain;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
public class Book {

	private @Id long isbn;
	private @NotEmpty String title;
	private @ElementCollection Collection<Author> authors = new ArrayList<Author>();
	private @NotEmpty String publisher;
	private @NotEmpty String language;
	private @ElementCollection Collection<Genre> genres = new ArrayList<Genre>();
	private @JsonIgnore @OneToMany(mappedBy = "book") Collection<BookCopy> bookCopies = new ArrayList<BookCopy>();
}
