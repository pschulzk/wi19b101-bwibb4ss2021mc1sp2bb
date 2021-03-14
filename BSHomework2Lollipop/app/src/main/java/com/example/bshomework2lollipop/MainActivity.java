package com.example.bshomework2lollipop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

public class MainActivity extends AppCompatActivity {

    private static final String KEY_TEXT = "MAIN";
    private static final String LOG_TAG = MainActivity.class.getCanonicalName();

    private static final String API_URL = "https://api.telegram.org/bot1530364130:AAHvyaeKdrO9S7ZD-94FYKIIj4nmtkpOA1M/getUpdates";

    private Button btnLoadContent;
    private TextView tvResult;

    WebRunnable webRunnableLoadWebResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        setContentView(R.layout.activity_main);

        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey(KEY_TEXT)) {
                tvResult.setText(savedInstanceState.getString(KEY_TEXT));
            }
        }

        btnLoadContent = findViewById(R.id.btn_load_content);
        tvResult = findViewById(R.id.tv_result);

        btnLoadContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    loadWebResult();
                } catch (NoSuchAlgorithmException | KeyManagementException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        outState.putString(KEY_TEXT, tvResult.getText().toString());
    }

    public void loadWebResultCallback(String message) {
        tvResult.setText(message);
        btnLoadContent.setEnabled(true);
    }

    private void loadWebResult() throws NoSuchAlgorithmException, KeyManagementException {
        this.webRunnableLoadWebResults = new WebRunnable(API_URL, this::loadWebResultCallback);
        new Thread(webRunnableLoadWebResults).start();
        btnLoadContent.setEnabled(false);
    }

}