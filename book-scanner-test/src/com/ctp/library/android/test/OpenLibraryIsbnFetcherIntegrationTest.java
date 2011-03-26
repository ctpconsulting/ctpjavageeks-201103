package com.ctp.library.android.test;

import junit.framework.TestCase;

import com.ctp.library.android.domain.BookInfo;
import com.ctp.library.android.service.BookFetcher;
import com.ctp.library.android.service.OpenLibraryBookFetcher;

public class OpenLibraryIsbnFetcherIntegrationTest extends TestCase {
	
	
	public void testShouldFetchBookInfoFromOpenLibraryForValidISBN() throws Exception {
		// given
		String isbn = "9780679790730";
		BookFetcher fetcher = new OpenLibraryBookFetcher();

		// when
		BookInfo bookInfo = fetcher.fetch(isbn);
		
		// then
		assertEquals("", isbn, bookInfo.getIsbn());
	}
	
}
