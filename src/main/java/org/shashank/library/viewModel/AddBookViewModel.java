package org.shashank.library.viewModel;

import javax.validation.constraints.Min;

import org.shashank.library.domain.Book;

import lombok.Data;

@Data
public class AddBookViewModel {

	private Book book;
	@Min(1)
	private int noOfCopies;
}
