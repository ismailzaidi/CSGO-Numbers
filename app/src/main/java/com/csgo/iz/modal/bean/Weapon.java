package com.csgo.iz.modal.bean;

import android.os.Parcel;
import android.os.Parcelable;

public class Weapon implements Comparable<Weapon>, Parcelable {

    public final int id;
    public final String type;
    public final String name;
    public final int kills;
    public final int hit;
    public final int accuracy;
    public final int missedShots;
    public final int shots;

    public Weapon(int id, String type, String name, int kills, int hit,
                  int accuracy, int missedShots, int shots) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.kills = kills;
        this.hit = hit;
        this.accuracy = accuracy;
        this.missedShots = missedShots;
        this.shots = shots;
    }

    @Override
    public String toString() {
        return "Weapon [id=" + id + ", name=" + name + ", kills=" + kills
                + ", hit=" + hit + ", accuracy=" + accuracy + ", missedShots=" + missedShots
                + ", shots=" + shots + "]";
    }

    @Override
    public int compareTo(Weapon another) {
        return this.name.compareTo(another.name);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(type);
        dest.writeString(name);
        dest.writeInt(kills);
        dest.writeInt(hit);
        dest.writeInt(accuracy);
        dest.writeInt(missedShots);
        dest.writeInt(shots);
    }

    public static Parcelable.Creator<Weapon> CREATOR = new Creator<Weapon>() {
        @Override
        public Weapon createFromParcel(Parcel source) {
            int id = source.readInt();
            String type = source.readString();
            String name = source.readString();
            int kills = source.readInt();
            int hit = source.readInt();
            int accuracy = source.readInt();
            int missedShots = source.readInt();
            int shots = source.readInt();
            return new Weapon(id, type, name, kills, hit, accuracy, missedShots, shots);
        }

        @Override
        public Weapon[] newArray(int size) {
            return new Weapon[size];
        }
    };
}
