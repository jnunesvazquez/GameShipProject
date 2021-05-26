package com.project.io;

import com.project.gameObject.Player;
import com.project.states.choosePlayer.*;

public class ScoreData {

    private int id;
    private String name;
    private String ship;
    private int score;
    private int wave;

    public ScoreData() {}

    public ScoreData(int id, String name, String ship, int wave, int score){
        this.id = id;
        this.score = score;
        this.ship = ship;
        this.name = name;
        this.wave = wave;
    }

    public String getShip(){
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

    public void setShip(String ship) {
        this.ship = ship;
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
