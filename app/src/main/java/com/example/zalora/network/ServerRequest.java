package com.example.zalora.network;

import com.google.gson.Gson;
import com.example.zalora.model.Response;

public class ServerRequest extends ServerRequestUtilities {

	private Gson gson = new Gson();

	public Response retrieveData() {
		try {
			String jsonResponse;
			jsonResponse = performGetRequest(API_URL);
			Response response = gson.fromJson(jsonResponse, Response.class);
			return response;
		} catch (Exception e) {			
			return new ResponseFactory<Response>().getResponseError(
					Response.class, e);
		}
	}
	
	public Response retrieveDataSorted(String param) {
		try {
			String jsonResponse;
			jsonResponse = performGetRequestSorted(API_URL, param);
			Response response = gson.fromJson(jsonResponse, Response.class);
			return response;
		} catch (Exception e) {			
			return new ResponseFactory<Response>().getResponseError(
					Response.class, e);
		}
	}

}
