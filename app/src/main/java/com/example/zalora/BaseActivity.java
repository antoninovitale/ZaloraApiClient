package com.example.zalora;

import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.zalora.model.Response;

public class BaseActivity extends AppCompatActivity {

    public final boolean isCodeSuccessful(Response response) {
    	if(response==null){
            Toast.makeText(this, "Errore", Toast.LENGTH_LONG).show();
            return false;
        }
    	else 
    		if(response.getMetadata()==null)
    			return false;
    		else
    			if(response.getMetadata().getResults()==null)
    				return false;
    			else
    				return true;
    }

}
