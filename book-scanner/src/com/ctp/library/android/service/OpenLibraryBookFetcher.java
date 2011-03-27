package com.ctp.library.android.service;

import java.lang.reflect.Type;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.HttpResponse;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;

import android.util.Log;

import com.ctp.library.android.domain.BookInfo;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class OpenLibraryBookFetcher implements BookFetcher {

	private static final String OPEN_LIBRARY_RETRIEVE_BY_ISBN_IN_JSON_URL = "http://openlibrary.org/api/books?bibkeys=ISBN:%s&format=json&jscmd=data";
	
	@Override
	public BookInfo fetch(String isbn) {
		BookInfo result = BookInfo.UNKNOWN_BOOK;
		HttpGet get = new HttpGet(String.format(OPEN_LIBRARY_RETRIEVE_BY_ISBN_IN_JSON_URL, isbn));
		DefaultHttpClient client = new DefaultHttpClient();
		try {
			HttpResponse response = client.execute(get);
			ResponseHandler<String> responseHandler = new BasicResponseHandler();
			if (200 == response.getStatusLine().getStatusCode()) {
				result = fromJSON(responseHandler.handleResponse(response));
			}
		} catch (Exception e) {
			Log.d("OpenLibraryBookFetcher", "Exception has been thrown", e);
		}
		return result ;
	}
	
	
	public static BookInfo fromJSON(String json) throws JSONException {
		Type bookInfoMapTokenType = new TypeToken<Map<String,BookInfo>>() {}.getType();
		Map<String,BookInfo> fromJson = new GsonBuilder().create().fromJson(json, bookInfoMapTokenType);
		Entry<String, BookInfo> firstBookEntry = fromJson.entrySet().iterator().next();
		BookInfo bookInfo = firstBookEntry.getValue();
		bookInfo.setIsbn(firstBookEntry.getKey());
		return bookInfo;
	}

}
