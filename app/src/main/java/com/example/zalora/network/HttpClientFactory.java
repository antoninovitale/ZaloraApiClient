package com.example.zalora.network;

import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.conn.params.ConnManagerPNames;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.params.ConnPerRouteBean;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HttpContext;

public class HttpClientFactory {

    private static final int TIMEOUT = 50 * 1000; // milliseconds
    private static final boolean HTTPS_NON_CERTIFICATO = false;


    private int sslPort = 443;
    private int timeout;

    private ClientConnectionManager clientConnectionManager;
    private HttpParams params;


    public HttpClientFactory(boolean httpsnoncertificato, int sslPort, int timeout) {
        this.timeout = timeout;
        this.sslPort = sslPort;
    }

    public HttpClient getHttpClient() {

        HttpClient client;

        if (HTTPS_NON_CERTIFICATO) {
            startupUnsecureSSL();
            client = new DefaultHttpClient(clientConnectionManager, params);
        } else {
            client = new DefaultHttpClient();
        }

        final HttpParams params = client.getParams();

        HttpConnectionParams.setConnectionTimeout(params, timeout);
        HttpConnectionParams.setSoTimeout(params, timeout);
        ConnManagerParams.setTimeout(params, timeout);

        return client;

    }

    private void startupUnsecureSSL() {
        SchemeRegistry schemeRegistry = new SchemeRegistry();

        // http scheme
        schemeRegistry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
        // https scheme
        schemeRegistry.register(new Scheme("https", new EasySSLSocketFactory(), sslPort));

        params = new BasicHttpParams();
        params.setParameter(ConnManagerPNames.MAX_TOTAL_CONNECTIONS, 1);
        params.setParameter(ConnManagerPNames.MAX_CONNECTIONS_PER_ROUTE, new ConnPerRouteBean(1));
        params.setParameter(HttpProtocolParams.USE_EXPECT_CONTINUE, false);
        HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
        HttpProtocolParams.setContentCharset(params, "utf8");

        clientConnectionManager = new ThreadSafeClientConnManager(params, schemeRegistry);

    }

    public static HttpClient createHttpClient() {

        HttpClient client;
        if (!HTTPS_NON_CERTIFICATO) {
            client = HttpClientFactory.getThreadSafeClient();
        } else {
            client = HttpClientFactory.getNotThreadSafeClient();
        }
        return client;
    }


    public static HttpClient getNotThreadSafeClient() {
        HttpClient client;
        HttpClientFactory httpClientFactory = new HttpClientFactory(HTTPS_NON_CERTIFICATO, 443, TIMEOUT);
        client = httpClientFactory.getHttpClient();
        ((DefaultHttpClient) client).setKeepAliveStrategy(new ConnectionKeepAliveStrategy() {
            public long getKeepAliveDuration(HttpResponse httpResponse, HttpContext httpContext) {
                return 60;
            }
        });
        return client;
    }

    public static HttpClient getThreadSafeClient() {
        // Setting up parameters
        HttpParams params = new BasicHttpParams();
        HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
        HttpProtocolParams.setContentCharset(params, "utf-8");
        params.setBooleanParameter("http.protocol.expect-continue", false);

        // Setting timeout
        HttpConnectionParams.setConnectionTimeout(params, TIMEOUT);
        HttpConnectionParams.setSoTimeout(params, TIMEOUT);

        SchemeRegistry registry = new SchemeRegistry();
        registry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
        final SSLSocketFactory sslSocketFactory = SSLSocketFactory.getSocketFactory();
        sslSocketFactory.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
        registry.register(new Scheme("https", sslSocketFactory, 443));

        // Creating thread safe client connection manager
        ThreadSafeClientConnManager manager = new ThreadSafeClientConnManager(params, registry);
        return new DefaultHttpClient(manager, params);
    }

}
