package com.example.zalora.network;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.example.zalora.utils.Utils;
import com.example.zalora.model.Response;

public class RetrieveDataSortedTask extends AsyncTask<String, Void, Response> {
	public final static String TAG = RetrieveDataSortedTask.class.getSimpleName();
	private Context context;    
	private ProgressDialog progressDialog;
    private PropertyChangeListener propertyChangeListener;

    public static final String PROPERTY_NAME = "RetrieveDataSorted";

    public RetrieveDataSortedTask(Context context) {
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        progressDialog = ProgressDialog.show(context, "", "Caricamento dati...");
        super.onPreExecute();
    }

    @Override
    protected Response doInBackground(String... input) {  	
    	return new ServerRequest().retrieveDataSorted(input[0]);
    }

    @Override
    protected void onPostExecute(Response response) {
        final PropertyChangeEvent propertyChangeEvent = new PropertyChangeEvent(this, PROPERTY_NAME, null, response);
        Utils.tryToDismissDialog(progressDialog);
        propertyChangeListener.propertyChange(propertyChangeEvent);
    }

    public void addPropertyChangeListener(PropertyChangeListener propertyChangeListener) {
        this.propertyChangeListener = propertyChangeListener;
    }
    
}
