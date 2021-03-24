package com.example.bshomework3;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MagicCard implements Parcelable {
    private static final String LOG_TAG = MainActivity.class.getCanonicalName();

    protected MagicCard(Parcel in) {
        name = in.readString();
        type = in.readString();
        rarity = in.readString();
        colors = in.readString();
        color = in.readInt();
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getRarity() {
        return rarity;
    }

    public int getColor() {
        return color;
    }

    public String getColors() {
        return colors;
    }

    private String name;
    private String type;
    private String rarity;
    private String colors;
    private int color;

    public MagicCard(JSONObject card) {
        try{
            this.name = card.getString("name");
            this.type = card.getString("type");
            this.rarity = card.getString("rarity");

            JSONArray colors = card.getJSONArray("colors");
            color = getColorIndex(colors.getString(0));
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
            this.colors = colorsString.toString();
            Log.d(LOG_TAG, "Card created with name: " + name);
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

    private int getColorIndex(String colorName) {
        switch (colorName) {
            case "White":
                return R.color.white;
            case "Black":
                return R.color.black;
            case "Red":
                return R.color.red;
            case "Blue":
                return R.color.blue;
            case "Green":
                return R.color.green;
            default:
                return R.color.design_default_color_background;
        }
    }

    public static final Creator<MagicCard> CREATOR = new Creator<MagicCard>() {
        @Override
        public MagicCard createFromParcel(Parcel in) {
            return new MagicCard(in);
        }

        @Override
        public MagicCard[] newArray(int size) {
            return new MagicCard[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(type);
        parcel.writeInt(color);
    }
}
