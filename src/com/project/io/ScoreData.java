package com.project.io;

import com.project.gameObject.Player;
import com.project.states.choosePlayer.*;

public class ScoreData {

    private int id;
    private String name;
    private static String ship;
    private int score;
    private int wave;

    public ScoreData() {}

    public ScoreData(int id, int score){
        this.id = id;
        this.score = score;
        ship = getShip();
    }

    public String getShip(){
        String ship = "? Ship";
        if (Factory.getPlayerFactory(Factory.PLAYER_A) instanceof Player){
            ship = "Ship A";
        }
        else if (Factory.getPlayerFactory(Factory.PLAYER_B) instanceof Player){
            ship = "Ship B";
        }
        else if (Factory.getPlayerFactory(Factory.PLAYER_C) instanceof Player){
            ship = "Ship C";
        }
        return ship;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static void setShip(String ship) {
        ScoreData.ship = ship;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getWave() {
        return wave;
    }

    public void setWave(int wave) {
        this.wave = wave;
    }
}
