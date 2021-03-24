package com.example.bshomework3;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

interface Callback {
    void callback(LinkedList<MagicCard> data); // would be in any signature
}

public class WebRunnableMagic implements Runnable {
    private static final String LOG_TAG = MainActivity.class.getCanonicalName();
    private static final String API_URL = "https://api.magicthegathering.io/v1/cards";

    WebRunnableMagic(int pageSize, int currentPage, Callback c) throws KeyManagementException, NoSuchAlgorithmException {

        try {
            LinkedList<MagicCard> items = new LinkedList<MagicCard>();
            StringBuilder out = new StringBuilder();
            Boolean connected = false;
            HttpURLConnection urlConnection = createHttpUrlConnectionWithQueryParams(pageSize, currentPage);

            switch (urlConnection.getResponseCode()) {
                case HttpURLConnection.HTTP_OK:
                    Log.d(LOG_TAG, "HTTP OK");
                    connected = true;
                    break; // fine, go on
                case HttpURLConnection.HTTP_GATEWAY_TIMEOUT:
                    Log.d(LOG_TAG, "HTTP Timeout");
                    out.append("HTTP Error: Timeout");
                    break;
                case HttpURLConnection.HTTP_UNAVAILABLE:
                    Log.d(LOG_TAG, "HTTP Unavailable");
                    out.append("HTTP Error: Unavailable");
                    break;
                default:
                    Log.d(LOG_TAG, "HTTP Unknown response code");
                    out.append("HTTP Error: Unknown");
            }

            if (!connected) {
                urlConnection.disconnect();
                return;
            }

            InputStream in = urlConnection.getInputStream();
            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            if (!scanner.hasNext()) {
                return;
            }

            try {
                JSONObject root = new JSONObject(scanner.next());
                JSONArray cards = null;
                cards = root.getJSONArray("cards");
                for(int i = 0; i < cards.length(); i++){
                    JSONObject result = cards.getJSONObject(i);
                    MagicCard tm = new MagicCard(result);
                    items.add(tm);
                    out.append(tm.getMessageData());
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            Handler mainHandler = new Handler(Looper.getMainLooper());

            mainHandler.post(new Runnable() {
                @Override
                public void run() {
                    c.callback(items);
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
            URL url = new URL(API_URL + "?pageSize=" + pageSize + "&page=" + currentPage + "&random=true");
            Log.d(LOG_TAG, "Loading data from URL: " + url.toString());
            return (HttpURLConnection) url.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
