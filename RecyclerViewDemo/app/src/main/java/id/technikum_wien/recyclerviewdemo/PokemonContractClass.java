package id.technikum_wien.recyclerviewdemo;

import android.provider.BaseColumns;

public final class PokemonContractClass {

    private PokemonContractClass() {

    }

    public static class PokemonEntry implements BaseColumns {
        public static final String TABLE_NAME = "pokemon";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_TYPE = "type";
        public static final String COLUMN_NAME_COLOR_ID = "color_id";
        public static final String COLUMN_NAME_WEIGHT = "weight";
    }

}
