package com.example.viewutil;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import com.example.myutil.R;
import com.example.threadutil.BaseExecute;
import com.example.threadutil.ExecuteInterface;
import com.example.threadutil.ThreadUtil;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.widget.ImageView;

public class AsyncImageView extends ImageView {

	public AsyncImageView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub

	}

	public AsyncImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public AsyncImageView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub

	}

	public void loadingAsyncImage(String url) {
	
			ThreadUtil.executeLoadImage(new BaseExecute(new ExecuteInterface() {

				@Override
				public void execute(Object... object) {
					// TODO Auto-generated method stub
					try {
						Drawable drawable = Drawable.createFromStream(new URL(
								(String) object[0]).openStream(), "image.png");
						
						ThreadUtil.backToUI(new BaseExecute(new ExecuteInterface() {

							@Override
							public void execute(Object... object) {
								// TODO Auto-generated method stub
								setImageDrawable((Drawable) object[0]);
							}
						}, drawable));

					} catch (MalformedURLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						Drawable drawable = getContext().getResources()
								.getDrawable(R.drawable.ic_launcher);
						ThreadUtil.backToUI(new BaseExecute(new ExecuteInterface() {

							@Override
							public void execute(Object... object) {
								// TODO Auto-generated method stub
								setImageDrawable((Drawable) object[0]);
							}
						}, drawable));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						Drawable drawable = getContext().getResources()
								.getDrawable(R.drawable.ic_launcher);
						ThreadUtil.backToUI(new BaseExecute(new ExecuteInterface() {

							@Override
							public void execute(Object... object) {
								// TODO Auto-generated method stub
								setImageDrawable((Drawable) object[0]);
							}
						}, drawable));
					}
				}
			}, url));

		
	}


}
