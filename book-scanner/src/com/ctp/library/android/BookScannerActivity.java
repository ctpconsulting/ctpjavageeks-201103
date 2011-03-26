package com.ctp.library.android;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class BookScannerActivity extends Activity {
	private EditText text;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		text = (EditText) findViewById(R.id.editText1);

	}

	public void scanButtonClickHandler(View view) {
		text = (EditText) findViewById(R.id.editText1);
		Intent intent = new Intent("com.google.zxing.client.android.SCAN");
		intent.putExtra("com.google.zxing.client.android.SCAN.SCAN_MODE", "QR_CODE_MODE");
		startActivityForResult(intent, 0);
	}

	public void onActivityResult(int requestCode, int resultCode, Intent intent) {
		if (requestCode == 0) {
			if (resultCode == RESULT_OK) {
				String scanResult = intent.getStringExtra("SCAN_RESULT");
				text.setText(scanResult);
			} else if (resultCode == RESULT_CANCELED) {
				// Handle cancel
				text.setText("RESULT CANCELED");
			}
		}
	}

}
