package com.example.bshomework2lollipop;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.os.StrictMode;
import android.text.method.ScrollingMovementMethod;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

public class MainActivity extends AppCompatActivity {

    private static final String KEY_TEXT = "MAIN";
    private static final String KEY_CURRENT_PAGE = "CURRENT_PAGE";
    private static final String LOG_TAG = MainActivity.class.getCanonicalName();
    /**
     * Laut Aufgabenstellung sollten wir die default pageSize benutzen, aber mein Rechner/Emulator hat
     * zu wenig Memory und raucht dann ab. Daher hier bitte Verständnis für den kleineren Wert.
     */
    private static final int PAGE_SIZE = 10;
    private int currentPage = 1;

    private Button btnLoadContent;
    private TextView tvResult;

    WebRunnableMagic webRunnableLoadWebResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        setContentView(R.layout.activity_main);

        btnLoadContent = findViewById(R.id.btn_load_content);
        tvResult = findViewById(R.id.tv_result);
        tvResult.setMovementMethod(new ScrollingMovementMethod());

        btnLoadContent.setOnClickListener(view -> {
            try {
                loadWebResult();
            } catch (NoSuchAlgorithmException | KeyManagementException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(KEY_TEXT, tvResult.getText().toString());
        outState.putInt(KEY_CURRENT_PAGE, currentPage);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey(KEY_TEXT)) {
                tvResult.setText(savedInstanceState.getString(KEY_TEXT));
            }
            if (savedInstanceState.containsKey(KEY_CURRENT_PAGE)) {
                currentPage = savedInstanceState.getInt(KEY_CURRENT_PAGE);
                Log.d(LOG_TAG, "Restoring state setting of current page: " + String.valueOf(currentPage));
            }
        }
    }

    public void loadWebResultCallback(String message) {
        tvResult.setText(message);
        btnLoadContent.setEnabled(true);
    }

    private void loadWebResult() throws NoSuchAlgorithmException, KeyManagementException {
        this.webRunnableLoadWebResults = new WebRunnableMagic(PAGE_SIZE, currentPage, this::loadWebResultCallback);
        new Thread(webRunnableLoadWebResults).start();
        btnLoadContent.setEnabled(false);
        currentPage++;
    }

}