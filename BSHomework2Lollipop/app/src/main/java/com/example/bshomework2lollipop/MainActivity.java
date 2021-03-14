package com.example.bshomework2lollipop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

public class MainActivity extends AppCompatActivity {

    private static final String KEY_TEXT = "MAIN";
    private static final String LOG_TAG = MainActivity.class.getCanonicalName();

    private Button btnLoadContent;
    private TextView tvResult;

    WebRunnableTelegram webRunnableLoadWebResultsTelegram;

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

        btnLoadContent.setOnClickListener(view -> {
            try {
                loadWebResult();
            } catch (NoSuchAlgorithmException | KeyManagementException e) {
                e.printStackTrace();
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
        this.webRunnableLoadWebResultsTelegram = new WebRunnableTelegram(this::loadWebResultCallback);
        new Thread(webRunnableLoadWebResultsTelegram).start();
        btnLoadContent.setEnabled(false);
    }

}