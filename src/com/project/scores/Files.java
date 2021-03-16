package com.project.scores;


import javax.swing.*;
import java.io.*;

public class Files {
    /**
     * Con este metodo pido la ruta del usuario y le eñado a la ruta Desktop
     * @return retorno la ruta del escritorio
     */
    public static String desktop() {
        return System.getProperty("user.home") + "\\Desktop\\";
    }

    /**
     * Con este metodo creo y escribo un ficjero con el nombre y puntiacion del jugador
     * @param nameFile Aqui recive la ruta y nombre del archivo a creal
     * @param score Aqui recive la puntuacion que obtuvo en la partida
     */
    public static void writeScore(String nameFile, int score) {

        File fi = new File(nameFile + ".txt");
        PrintWriter esc = null;
        try {
            esc = new PrintWriter(fi);
            esc.println("Player= " + JOptionPane.showInputDialog("Player name") + ", Score= " + score);
        } catch (FileNotFoundException ex) {
            System.out.println("Erro escribirNumeros " + ex.toString());
        } finally {
            esc.close();
        }
    }

    /**
     * Este metodo lee el fichero ya esistente y en la ultima parte añade el nombre y la puntuacion del jugador
     * @param nomeFich Aqui recive la ruta y nombre del archivo
     * @param puntuacion Aqui recive la puntuacion que obtuvo en la partida
     */
    public static void readAndWrite(String nomeFich, int puntuacion) {
        PrintWriter f = null;
        File fich;
        try {
            FileWriter eng = new FileWriter(new File(nomeFich + ".txt"), true);
            f = new PrintWriter(eng);
            Score p = new Score(JOptionPane.showInputDialog("Player name"), puntuacion);
            f.println(p);
        } catch (IOException ex) {
            System.out.println("Erro" + ex.getMessage());
        } finally {
            f.close();
        }
    }

    /**
     * Este metodo comprueba si existe el erchivo donde guardar las puntuaciones
     * @return Retorna true si el fichero existe, retorna false en caso contrario
     */
    private static boolean checkFile() {

        String sFichero = desktop() + "Puntuacion.txt";
        File fichero = new File(sFichero);
        if (fichero.exists()) {
            return true;

        } else {
            return false;
        }
    }

    /**
     * Metodo que si el archivo donde guardar la partida existe se pondra en la ultima parte y escribira los nuevos
     * datos, en caso contrario creara el erchivo y escribira los datos
     * @param puntuacion recibe la puntuacion que consiguio el jugador en la partidda
     */
    private static void LecturaEscritura(int puntuacion) {
        if (checkFile() == true) {
            readAndWrite(desktop() + "Puntuacion", puntuacion);
        } else {
            writeScore(desktop() + "Puntuacion", puntuacion);
        }
    }
}
