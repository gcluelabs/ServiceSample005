package com.example.service;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

public class ServiceSampleService extends Service implements Runnable {

	private Boolean isRunning = false;

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		Log.i("SERVICE", "onCreate()");
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		Log.i("SERVICE", "onDestroy()");

		isRunning = false;
	}

	@Override
	public void onStart(Intent intent, int startId) {
		Log.i("SERVICE", "onStart()");

		mHandler.sendEmptyMessageDelayed(0, 10000);
	}

	/**
	 * 描画処理はHandlerでおこなう
	 */
	final Handler mHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			Intent broadcastIntent = new Intent("com.example.SERVICE_TEST");
			broadcastIntent.putExtra("VALUE", "レシーバーへの値を受け渡す");
			sendBroadcast(broadcastIntent);
		}
	};

	@Override
	public void run() {
		Log.i("SERVICE", "run()");
		int count = 1;
		while (isRunning) {

			Log.i("SERVICE", "count:" + count);
			if (count % 50 == 0) {
				Looper.prepare();
				String msg = "Count:" + count;
				Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
				Looper.loop();
			}
			count++;

			try {
				Thread.sleep(100);
			} catch (Exception e) {
			}
		}
	}
}