package com.example.phisch.bsahomework1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    public static final String KEY = "SecondView";

    private TextView textViewTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Button buttonNavigate = findViewById(R.id.button_navigate);

        textViewTest = findViewById(R.id.textViewTest);
        Bundle extras = getIntent().getExtras();
        if (extras != null && extras.getString(KEY) != null) {
            setText(extras.getString(KEY));
        } else {
            setText(getResources().getString(R.string.empty_string));
        }

        buttonNavigate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SecondActivity.this, MainActivity.class);
                startActivity(i);
            }
        });
    }

    private void setText(String text) {
        textViewTest.setText((text));
    }

}
