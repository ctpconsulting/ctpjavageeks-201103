package com.ctp.library.android.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.ctp.library.android.R;
import com.ctp.library.android.domain.BookInfo;
import com.ctp.library.android.service.OpenLibraryBookFetcher;

public class BookScannerActivity extends Activity {
	
	private TextView text;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		text = (TextView) findViewById(R.id.content);

	}

	public void scanButtonClickHandler(View view) {
		text = (TextView) findViewById(R.id.content);
		Intent intent = ZxingIntentFactory.scanBarcode();
		startActivityForResult(intent, 0);
	}

	public void onActivityResult(int requestCode, int resultCode, Intent intent) {
		if (requestCode == 0) {
			if (resultCode == RESULT_OK) {
				String scanResult = intent.getStringExtra("SCAN_RESULT");
				BookInfo bookInfo = fetchBook(scanResult);
				text.setText(bookInfo.getIsbn());
			} else if (resultCode == RESULT_CANCELED) {
				text.setText("RESULT CANCELED");
			}
		}
	}

	private BookInfo fetchBook(String scannedInput) {
		OpenLibraryBookFetcher bookFetcher = new OpenLibraryBookFetcher();
		BookInfo bookInfo = bookFetcher.fetch(scannedInput);
		return bookInfo;
	}

}
