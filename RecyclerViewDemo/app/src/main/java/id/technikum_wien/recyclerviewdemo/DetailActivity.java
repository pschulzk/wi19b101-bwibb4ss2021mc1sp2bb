package id.technikum_wien.recyclerviewdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POKEMON_KEY = "pokemon";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Pokemon p = getIntent().getParcelableExtra(EXTRA_POKEMON_KEY);
        Toast.makeText(DetailActivity.this, p.getName(), Toast.LENGTH_SHORT).show();
    }
}