package com.ctp.library.android.test;

import java.util.List;

import junit.framework.TestCase;

import com.ctp.library.android.domain.Author;
import com.ctp.library.android.domain.BookInfo;
import com.ctp.library.android.service.BookFetcher;
import com.ctp.library.android.service.OpenLibraryBookFetcher;

public class OpenLibraryIsbnFetcherIntegrationTest extends TestCase {
	
	BookFetcher fetcher = new OpenLibraryBookFetcher();
	
	public void testShouldFetchBookInfoForValidISBN() throws Exception {
		// given
		String isbn = "9780321356680"; // effective java
		int expectedAuthorCount = 1;
		String expectedAuthor = "Joshua Bloch";

		// when
		BookInfo bookInfo = fetcher.fetch(isbn);
		List<Author> authors = bookInfo.getAuthors();
		
		// then
		assertEquals("", expectedAuthorCount, authors.size());
		assertEquals("", expectedAuthor, authors.get(0).getName());
	}
	
	public void testShouldHaveTitleOfRequestedBook() throws Exception {
		// given
		String isbn = "9780321356680"; // effective java
		String expectedTitle = "Effective Java(TM) Programming Language Guide (2nd Edition) (The Java Series)";

		// when
		BookInfo bookInfo = fetcher.fetch(isbn);
		
		// then
		assertEquals("", expectedTitle, bookInfo.getTitle());
	}
	
	public void testShouldHavePublishDateOfRequestedBook() throws Exception {
		// given
		String isbn = "9780321356680"; // effective java
		String expectedPublishDate = "November 9, 2007";

		// when
		BookInfo bookInfo = fetcher.fetch(isbn);
		
		// then
		assertEquals("", expectedPublishDate, bookInfo.getPublishDate());
	}
	
	public void testShouldHaveNumberOfPagesOfRequestedBook() throws Exception {
		// given
		String isbn = "9780321356680"; // effective java
		Integer expectedNumberOfPages = 256;

		// when
		BookInfo bookInfo = fetcher.fetch(isbn);
		
		// then
		assertEquals("", expectedNumberOfPages, bookInfo.getNumberOfPages());
	}
	
}
