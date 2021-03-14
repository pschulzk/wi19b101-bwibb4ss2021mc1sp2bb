package com.example.phisch.bsahomework1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = MainActivity.class.getCanonicalName();

    private EditText field_a;

    private EditText field_b;

    private Double result = 0.0;

    private TextView ResultDisplay;

    private SeekBar seekbar;

    private TextView seekbarResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(LOG_TAG, "Life cycle hook onCreate executed !");

        field_a = findViewById(R.id.field_a);
        field_b = findViewById(R.id.field_b);

        ResultDisplay = findViewById(R.id.ResultDisplay);
        Button buttonCalculate = findViewById(R.id.button);
        Button buttonNavigate = findViewById(R.id.button_navigate);

        seekbar = findViewById(R.id.seekbar);
        seekbarResult= findViewById(R.id.seekbar_result);

        buttonCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(LOG_TAG, "buttonCalculate pushed !");
                result = getCalculatedResult();
                ResultDisplay.setText(result.toString());
            }
        });

        buttonNavigate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, SecondActivity.class);

                boolean resultDisplayIsValid = ResultDisplay.getText().toString().length() > 0;
                if (resultDisplayIsValid ) {
                    i.putExtra(SecondActivity.KEY, ResultDisplay.getText().toString());
                }

                boolean resultIsValid = result.toString().length() > 0;
                if (resultIsValid ) {
                    i.putExtra(SecondActivity.KEY, result.toString());
                }
                startActivity(i);
            }
        });

        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                seekbarResult.setText(result.toString());
            }
        });
    }

    Double getCalculatedResult() {
        Double a = Double.parseDouble(field_a.getText().toString());
        Double b = Double.parseDouble(field_b.getText().toString());
        return a - b;
    }
}
