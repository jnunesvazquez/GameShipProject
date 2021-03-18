package com.project.states.choosePlayer;

public class Factory {
    public static final int PLAYER_A = 1;
    public static final int PLAYER_B = 2;
    public static final int PLAYER_C = 3;

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
