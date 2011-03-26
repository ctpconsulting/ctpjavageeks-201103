package com.ctp.library.android.test;

import android.util.Log;

import com.ctp.library.android.domain.BookInfo;
import com.ctp.library.android.service.OpenLibraryBookFetcher;

import junit.framework.TestCase;

public class OpenLibraryIsbnFetcherIntegrationTest extends TestCase {
	
	
	public void testShouldFetchBookInfoFromOpenLibraryForValidISBN() throws Exception {
		// given
		String isbn = "9780679790730";
		OpenLibraryBookFetcher fetcher = new OpenLibraryBookFetcher();

		// when
		BookInfo bookInfo = fetcher.fetch(isbn);
		
		// then
		Log.i("TEST", "Test message info");
		Log.d("TEST", "Test message debug");
		assertEquals("", isbn, bookInfo.getIsbn());
	}
	
}
