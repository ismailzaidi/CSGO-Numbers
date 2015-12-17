package com.csgo.iz.modal.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HTTPHandler {

    public interface HTTPHandlerCallback<T> {

        void notFound();
        void badRequest();
        T response(String response);
        void connectionError(IOException e);
    }

    public <T> T readHTTPRequest(String url, HTTPHandlerCallback<T> callback) {
        try {
            HttpURLConnection client = (HttpURLConnection) new URL(url).openConnection();
            client.connect();

            if (client.getResponseCode() == HttpURLConnection.HTTP_OK) {
                InputStream in = client.getInputStream();
                return callback.response(readStream(in));
            } else if (client.getResponseCode() == HttpURLConnection.HTTP_BAD_REQUEST) {
                callback.badRequest();
            } else if (client.getResponseCode() == HttpURLConnection.HTTP_NOT_FOUND) {
                callback.notFound();
            }
        } catch (IOException e) {
            e.printStackTrace();
            callback.connectionError(e);
        }
        return null;
    }

    private String readStream(InputStream in) throws IOException {
        StringBuilder content = new StringBuilder();
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        String readLine;
        while ((readLine = reader.readLine()) != null) {
            content.append(readLine);
        }

        try {
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content.toString();
    }
}
