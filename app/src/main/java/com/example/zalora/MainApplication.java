package com.example.zalora;

import android.app.Application;

public class MainApplication extends Application {
	
	@Override
	public void onCreate() {
		super.onCreate();
		ApplicationStatus.getInstance().setApplicationContext(getApplicationContext());
	}
}
