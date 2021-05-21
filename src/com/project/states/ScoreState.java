package com.project.states;

import com.project.graphics.Assets;
import com.project.graphics.Text;
import com.project.io.ScoreData;
import com.project.math.Vector2D;
import com.project.states.choosePlayer.PlayerB;
import com.project.ui.Action;
import com.project.ui.Button;
import com.project.ui.ButtonBuilder;
import constants.Constants;

import java.awt.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class ScoreState extends State{

    private Button returnButton;

    private PriorityQueue<ScoreData> highScores;

    private Comparator<ScoreData> scoreComparator;

    private ScoreData [] auxArray;

    public ScoreState(){
        returnButton = new ButtonBuilder(
                Assets.greyButton,
                Assets.blueButton,
                Assets.greyButton.getHeight(),
                Constants.HEIGHT / 2 + Assets.greyButton.getHeight() * 3,
                Constants.RETURN,
                new Action() {
                    @Override
                    public void doAction() {
                        State.changeState(new MenuState());
                    }
                })
                .build();

        scoreComparator = new Comparator<ScoreData>() {
            @Override
            public int compare(ScoreData s1, ScoreData s2) {
                return Integer.compare(s1.getScore(), s2.getScore());
            }
        };
        highScores = new PriorityQueue<ScoreData>(10, scoreComparator);

        highScores.add(new ScoreData(1,1000));
    }

    @Override
    public void update() {
        returnButton.update();
    }

    @Override
    public void draw(Graphics g) {
        returnButton.draw(g);

        auxArray = highScores.toArray(new ScoreData[highScores.size()]);

        Arrays.sort(auxArray, scoreComparator);

        Vector2D scorePos = new Vector2D(
                Constants.WIDTH / 2 - 200,
                100
        );
        Vector2D idPos = new Vector2D(
                Constants.WIDTH / 2 - 300,
                100
        );

        Text.drawText(g, "SCORE", scorePos, true, Color.ORANGE, Assets.fontMed);
        Text.drawText(g, "ID", idPos, true, Color.ORANGE, Assets.fontMed);

        scorePos.setY(scorePos.getY() + 40);
        idPos.setY(idPos.getY() + 40);

        for (int i = auxArray.length - 1; i > -1; i--){

            ScoreData d = auxArray[i];

            Text.drawText(g, Integer.toString(d.getScore()), scorePos, true, Color.WHITE, Assets.fontMed);
            Text.drawText(g, Integer.toString(d.getId()), idPos, true, Color.WHITE, Assets.fontMed);

            scorePos.setY(scorePos.getY() + 40);
            idPos.setY(idPos.getY() + 40);
        }
    }
}
