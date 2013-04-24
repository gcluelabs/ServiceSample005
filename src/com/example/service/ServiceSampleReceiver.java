package com.example.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

/**
 * Broadcast Receiver
 */
public class ServiceSampleReceiver extends BroadcastReceiver {
	
	@Override
	public void onReceive(Context context, Intent intent) {
	    Intent testIntent = new Intent();
	    testIntent.setAction(Intent.ACTION_VIEW);
	    Uri uri = Uri.parse("http://www.gclue.com/");
	    testIntent.setData(uri);
	    testIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	    context.startActivity(testIntent);
	}
}