package com.project.scores;

public class Score implements Comparable{
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
        return  name + " " + score;
    }

    /**
     * Metodo que ordena por puntuacion de mayor a menor
     * @param o
     * @return
     */
    @Override
    public int compareTo(Object o) {
        Score scor=(Score) o;
        if(this.score<scor.score){
            return 1;
        }else if(this.score>scor.score){
            return -1;
        }else
            return 0;
    }
}
