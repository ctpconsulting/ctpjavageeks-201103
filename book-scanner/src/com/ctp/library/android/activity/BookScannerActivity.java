package com.ctp.library.android.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.ctp.library.android.R;
import com.ctp.library.android.domain.BookInfo;
import com.ctp.library.android.service.BookFetcher;
import com.ctp.library.android.service.OpenLibraryBookFetcher;
import com.ctp.library.android.ui.BookDetailsDialog;

public class BookScannerActivity extends Activity {
	
	private TextView text;
	
	private BookFetcher bookFetcher;
	
	private BookInfo bookInfo;
	

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		bookFetcher = new OpenLibraryBookFetcher();
		restoreIfBookAlreadyFetched();

	}

	public void scanButtonClickHandler(View view) {
		Intent intent = ZxingIntentFactory.scanBarcode();
		startActivityForResult(intent, 0);
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent intent) {
		if (requestCode == 0) {
			if (resultCode == RESULT_OK) {
				String scanResult = intent.getStringExtra("SCAN_RESULT");
				loadBook(scanResult);
			} else if (resultCode == RESULT_CANCELED) {
				text.setText("RESULT CANCELED");
			}
		}
	}

	private void loadBook(String isbn) {
		bookInfo = bookFetcher.fetch(isbn);
		showBook(bookInfo);
	}

	
	private void showBook(BookInfo bookInfo) {
		BookDetailsDialog bookDetailsDialog = new BookDetailsDialog(BookScannerActivity.this);
		bookDetailsDialog.bindDetails(bookInfo);
		bookDetailsDialog.show();
	}

	private void restoreIfBookAlreadyFetched() {
		Object lastNonConfigurationInstance = getLastNonConfigurationInstance();
		if (null != lastNonConfigurationInstance) {
			bookInfo = (BookInfo) lastNonConfigurationInstance;
			showBook(bookInfo);
		}
	}

	@Override
	public Object onRetainNonConfigurationInstance() {
		if (null != bookInfo) {
			return bookInfo;
		}
		return super.onRetainNonConfigurationInstance();
	}
}
