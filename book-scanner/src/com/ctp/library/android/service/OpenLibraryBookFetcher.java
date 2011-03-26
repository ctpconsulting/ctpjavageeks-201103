package com.ctp.library.android.service;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.util.Log;

import com.ctp.library.android.domain.BookInfo;

public class OpenLibraryBookFetcher implements BookFetcher {

	@Override
	public BookInfo fetch(String isbn) {
		BookInfo result = BookInfo.UNKNOWN_BOOK;
		HttpGet get = new HttpGet("http://openlibrary.org/api/books?bibkeys=ISBN:9780679790730");
		DefaultHttpClient client = new DefaultHttpClient();
		try {
			HttpResponse response = client.execute(get);
			result = new BookInfo(String.valueOf(response.getStatusLine().getStatusCode()));
		} catch (Exception e) {
			Log.d("OpenLibraryBookFetcher", "Exception has been thrown", e);
		}
		return result ;
	}
	

}
