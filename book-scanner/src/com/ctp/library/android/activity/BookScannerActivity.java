package com.ctp.library.android.activity;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ctp.library.android.R;
import com.ctp.library.android.domain.BookInfo;
import com.ctp.library.android.domain.BookInfo.CoverSize;
import com.ctp.library.android.service.OpenLibraryBookFetcher;

public class BookScannerActivity extends Activity {
	
	private TextView text;
	
	private ImageView image;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		bindWidgets();

	}

	public void scanButtonClickHandler(View view) {
		bindWidgets();
		Intent intent = ZxingIntentFactory.scanBarcode();
		startActivityForResult(intent, 0);
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent intent) {
		if (requestCode == 0) {
			if (resultCode == RESULT_OK) {
				String scanResult = intent.getStringExtra("SCAN_RESULT");
				BookInfo bookInfo = fetchBook(scanResult);
				text.setText(bookInfo.getTitle());
				downloadImage(bookInfo.getCoverUrl(CoverSize.MEDIUM));
			} else if (resultCode == RESULT_CANCELED) {
				text.setText("RESULT CANCELED");
			}
		}
	}
	
	private void downloadImage(String imageLocation) {
		HttpURLConnection connection = null;
		try {
			URL imageUrl = new URL(imageLocation);
			connection = (HttpURLConnection) imageUrl.openConnection();
			connection.setDoInput(true);
			connection.connect();
			InputStream is = connection.getInputStream();
			image.setImageBitmap(BitmapFactory.decodeStream(is));
		} catch (Exception e) {
			Log.d("Book-scanner", "Exception while trying to download image from location " + imageLocation, e);
		} finally {
			if (null != connection) {
				connection.disconnect();
			}
		}
	}

	private void bindWidgets() {
		text = (TextView) findViewById(R.id.content);
		image = (ImageView) findViewById(R.id.imageView);
	}


	private BookInfo fetchBook(String scannedInput) {
		OpenLibraryBookFetcher bookFetcher = new OpenLibraryBookFetcher();
		BookInfo bookInfo = bookFetcher.fetch(scannedInput);
		return bookInfo;
	}

}
