package com.example.zalora.network;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;

import android.text.TextUtils;

import com.google.gson.JsonDeserializer;
import com.example.zalora.ApplicationStatus;
import com.example.zalora.model.Response;

public class ServerRequestUtilities {
	public final static String NO_CONNECTION_ERROR_MSG = "No connection!";
	private static HttpClient mHttpClient;
	protected final String API_URL = "https://www.zalora.com.my/mobile-api/women/clothing";
	protected final String SORT_URL = "https://www.zalora.com.my/mobile-api/women/clothing?maxitems=24&page=1&sort=price&dir=desc";

	@SuppressWarnings("rawtypes")
	protected Map<Class, JsonDeserializer> getClassAndDeserializerMap() {
		return new HashMap<Class, JsonDeserializer>();
	}

	public static HttpClient getHttpClient() {
		if (mHttpClient == null) {
			mHttpClient = HttpClientFactory.createHttpClient();
		}
		return mHttpClient;
	}

	public String performPostRequest(String url, String object)
			throws IOException {
		if (!NetworkUtilities.isNetworkConnected(ApplicationStatus
				.getInstance().getApplicationContext())) {
			return NO_CONNECTION_ERROR_MSG;
		}
		final HttpPost httpPost = new HttpPost(url);
		if (!TextUtils.isEmpty(object)) {
			StringEntity stringEntity = new StringEntity(object);
			stringEntity.setContentType("application/json;charset=UTF-8");
			httpPost.setEntity(stringEntity);
			httpPost.getParams().setParameter("Content-Type",
					"application/json");
		}
		HttpResponse resp = getHttpClient().execute(httpPost);
		final int statusCode = resp.getStatusLine().getStatusCode();
		if (statusCode == HttpURLConnection.HTTP_UNAUTHORIZED) {
			resp.getEntity().consumeContent();
			throw new IOException("Unexpected status code " + statusCode);
		} else if (statusCode != HttpURLConnection.HTTP_OK) {
			resp.getEntity().consumeContent();
			throw new IOException("Unexpected status code " + statusCode);
		}
		final String response = EntityUtils.toString(resp.getEntity());
		return response;
	}

	protected String performGetRequest(String url) throws IOException {
		if (!NetworkUtilities.isNetworkConnected(ApplicationStatus
				.getInstance().getApplicationContext())) {
			return NO_CONNECTION_ERROR_MSG;
		}
		final HttpGet httpGet = new HttpGet(url);
		HttpResponse resp = getHttpClient().execute(httpGet);
		final int statusCode = resp.getStatusLine().getStatusCode();
		if (statusCode == HttpURLConnection.HTTP_UNAUTHORIZED) {
			resp.getEntity().consumeContent();
			throw new IOException("Unexpected status code " + statusCode);
		} else if (statusCode != HttpURLConnection.HTTP_OK) {
			resp.getEntity().consumeContent();
			throw new IOException("Unexpected status code " + statusCode);
		}
		final String response = EntityUtils.toString(resp.getEntity());
		return response;
	}
	
	protected String performGetRequestSorted(String url, String param) throws IOException {
		if (!NetworkUtilities.isNetworkConnected(ApplicationStatus
				.getInstance().getApplicationContext())) {
			return NO_CONNECTION_ERROR_MSG;
		}
		url = url.concat("?maxitems=24&page=1&sort=").concat(param).concat("&dir=desc");
		final HttpGet httpGet = new HttpGet(url);
		HttpResponse resp = getHttpClient().execute(httpGet);
		final int statusCode = resp.getStatusLine().getStatusCode();
		if (statusCode == HttpURLConnection.HTTP_UNAUTHORIZED) {
			resp.getEntity().consumeContent();
			throw new IOException("Unexpected status code " + statusCode);
		} else if (statusCode != HttpURLConnection.HTTP_OK) {
			resp.getEntity().consumeContent();
			throw new IOException("Unexpected status code " + statusCode);
		}
		final String response = EntityUtils.toString(resp.getEntity());
		return response;
	}

	

	protected static class ResponseFactory<T extends Response> {

		protected T getResponseError(Class<T> c, Throwable t) {
			try {
				T response = c.newInstance();
				return response;
			} catch (IllegalAccessException e) {

			} catch (InstantiationException e) {

			}
			return null;
		}

		@SuppressWarnings("hiding")
		public <T extends Response> T getResponseErrorStatusCode(Class<T> c,
				int statusCode) {
			try {
				T response = c.newInstance();
				return response;
			} catch (IllegalAccessException e) {

			} catch (InstantiationException e) {

			}
			return null;
		}
	}

}
