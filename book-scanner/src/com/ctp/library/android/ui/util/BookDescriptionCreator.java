package com.ctp.library.android.ui.util;

import android.text.Html;
import android.text.Spanned;

import com.ctp.library.android.domain.Author;
import com.ctp.library.android.domain.BookInfo;

public class BookDescriptionCreator {

	private static final String BOOK_DESCRIPTION_HTML_TEMPLATE = "<b>%s</b><br/><br/>%s<br/>Published %s<br/>Number of pages: %d";
	
	private final BookInfo bookInfo;
	
	public BookDescriptionCreator(BookInfo bookInfo) {
		super();
		this.bookInfo = bookInfo;
	}

	public Spanned create() {
		return Html.fromHtml(String.format(BOOK_DESCRIPTION_HTML_TEMPLATE, bookInfo.getTitle(), produceAuthorsList(bookInfo), bookInfo.getPublishDate(), bookInfo.getNumberOfPages()));
	}
	
	private String produceAuthorsList(BookInfo bookInfo) {
		String prefix = "Author";
		if (bookInfo.getAuthors().size() > 1) {
			prefix += "s";
		}
		prefix += ": ";
		String authorsLinks = "";
		for (Author author : bookInfo.getAuthors()) {
			authorsLinks += "<a href=\""+ author.getUrl() + "\">" + author.getName() + "</a>, ";
		}
		authorsLinks = authorsLinks.substring(0, authorsLinks.length() - 2);
		return prefix + authorsLinks;
	}
	
}
