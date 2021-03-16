package com.project.scores;

public class Score {
    /**
     * @param name Nombre del jugador
     * @param Score Puntuacion que consiguio en la partida
     */
    private String name;
    private int score;

    public Score() {
    }

    /**
     *
     * @param name Nombre del jugador
     * @param score Puntuacion de la partida
     */
    public Score(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "name= " + name + ", score= " + score;
    }
}
