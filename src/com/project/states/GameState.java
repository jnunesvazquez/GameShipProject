package com.project.states;

import com.project.gameObject.MovingObject;
import com.project.gameObject.Player;
import com.project.graphics.Assets;
import com.project.math.Vector2D;
import constants.Constants;

import java.awt.*;
import java.util.ArrayList;

public class GameState {

    private Player player;
    private ArrayList<MovingObject> movingObjects = new ArrayList();

    public GameState() {
        player = new Player(new Vector2D(Constants.WIDTH/2 - Assets.player.getWidth()/2,
                Constants.HEIGHT/2 - Assets.player.getHeight()/2), new Vector2D(), Constants.PLAYER_MAX_VEL, Assets.player, this);
        movingObjects.add(player);
    }

    public void update(){
        for (int i = 0; i < movingObjects.size(); i++){
            movingObjects.get(i).update();
        }
    }
    public void draw(Graphics g){

        for (int i = 0; i < movingObjects.size(); i++) {
            movingObjects.get(i).draw(g);
        }
    }

    public ArrayList<MovingObject> getMovingObjects() {
        return movingObjects;
    }
}
