package com.example.zalora.network;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.example.zalora.utils.Utils;
import com.example.zalora.model.Response;

public class RetrieveDataTask extends AsyncTask<Object, Void, Response> {
	public final static String TAG = RetrieveDataTask.class.getSimpleName();
	private Context context;    
	private ProgressDialog progressDialog;
    private PropertyChangeListener propertyChangeListener;

    public static final String PROPERTY_NAME = "RetrieveData";

    public RetrieveDataTask(Context context) {
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        progressDialog = ProgressDialog.show(context, "", "Caricamento dati...");
        super.onPreExecute();
    }

    @Override
    protected Response doInBackground(Object... input) {  	
    	return new ServerRequest().retrieveData();
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
