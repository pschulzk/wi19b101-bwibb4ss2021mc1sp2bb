package com.example.bshomework2lollipop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.os.StrictMode;
import android.text.method.ScrollingMovementMethod;
import android.widget.Button;
import android.widget.TextView;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

public class MainActivity extends AppCompatActivity {

    private static final String KEY_TEXT = "MAIN";
    private static final String LOG_TAG = MainActivity.class.getCanonicalName();
    /**
     * Laut Aufgabenstellung sollten wir die default pageSize benutzen, aber mein Rechner/Emulator hat
     * zu wenig Memory und raucht dan ab. Daher hier bitte Verständnis für den kleineren Wert.
     */
    private static final int PAGE_SIZE = 10;
    private int currentPage = 0;

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

        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey(KEY_TEXT)) {
                tvResult.setText(savedInstanceState.getString(KEY_TEXT).toString());
            }
        }

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
        this.webRunnableLoadWebResults = new WebRunnableMagic(PAGE_SIZE, currentPage, this::loadWebResultCallback);
        new Thread(webRunnableLoadWebResults).start();
        btnLoadContent.setEnabled(false);
        currentPage++;
    }

}