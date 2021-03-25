package id.technikum_wien.recyclerviewdemo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class PokemonDbHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Pokemon.db";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + PokemonContractClass.PokemonEntry.TABLE_NAME + " (" +
                    PokemonContractClass.PokemonEntry._ID + " INTEGER PRIMARY KEY, " +
                    PokemonContractClass.PokemonEntry.COLUMN_NAME_NAME + " TEXT, " +
                    PokemonContractClass.PokemonEntry.COLUMN_NAME_TYPE + " TEXT, " +
                    PokemonContractClass.PokemonEntry.COLUMN_NAME_COLOR_ID + " INTEGER, " +
                    PokemonContractClass.PokemonEntry.COLUMN_NAME_WEIGHT + " REAL )";

    private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + PokemonContractClass.PokemonEntry.TABLE_NAME;

    public PokemonDbHelper(Context context) {
        super(
                context,
                DATABASE_NAME,
                null,
                DATABASE_VERSION
        );
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
}
