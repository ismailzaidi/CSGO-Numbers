package com.csgo.iz;

import java.io.File;
import java.io.IOException;

import android.app.Application;
import android.content.Context;
import android.net.http.HttpResponseCache;

public class CSGONumbersApplication extends Application {

    @Override
    public void onCreate() {
        // TODO Auto-generated method stub
        super.onCreate();
        enableHTTPResponseCache(getApplicationContext());
    }

    public void onTerminate() {
        emptyCache();
    }


    private void enableHTTPResponseCache(Context context) {

        long httpCacheSize = 30 * 1024 * 1024;
        File httpCacheDir = new File(context.getCacheDir(), "http");
        try {
            HttpResponseCache.install(httpCacheDir, httpCacheSize);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void emptyCache() {
        HttpResponseCache cache = HttpResponseCache.getInstalled();
        cache.flush();
    }
}
