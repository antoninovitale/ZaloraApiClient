/**
 * 
 */
package com.example.zalora.network;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;
import java.util.List;

import org.apache.http.conn.util.InetAddressUtils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Build;


/**
 * Classe che racchiude una serie di utility comuni per funzioni per sim e
 * networking
 * 
 * @author a.vitale
 * 
 */
public class NetworkUtilities {
//	private static final String TAG = NetworkUtilities.class.getSimpleName();	

	/**
	 * recupera l'indirizzo ip del dispositivo
	 * 
	 * @return
	 */
	public static String getLocalIpAddress() {
		try {
			for (Enumeration<NetworkInterface> en = NetworkInterface
					.getNetworkInterfaces(); en.hasMoreElements();) {
				NetworkInterface intf = en.nextElement();
				for (Enumeration<InetAddress> enumIpAddr = intf
						.getInetAddresses(); enumIpAddr.hasMoreElements();) {
					InetAddress inetAddress = enumIpAddr.nextElement();
					System.out.println("ip1--:" + inetAddress);
					System.out.println("ip2--:" + inetAddress.getHostAddress());
					// for getting IPV4 format
					if (!inetAddress.isLoopbackAddress()
							&& InetAddressUtils.isIPv4Address(inetAddress
									.getHostAddress())) {
						String ip = inetAddress.getHostAddress();
						System.out.println("ip---::" + ip);
						return ip;
					}
				}
			}
		}
		catch (Exception ex) {
		}
		return "";
	}

	/**
	 * metodo che verifica se l'applicazione e' connessa ad Internet
	 * 
	 * @param context
	 * @return
	 */
	public static boolean isConnected(Context context) {
		ConnectivityManager connMgr = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
		if (networkInfo != null && networkInfo.isConnectedOrConnecting())
			return true;
		else
			return false;
	}

	public static boolean isNetworkConnected(Context ctx) {
		ConnectivityManager cm = (ConnectivityManager) ctx
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		return (cm.getActiveNetworkInfo() != null);
	}

	/**
	 * metodo che verifica se l'applicazione e' connessa tramite wifi
	 * 
	 * @param context
	 * @return
	 */
	public static boolean isWifiConnected(Context context) {
		ConnectivityManager connMgr = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo wifi = connMgr
				.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
		if (wifi != null && wifi.isConnected())
			return true;
		else
			return false;
	}

	/**
	 * metodo che verifica se l'applicazione e' connessa tramite connessione
	 * dati
	 * 
	 * @param context
	 * @return
	 */
	public static boolean isMobileConnected(Context context) {
		ConnectivityManager connMgr = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo mobile = connMgr
				.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
		if (mobile != null && mobile.isConnected())
			return true;
		else
			return false;
	}

	/**
	 * disabilita la connessione WiFi
	 * 
	 * @param context
	 * @return false se la connessione e' disabilitata
	 */
	public static boolean disableWifiConnection(Context context) {
		WifiManager wifiManager = (WifiManager) context
				.getSystemService(Context.WIFI_SERVICE);
		if (wifiManager.isWifiEnabled())
			wifiManager.setWifiEnabled(false);
		return wifiManager.isWifiEnabled();
	}

	/**
	 * abilita/disabilita la connessione dati
	 * 
	 * @param context
	 * @param enabled
	 */
	public static void setMobileDataEnabled(Context context, boolean enabled) {
		final ConnectivityManager conman = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		Class<?> conmanClass;
		try {
			conmanClass = Class.forName(conman.getClass().getName());
			final Field iConnectivityManagerField = conmanClass
					.getDeclaredField("mService");
			iConnectivityManagerField.setAccessible(true);
			final Object iConnectivityManager = iConnectivityManagerField
					.get(conman);
			final Class<?> iConnectivityManagerClass = Class
					.forName(iConnectivityManager.getClass().getName());
			final Method setMobileDataEnabledMethod = iConnectivityManagerClass
					.getDeclaredMethod("setMobileDataEnabled", Boolean.TYPE);
			setMobileDataEnabledMethod.setAccessible(true);
			setMobileDataEnabledMethod.invoke(iConnectivityManager, enabled);
		}
		catch (ClassNotFoundException e) {
		}
		catch (IllegalArgumentException e) {		
		}
		catch (IllegalAccessException e) {
		}
		catch (InvocationTargetException e) {
		}
		catch (NoSuchMethodException e) {
		}
		catch (NoSuchFieldException e) {
		}
	}

	/**
	 * controlla se la connessione dati e' abilitata o meno
	 * 
	 * @param context
	 * @return
	 */
	public static boolean getMobileDataEnabled(Context context) {
		try {
			final ConnectivityManager conman = (ConnectivityManager) context
					.getSystemService(Context.CONNECTIVITY_SERVICE);
			Class<?> conmanClass;
			conmanClass = Class.forName(conman.getClass().getName());
			final Field iConnectivityManagerField = conmanClass
					.getDeclaredField("mService");
			iConnectivityManagerField.setAccessible(true);
			final Object iConnectivityManager = iConnectivityManagerField
					.get(conman);
			final Class<?> iConnectivityManagerClass = Class
					.forName(iConnectivityManager.getClass().getName());
			final Method getMobileDataEnabledMethod = iConnectivityManagerClass
					.getDeclaredMethod("getMobileDataEnabled");
			getMobileDataEnabledMethod.setAccessible(true);
			boolean r = (Boolean) getMobileDataEnabledMethod
					.invoke(iConnectivityManager);
			return r;
		}
		catch (ClassNotFoundException e) {
			return false;
		}
		catch (IllegalArgumentException e) {
			return false;
		}
		catch (IllegalAccessException e) {
			return false;
		}
		catch (InvocationTargetException e) {
			return false;
		}
		catch (NoSuchMethodException e) {
			return false;
		}
		catch (NoSuchFieldException e) {
			return false;
		}
	}

	/**
	 * metodo che controlla se disponibile il DownloadManager
	 * 
	 * @param context
	 * @return
	 */
	public static boolean isDownloadManagerAvailable(Context context) {
		try {
			if (Build.VERSION.SDK_INT < Build.VERSION_CODES.GINGERBREAD) {
				return false;
			}
			Intent intent = new Intent(Intent.ACTION_MAIN);
			intent.addCategory(Intent.CATEGORY_LAUNCHER);
			intent.setClassName("com.android.providers.downloads.ui",
					"com.android.providers.downloads.ui.DownloadList");
			List<ResolveInfo> list = context.getPackageManager()
					.queryIntentActivities(intent,
							PackageManager.MATCH_DEFAULT_ONLY);
			return list.size() > 0;
		}
		catch (Exception e) {
			return false;
		}
	}

	/**
	 * metodo che recupera i byte di un oggetto InputStream
	 * 
	 * @param inputStream
	 * @return
	 * @throws IOException
	 */
	public static byte[] readBytes(InputStream inputStream) throws IOException {
		ByteArrayOutputStream byteBuffer = new ByteArrayOutputStream();
		int bufferSize = 1024;
		byte[] buffer = new byte[bufferSize];
		int len = 0;
		while ((len = inputStream.read(buffer)) != -1) {
			byteBuffer.write(buffer, 0, len);
		}
		return byteBuffer.toByteArray();
	}

}
