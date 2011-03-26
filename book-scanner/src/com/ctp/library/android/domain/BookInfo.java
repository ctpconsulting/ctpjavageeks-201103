package com.ctp.library.android.domain;

public class BookInfo {

	public static final BookInfo UNKNOWN_BOOK = new BookInfo("fake-isbn");
	
	private final String isbn;

	public BookInfo(String isbn) {
		super();
		this.isbn = isbn;
	}

	public String getIsbn() {
		return isbn;
	}
	
}
