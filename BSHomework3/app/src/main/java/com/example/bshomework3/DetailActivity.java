package com.example.bshomework3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

public class DetailActivity extends AppCompatActivity {

    public static final String KEY_EXTRA_ITEM = "ITEM";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        MagicCard p = getIntent().getParcelableExtra(KEY_EXTRA_ITEM);
        Toast.makeText(DetailActivity.this, p.getName(), Toast.LENGTH_SHORT).show();
    }
}