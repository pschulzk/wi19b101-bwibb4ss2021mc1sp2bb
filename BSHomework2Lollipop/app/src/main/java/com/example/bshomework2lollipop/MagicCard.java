package com.example.bshomework2lollipop;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MagicCard {
    private static final String LOG_TAG = MainActivity.class.getCanonicalName();

    private String name;
    private String type;
    private String rarity;
    private String colors;

    public MagicCard(JSONObject card) {
        try{
            this.name = card.getString("name");
            this.type = card.getString("type");
            this.rarity = card.getString("rarity");

            JSONArray colors = card.getJSONArray("colors");
            StringBuilder colorsString = new StringBuilder();
            colorsString.append("[ ");
            for(int i = 0; i < colors.length(); i++){
                String colorString = colors.getString(i);
                colorsString.append(colorString);
                if (i + 1 < colors.length()) {
                    colorsString.append(" ,");
                }
            }
            colorsString.append(" ]");
            Log.d(LOG_TAG, "!!! MagicCard.colors: " + colorsString.toString());
            this.colors = colorsString.toString();

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getMessageData() {
        return "Card with name " + this.name + "has:\n"
            + "Type: " + this.type + "\n"
            + "Rarity: " + this.rarity + "\n"
            + "Colors: " + this.colors + "\n\n";
    }
}
