package com.ctp.library.android.service;

import com.ctp.library.android.domain.BookInfo;

public interface BookFetcher {

	public BookInfo fetch(String isbn);
	
}
