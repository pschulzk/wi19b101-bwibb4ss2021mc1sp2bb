package id.technikum_wien.recyclerviewdemo;

import android.os.Parcel;
import android.os.Parcelable;

public class Pokemon implements Parcelable {

    private String name;
    private String type;
    private double weight;
    private int color;

    public Pokemon(String name, String type, double weight, int color) {
        this.name = name;
        this.type = type;
        this.weight = weight;
        this.color = color;
    }

    protected Pokemon(Parcel in) {
        name = in.readString();
        type = in.readString();
        weight = in.readDouble();
        color = in.readInt();
    }

    public static final Creator<Pokemon> CREATOR = new Creator<Pokemon>() {
        @Override
        public Pokemon createFromParcel(Parcel in) {
            return new Pokemon(in);
        }

        @Override
        public Pokemon[] newArray(int size) {
            return new Pokemon[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(type);
        dest.writeDouble(weight);
        dest.writeInt(color);
    }
}
