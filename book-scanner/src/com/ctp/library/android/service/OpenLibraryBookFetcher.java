package com.ctp.library.android.service;

import org.apache.http.HttpResponse;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.ctp.library.android.domain.BookInfo;

public class OpenLibraryBookFetcher implements BookFetcher {

	private static final String OPEN_LIBRARY_RETRIEVE_BY_ISBN_IN_JSON_URL = "http://openlibrary.org/api/books?bibkeys=ISBN:%s&format=json";
	
	@Override
	public BookInfo fetch(String isbn) {
		BookInfo result = BookInfo.UNKNOWN_BOOK;
		HttpGet get = new HttpGet(String.format(OPEN_LIBRARY_RETRIEVE_BY_ISBN_IN_JSON_URL, isbn));
		DefaultHttpClient client = new DefaultHttpClient();
		try {
			HttpResponse response = client.execute(get);
			ResponseHandler<String> responseHandler = new BasicResponseHandler();
			if (200 == response.getStatusLine().getStatusCode()) {
				result = BookInfoResponse.fromJSON(responseHandler.handleResponse(response));
			}
		} catch (Exception e) {
			Log.d("OpenLibraryBookFetcher", "Exception has been thrown", e);
		}
		return result ;
	}
	
	
	private static final class BookInfoResponse extends JSONObject {

		private final String isbn;

		public BookInfoResponse(String response) throws JSONException {
			super(response);
			this.isbn = extractISBN(); 
		}

		private String extractISBN() {
			String isbnKey = (String) keys().next();
			return isbnKey.substring(isbnKey.indexOf(":") + 1);
		}

		public static BookInfo fromJSON(String json) throws JSONException {
			BookInfoResponse bookInfoResponse = new BookInfoResponse(json);
			return new BookInfo(bookInfoResponse.isbn);
		}

	}

}
