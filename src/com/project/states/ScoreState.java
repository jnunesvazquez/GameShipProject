package com.project.states;

import com.project.graphics.Assets;
import com.project.graphics.Text;
import com.project.io.ScoreData;
import com.project.math.Vector2D;
import com.project.ui.Action;
import com.project.ui.Button;
import com.project.ui.ButtonBuilder;
import constants.Constants;

import java.awt.*;
import java.sql.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ScoreState extends State{

    private Button returnButton;

    private PriorityQueue<ScoreData> highScores;

    private Comparator<ScoreData> scoreComparator;

    private ScoreData [] auxArray;

    Connection cn;

    public ScoreState() throws SQLException {

        cn = DriverManager.getConnection("jdbc:mysql://localhost/gameship", "root", "");
        PreparedStatement pst = cn.prepareStatement("select * from score");
        returnButton = new ButtonBuilder(
                Assets.greyButton,
                Assets.blueButton,
                Assets.greyButton.getHeight(),
                Constants.HEIGHT / 2 + Assets.greyButton.getHeight() * 3,
                "RETURN",
                new Action() {
                    @Override
                    public void doAction() {
                        State.changeState(new MenuState());
                        try {
                            pst.close();
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }
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

        try {
            ResultSet rs = pst.executeQuery();
            for (int i = 0; rs.next() == true; i++){
                highScores.add(new ScoreData(
                        rs.getInt("ID"),
                        rs.getString("nombre"),
                        rs.getString("nave"),
                        rs.getInt("oleada"),
                        rs.getInt("puntuacion")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ScoreState.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update() throws SQLException {
        returnButton.update();
    }

    @Override
    public void draw(Graphics g) {
        returnButton.draw(g);

        auxArray = highScores.toArray(new ScoreData[highScores.size()]);

        Arrays.sort(auxArray, scoreComparator);

        Vector2D namePos = new Vector2D(
                Constants.WIDTH / 2 - 200,
                100
        );
        Vector2D shipPos = new Vector2D(
                Constants.WIDTH / 2 - 50,
                100
        );
        Vector2D wavePos = new Vector2D(
                Constants.WIDTH / 2 + 100,
                100
        );
        Vector2D scorePos = new Vector2D(
                Constants.WIDTH / 2 + 250,
                100
        );

        Text.drawText(g, "SCORE", scorePos, true, Color.ORANGE, Assets.fontMed);
        Text.drawText(g, "NAME", namePos, true, Color.ORANGE, Assets.fontMed);
        Text.drawText(g, "SHIP", shipPos, true, Color.ORANGE, Assets.fontMed);
        Text.drawText(g, "WAVE", wavePos, true, Color.ORANGE, Assets.fontMed);

        scorePos.setY(scorePos.getY() + 40);
        namePos.setY(namePos.getY() + 40);
        shipPos.setY(shipPos.getY() + 40);
        wavePos.setY(wavePos.getY() + 40);

        for (int i = auxArray.length - 1; i > -1; i--){

            ScoreData d = auxArray[i];

            Text.drawText(g, Integer.toString(d.getScore()), scorePos, true, Color.WHITE, Assets.fontMed);
            Text.drawText(g, d.getName(), namePos, true, Color.WHITE, Assets.fontMed);
            Text.drawText(g, d.getShip(), shipPos, true, Color.WHITE, Assets.fontMed);
            Text.drawText(g, Integer.toString(d.getWave()), wavePos, true, Color.WHITE, Assets.fontMed);

            scorePos.setY(scorePos.getY() + 40);
            namePos.setY(namePos.getY() + 40);
            shipPos.setY(shipPos.getY() + 40);
            wavePos.setY(wavePos.getY() + 40);
        }
    }
}
