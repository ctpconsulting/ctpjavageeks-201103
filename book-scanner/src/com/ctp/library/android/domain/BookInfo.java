package com.ctp.library.android.domain;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.SerializedName;

public class BookInfo {

	public static final BookInfo UNKNOWN_BOOK = new BookInfo("unknown-book");
	
	private String isbn;
	
	@SerializedName("number_of_pages")
	private Integer numberOfPages;
	
	private String title;
	
	@SerializedName("publish_date")
	private String publishDate;

	private List<Publisher> publishers = new ArrayList<Publisher>();

	private List<Author> authors = new ArrayList<Author>();
	
	public BookInfo() {
	}

	public BookInfo(String isbn) {
		this.isbn = isbn;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	
	public Integer getNumberOfPages() {
		return numberOfPages;
	}

	public void setNumberOfPages(Integer numberOfPages) {
		this.numberOfPages = numberOfPages;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getPublishDate() {
		return publishDate;
	}

	public void setPublishDate(String publishDate) {
		this.publishDate = publishDate;
	}

	public List<Publisher> getPublishers() {
		return publishers;
	}

	public void setPublishers(List<Publisher> publishers) {
		this.publishers = publishers;
	}

	public List<Author> getAuthors() {
		return authors;
	}

	public void setAuthors(List<Author> authors) {
		this.authors = authors;
	}
	
	
}
