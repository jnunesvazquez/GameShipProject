package com.project.states.choosePlayer;

/**
 * Clase en la que implementamos nuestro Factory
 */
public class Factory {
    public static final int PLAYER_A = 1;
    public static final int PLAYER_B = 2;
    public static final int PLAYER_C = 3;

    /**
     * Metodo para escoger la nave
     * @param type  Tipo de nave
     * @return  Nueva nave
     */
    public static IChoose getPlayerFactory(int type){
        switch (type){
            case PLAYER_A:
                return new PlayerA();
            case PLAYER_B:
                return new PlayerB();
            case PLAYER_C:
                return new PlayerC();
            default:
                return null;
        }
    }
}
