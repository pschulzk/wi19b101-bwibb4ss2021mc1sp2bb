package com.example.bshomework2lollipop;

import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class WebRunnableMagic implements Runnable {
    private static final String LOG_TAG = MainActivity.class.getCanonicalName();
    private static final String API_URL = "https://api.magicthegathering.io/v1/cards";

    WebRunnableMagic(int pageSize, int currentPage, Callback c) throws KeyManagementException, NoSuchAlgorithmException {

        try {
            HttpURLConnection urlConnection = createHttpUrlConnectionWithQueryParams(pageSize, currentPage);
            InputStream in = urlConnection.getInputStream();
            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            if (!scanner.hasNext()) {
                return;
            }
            StringBuilder out = new StringBuilder();
            try {
                JSONObject root = new JSONObject(scanner.next());
                JSONArray cards = null;
                cards = root.getJSONArray("cards");
                for(int i = 0; i < cards.length(); i++){
                    JSONObject result = cards.getJSONObject(i);
                    MagicCard tm = new MagicCard(result);
                    out.append(tm.getMessageData());
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
    }

    @Override
    public void run() {

    }

    private HttpURLConnection createHttpUrlConnectionWithQueryParams(int pageSize, int currentPage) throws IOException {
        try {
            URL url = new URL(API_URL + "?pageSize=" + pageSize + "&page=" + currentPage);
            return (HttpURLConnection) url.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
