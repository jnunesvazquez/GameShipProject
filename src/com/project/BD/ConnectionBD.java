package com.project.BD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class ConnectionBD {
    private static String bd = "gameship";
    private static String user = "root";
    private static String pass = "";
    private static String url = "jdbc:mysql://localhost/" + bd;

    /**
     * Metodo que comprueba la conexion a la base de datos
     *
     * @return si retorna un null indica que no hay conexion a la BD, cualquier otra cosa indica que si hay conexion a la BD
     */
    public Connection getConnectionBD() {
        Connection cn = null;                                           //Lo declaro como null
        try {
            cn = DriverManager.getConnection(url, user, pass);         // si hay conexion le doy un nuevo valor
        } catch (Exception e) {
            System.out.println("Error en conexion " + e.getMessage());
        }
        return cn;
    }

    /**
     * @param score Puntación de la partida
     * @param waves Oleada maxima de la partida
     * @param name  Nombre del jugador
     * @param ship  Tipo de nave que se jugó la partida
     */
    public void addScoreBD(int score, int waves, String name, String ship) {

        try {
            Connection cn = DriverManager.getConnection(url, user, pass);                          //conecto con la BD
            //ID,  nombre, nave, oleada, puntuacion
            PreparedStatement pst = cn.prepareStatement("insert into score values(?,?,?,?,?)"); //inserto los nuevos valores
            pst.setString(1, "0");                                                  //ID
            pst.setString(2, name);                                                    //Nombre Jugador
            pst.setString(3, ship);                                                    //Tipo nave
            pst.setInt(4, score);                                                      //Puntuacion de la partida
            pst.setInt(5, waves);                                                      //oleadas aguantadas en la partida
            pst.executeUpdate();
        } catch (Exception e) {
            System.out.println("Error en conexion " + e.getMessage());
        }

    }
}
