package com.ctp.library.android.ui;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.ctp.library.android.R;
import com.ctp.library.android.domain.BookInfo;
import com.ctp.library.android.domain.BookInfo.CoverSize;
import com.ctp.library.android.ui.util.BookDescriptionCreator;

public class BookDetailsDialog {

	private final Dialog dialog;
	
	public BookDetailsDialog(Context context) {
		this.dialog = new Dialog(context);
        dialog.setContentView(R.layout.bookdialog);
        dialog.setCancelable(true);
        defineControls();
	}

	private void defineControls() {
		Button button = (Button) dialog.findViewById(R.id.closeBookDetailsButton);
        button.setOnClickListener(new OnClickListener() {
        	@Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
	}

	public void bindDetails(BookInfo bookInfo) {
		dialog.setTitle("Scaned " + bookInfo.getIsbn());

		TextView text = (TextView) dialog.findViewById(R.id.content);
        ImageView image = (ImageView) dialog.findViewById(R.id.imageView);
        
        Bitmap coverImage = downloadImage(bookInfo.getCoverUrl(CoverSize.MEDIUM));
        image.setImageBitmap(coverImage);
        text.setText(new BookDescriptionCreator(bookInfo).create());
        text.setMovementMethod(LinkMovementMethod.getInstance());
	}

	public void show() {
		dialog.show();
	}

	private Bitmap downloadImage(String imageLocation) {
		Bitmap bitmap = null;
		HttpURLConnection connection = null;
		try {
			URL imageUrl = new URL(imageLocation);
			connection = (HttpURLConnection) imageUrl.openConnection();
			connection.setDoInput(true);
			connection.connect();
			InputStream is = connection.getInputStream();
			bitmap = BitmapFactory.decodeStream(is);
		} catch (Exception e) {
			Log.d("Book-scanner", "Exception while trying to download image from location " + imageLocation, e);
		} finally {
			if (null != connection) {
				connection.disconnect();
			}
		}
		return bitmap;
	}
	
}
