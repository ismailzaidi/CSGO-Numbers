package com.csgo.iz.modal.bean;

import java.io.Serializable;

public class Weapon implements Comparable<Weapon>, Serializable {

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
}
