package com.ctp.library.android.activity;

import android.content.Intent;

public class ZxingIntentFactory {

	private static final String QR_CODE_MODE = "QR_CODE_MODE";

	private static final String ZXING_INTENT_SCAN_MODE_KEY = "com.google.zxing.client.android.SCAN.SCAN_MODE";

	private static final String ZXING_SCAN_INTENT = "com.google.zxing.client.android.SCAN";
	
	public static Intent scanBarcode() {
		Intent intent = new Intent(ZXING_SCAN_INTENT);
		intent.putExtra(ZXING_INTENT_SCAN_MODE_KEY, QR_CODE_MODE);
		return intent;
	}
	
}
