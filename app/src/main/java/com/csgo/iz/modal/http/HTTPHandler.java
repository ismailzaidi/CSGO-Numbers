package com.csgo.iz.modal.http;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HTTPHandler {
	private static final String ERROR_REQUEST = "Error";
	public HTTPHandler(){
	}
	public String readHTTPRequest(String url) {
		StringBuilder content = new StringBuilder();
		try {
			HttpURLConnection client = (HttpURLConnection) new URL(url).openConnection();
			client.connect();
            Log.v("achievements", "Status Code: " + client.getResponseCode() +"\nURL: " + url);
			if (client.getResponseCode() == HttpURLConnection.HTTP_OK) {
				InputStream in = client.getInputStream();
				BufferedReader reader = new BufferedReader(new InputStreamReader(in));

				// Build string from input stream
				String readLine = reader.readLine();
				while (readLine != null) {
					content.append(readLine);
					readLine = reader.readLine();
				}

			} else if (client.getResponseCode() == HttpURLConnection.HTTP_BAD_REQUEST) {
				return ERROR_REQUEST;
			} else {
				content = null;
			}
		} catch (IOException e) {
			Log.e("readData", "IOException:\n+e.getMessage()");
		}

		// return data
		if (content == null) {
			return (null);
		} else {
			return (content.toString());
		}
	}

}
