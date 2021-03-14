package com.example.bshomework2lollipop;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

import javax.net.ssl.SSLContext;

interface Callback {
    void callback(String message); // would be in any signature
}

public class WebRunnableTelegram implements Runnable {

    private static final String LOG_TAG = MainActivity.class.getCanonicalName();
    private static final String API_URL = "https://api.telegram.org/bot1530364130:AAHvyaeKdrO9S7ZD-94FYKIIj4nmtkpOA1M/getUpdates";
    private Callback c;
    private URL url;

    WebRunnableTelegram(Callback c) throws KeyManagementException, NoSuchAlgorithmException {

        try {
            this.url = new URL(API_URL);

            try {
                HttpURLConnection urlConnection = (HttpURLConnection) this.url.openConnection();
                urlConnection.setRequestMethod("GET");
                InputStream in = urlConnection.getInputStream();
                Scanner scanner = new Scanner(in);
                scanner.useDelimiter("\\A");

                if (!scanner.hasNext()) {
                    return;
                }
                StringBuilder out = new StringBuilder();
                try {
                    JSONObject root = new JSONObject(scanner.next());
                    JSONArray results = null;
                    results = root.getJSONArray("result");
                    for(int i = 0; i < results.length(); i++){
                        JSONObject result = results.getJSONObject(i);
                        TelegramMessage tm = new TelegramMessage(result);
                        out.append(tm.getMessageData());
                        Log.d(LOG_TAG, "!!! TelegramMessage.getMessageData(): " + out);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                Handler mainHandler = new Handler(Looper.getMainLooper());

                mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        c.callback(out.toString());
                    }
                });

            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {

    }

}
