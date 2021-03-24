package com.example.bshomework3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListAdapter mAdapter;
    private Button mainButton;
    private boolean boolNew;

    private static final int PAGE_SIZE = 10;
    private int currentPage = 9;

    WebRunnableMagic webRunnableLoadWebResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        mainButton = findViewById(R.id.btn_swap);
        boolNew = false;

        mainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        try {
            loadWebResult();
        } catch (NoSuchAlgorithmException | KeyManagementException e) {
            e.printStackTrace();
        }
    }

    public void loadWebResultCallback(LinkedList<MagicCard> data) {
        RecyclerView list = findViewById(R.id.rv_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        list.setLayoutManager(layoutManager);
        list.setHasFixedSize(true);

        mAdapter = new ListAdapter(data);
        list.setAdapter(mAdapter);

        mAdapter.setOnListItemClickListener(new ListAdapter.ListItemClickListener() {
            @Override
            public void onListItemClick(MagicCard item) {
                Intent i = new Intent(MainActivity.this, DetailActivity.class);
                i.putExtra(DetailActivity.KEY_EXTRA_ITEM, item);
                startActivity(i);
            }
        });
    }

    private void loadWebResult() throws NoSuchAlgorithmException, KeyManagementException {
        this.webRunnableLoadWebResults = new WebRunnableMagic(PAGE_SIZE, currentPage, this::loadWebResultCallback);
        new Thread(webRunnableLoadWebResults).start();
    }

}