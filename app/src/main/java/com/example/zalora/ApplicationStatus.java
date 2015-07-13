package com.example.zalora;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;

import com.example.zalora.model.Result;

public class ApplicationStatus {

    private static final ApplicationStatus instance = new ApplicationStatus();
    private Context applicationContext;
    private List<Result> results;
    private List<LeftDrawerListItem> leftDrawerListItems;

    public static ApplicationStatus getInstance() {
        return instance;
    }

	public Context getApplicationContext() {
		return applicationContext;
	}

	public void setApplicationContext(Context applicationContext) {
		this.applicationContext = applicationContext;
	}

	public List<Result> getResults() {
		if(results==null) results = new ArrayList<Result>();
		return results;
	}

	public void setResults(List<Result> results) {
		this.results = results;
	}

	public List<LeftDrawerListItem> getLeftDrawerListItems() {
		return leftDrawerListItems;
	}

	public void setLeftDrawerListItems(
			List<LeftDrawerListItem> leftDrawerListItems) {
		this.leftDrawerListItems = leftDrawerListItems;
	}

}
