package com.example.bshomework3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView list = findViewById(R.id.rv_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        list.setLayoutManager(layoutManager);
        list.setHasFixedSize(true);

        mAdapter = new ListAdapter(generateContent());
        list.setAdapter(mAdapter);
    }

    private List<Pokemon> generateContent(){
        List<Pokemon> data = new LinkedList<>();
        data.add(new Pokemon("Bulbasaur", "Grass", R.color.colorbulbasaur));
        data.add(new Pokemon("Charmander", "Fire", R.color.colorcharmander));
        data.add(new Pokemon("Squirtle", "Water", R.color.colorsquirtle));
        data.add(new Pokemon("Bulbasaur", "Grass", R.color.colorbulbasaur));
        data.add(new Pokemon("Charmander", "Fire", R.color.colorcharmander));
        data.add(new Pokemon("Squirtle", "Water", R.color.colorsquirtle));
        data.add(new Pokemon("Pikachu", "Electric", R.color.colorpikachu));
        data.add(new Pokemon("Squirtle", "Water", R.color.colorsquirtle));
        data.add(new Pokemon("Bulbasaur", "Grass", R.color.colorbulbasaur));
        data.add(new Pokemon("Squirtle", "Water", R.color.colorsquirtle));
        data.add(new Pokemon("Pikachu", "Electric", R.color.colorpikachu));
        data.add(new Pokemon("Squirtle", "Water", R.color.colorsquirtle));
        data.add(new Pokemon("Bulbasaur", "Grass", R.color.colorbulbasaur));
        data.add(new Pokemon("Charmander", "Fire", R.color.colorcharmander));
        data.add(new Pokemon("Squirtle", "Water", R.color.colorsquirtle));
        data.add(new Pokemon("Bulbasaur", "Grass", R.color.colorbulbasaur));
        data.add(new Pokemon("Charmander", "Fire", R.color.colorcharmander));
        data.add(new Pokemon("Squirtle", "Water", R.color.colorsquirtle));
        data.add(new Pokemon("Pikachu", "Electric", R.color.colorpikachu));
        data.add(new Pokemon("Squirtle", "Water", R.color.colorsquirtle));
        data.add(new Pokemon("Bulbasaur", "Grass", R.color.colorbulbasaur));
        data.add(new Pokemon("Squirtle", "Water", R.color.colorsquirtle));
        data.add(new Pokemon("Pikachu", "Electric", R.color.colorpikachu));
        data.add(new Pokemon("Squirtle", "Water", R.color.colorsquirtle));
        data.add(new Pokemon("Bulbasaur", "Grass", R.color.colorbulbasaur));
        data.add(new Pokemon("Charmander", "Fire", R.color.colorcharmander));
        data.add(new Pokemon("Squirtle", "Water", R.color.colorsquirtle));
        data.add(new Pokemon("Pikachu", "Electric", R.color.colorpikachu));
        data.add(new Pokemon("Squirtle", "Water", R.color.colorsquirtle));
        data.add(new Pokemon("Pikachu", "Electric", R.color.colorpikachu));
        data.add(new Pokemon("Pikachu", "Electric", R.color.colorpikachu));
        data.add(new Pokemon("Squirtle", "Water", R.color.colorsquirtle));
        data.add(new Pokemon("Pikachu", "Electric", R.color.colorpikachu));
        data.add(new Pokemon("Pikachu", "Electric", R.color.colorpikachu));
        data.add(new Pokemon("Squirtle", "Water", R.color.colorsquirtle));
        data.add(new Pokemon("Bulbasaur", "Grass", R.color.colorbulbasaur));
        data.add(new Pokemon("Squirtle", "Water", R.color.colorsquirtle));
        data.add(new Pokemon("Pikachu", "Electric", R.color.colorpikachu));
        data.add(new Pokemon("Squirtle", "Water", R.color.colorsquirtle));
        data.add(new Pokemon("Bulbasaur", "Grass", R.color.colorbulbasaur));
        data.add(new Pokemon("Charmander", "Fire", R.color.colorcharmander));
        data.add(new Pokemon("Squirtle", "Water", R.color.colorsquirtle));
        data.add(new Pokemon("Pikachu", "Electric", R.color.colorpikachu));
        data.add(new Pokemon("Squirtle", "Water", R.color.colorsquirtle));
        data.add(new Pokemon("Pikachu", "Electric", R.color.colorpikachu));
        return data;
    }

}