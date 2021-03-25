package id.technikum_wien.recyclerviewdemo;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.view.View;
import android.widget.Button;

import java.util.LinkedList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class MainActivity extends AppCompatActivity {


    private ListAdapter mAdapter;
    private Button btn_swap;
    private boolean bool_new = false;
    private List<Pokemon> lst_pokemon;

    PokemonDbHelper dbHelper;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView list = findViewById(R.id.rv_list);
        btn_swap = findViewById(R.id.btn_swap);

        lst_pokemon = generateContent();
        storeList(lst_pokemon);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        list.setLayoutManager(layoutManager);
        list.setHasFixedSize(true);
        mAdapter = new ListAdapter(getContentFromDb());
        list.setAdapter(mAdapter);

        mAdapter.setOnListItemClickListener(new ListAdapter.ListItemClickListener() {
            @Override
            public void onListItemClick(Pokemon item) {
                Intent i = new Intent(MainActivity.this,DetailActivity.class);
                i.putExtra(DetailActivity.EXTRA_POKEMON_KEY, item);
                startActivity(i);
            }
        });

        btn_swap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    private List<Pokemon> getContentFromDb() {
        List<Pokemon> data = new LinkedList<Pokemon>();
        // SELECT name, type, weight FROM tb_pokemon
        String[] projection = {
                BaseColumns._ID,
                PokemonContractClass.PokemonEntry.COLUMN_NAME_NAME,
                PokemonContractClass.PokemonEntry.COLUMN_NAME_TYPE,
                PokemonContractClass.PokemonEntry.COLUMN_NAME_WEIGHT,
                PokemonContractClass.PokemonEntry.COLUMN_NAME_COLOR_ID,
        };
        String sortOrder = PokemonContractClass.PokemonEntry.COLUMN_NAME_TYPE + " DESC";

        Cursor cursor = db.query(
                PokemonContractClass.PokemonEntry.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                sortOrder
        );

        while (cursor.moveToNext()) {
            data.add(new Pokemon(
                    cursor.getString(cursor.getColumnIndexOrThrow(PokemonContractClass.PokemonEntry.COLUMN_NAME_NAME)),
                    cursor.getString(cursor.getColumnIndexOrThrow(PokemonContractClass.PokemonEntry.COLUMN_NAME_TYPE)),
                    cursor.getDouble(cursor.getColumnIndexOrThrow(PokemonContractClass.PokemonEntry.COLUMN_NAME_WEIGHT)),
                    cursor.getInt(cursor.getColumnIndexOrThrow(PokemonContractClass.PokemonEntry.COLUMN_NAME_COLOR_ID))
            ));
        }

        cursor.close();
        return data;
    }

    private void storeList(List<Pokemon> lst_pokemon) {
        dbHelper = new PokemonDbHelper(this);
        db = dbHelper.getWritableDatabase();

        Cursor cursor = db.query(
                PokemonContractClass.PokemonEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null
        );

        int amount = cursor.getCount();

        if (amount == 0) {

            for (Pokemon p : lst_pokemon) {
                ContentValues values = new ContentValues();
                values.put(PokemonContractClass.PokemonEntry.COLUMN_NAME_NAME, p.getName());
                values.put(PokemonContractClass.PokemonEntry.COLUMN_NAME_TYPE, p.getType());
                values.put(PokemonContractClass.PokemonEntry.COLUMN_NAME_WEIGHT, p.getWeight());
                int color;
                switch(p.getType()) {
                    case "Water":
                        color = R.color.colorsquirtle;
                        break;
                    case "Grass":
                        color = R.color.colorbulbasaur;
                        break;
                    case "Fire":
                        color = R.color.colorcharmander;
                        break;
                    case "Electric":
                        color = R.color.colorpikachu;
                        break;
                    default:
                        color = R.color.design_default_color_error;
                        break;
                }
                values.put(PokemonContractClass.PokemonEntry.COLUMN_NAME_COLOR_ID, color);

                db.insert(PokemonContractClass.PokemonEntry.TABLE_NAME, null, values);
            }
        }
    }

    private List<Pokemon> generateContent(){
        List<Pokemon> data = new LinkedList<>();
        data.add(new Pokemon("Bulbasaur", "Grass", 1.78, R.color.colorbulbasaur));
        data.add(new Pokemon("Charmander", "Fire", 4.8,R.color.colorcharmander));
        data.add(new Pokemon("Squirtle", "Water",6.94, R.color.colorsquirtle));
        data.add(new Pokemon("Bulbasaur", "Grass",3.81, R.color.colorbulbasaur));
        data.add(new Pokemon("Charmander", "Fire",6.23, R.color.colorcharmander));
        data.add(new Pokemon("Squirtle", "Water",3.84, R.color.colorsquirtle));
        data.add(new Pokemon("Pikachu", "Electric",5.06, R.color.colorpikachu));
        data.add(new Pokemon("Squirtle", "Water",9.38, R.color.colorsquirtle));
        data.add(new Pokemon("Bulbasaur", "Grass",8.8, R.color.colorbulbasaur));
        data.add(new Pokemon("Squirtle", "Water",2.55, R.color.colorsquirtle));
        data.add(new Pokemon("Pikachu", "Electric",3.64, R.color.colorpikachu));
        data.add(new Pokemon("Squirtle", "Water",8.89, R.color.colorsquirtle));
        data.add(new Pokemon("Bulbasaur", "Grass",4.92, R.color.colorbulbasaur));
        data.add(new Pokemon("Charmander", "Fire",3.65, R.color.colorcharmander));
        data.add(new Pokemon("Squirtle", "Water",5.14, R.color.colorsquirtle));
        data.add(new Pokemon("Bulbasaur", "Grass",5.82, R.color.colorbulbasaur));
        data.add(new Pokemon("Charmander", "Fire",2.1, R.color.colorcharmander));
        data.add(new Pokemon("Squirtle", "Water",3.08, R.color.colorsquirtle));
        data.add(new Pokemon("Pikachu", "Electric",4.7, R.color.colorpikachu));
        data.add(new Pokemon("Squirtle", "Water",1.24, R.color.colorsquirtle));
        data.add(new Pokemon("Bulbasaur", "Grass",6.01, R.color.colorbulbasaur));
        data.add(new Pokemon("Squirtle", "Water",8.66, R.color.colorsquirtle));
        data.add(new Pokemon("Pikachu", "Electric",6.96, R.color.colorpikachu));
        data.add(new Pokemon("Squirtle", "Water",7.33, R.color.colorsquirtle));
        data.add(new Pokemon("Bulbasaur", "Grass",8.74, R.color.colorbulbasaur));
        data.add(new Pokemon("Charmander", "Fire",3.65, R.color.colorcharmander));
        data.add(new Pokemon("Squirtle", "Water",1.18, R.color.colorsquirtle));
        data.add(new Pokemon("Pikachu", "Electric",6.64, R.color.colorpikachu));
        data.add(new Pokemon("Squirtle", "Water",8.15, R.color.colorsquirtle));
        data.add(new Pokemon("Pikachu", "Electric",1.09, R.color.colorpikachu));
        data.add(new Pokemon("Pikachu", "Electric",7.28, R.color.colorpikachu));
        data.add(new Pokemon("Squirtle", "Water",5.43, R.color.colorsquirtle));
        data.add(new Pokemon("Pikachu", "Electric",9.08, R.color.colorpikachu));
        data.add(new Pokemon("Pikachu", "Electric",3.41, R.color.colorpikachu));
        data.add(new Pokemon("Squirtle", "Water",7.06, R.color.colorsquirtle));
        data.add(new Pokemon("Bulbasaur", "Grass",4.37, R.color.colorbulbasaur));
        data.add(new Pokemon("Squirtle", "Water",4.85, R.color.colorsquirtle));
        data.add(new Pokemon("Pikachu", "Electric",2.06, R.color.colorpikachu));
        data.add(new Pokemon("Squirtle", "Water",9.95, R.color.colorsquirtle));
        data.add(new Pokemon("Bulbasaur", "Grass",6.47, R.color.colorbulbasaur));
        data.add(new Pokemon("Charmander", "Fire",3.33, R.color.colorcharmander));
        data.add(new Pokemon("Squirtle", "Water",8.04, R.color.colorsquirtle));
        data.add(new Pokemon("Pikachu", "Electric",1.06, R.color.colorpikachu));
        data.add(new Pokemon("Squirtle", "Water",5.94, R.color.colorsquirtle));
        data.add(new Pokemon("Pikachu", "Electric",4.52, R.color.colorpikachu));
        return data;
    }

}