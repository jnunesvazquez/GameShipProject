package com.project.scores;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * Clase para imprimir archivos donde guardar nuestras puntuaciones
 */
public class Files {
    /**
     * Con este metodo pido la ruta del usuario y le añado a la ruta Desktop
     * @return retorno la ruta del escritorio
     */
    private static String desktop() {
        return System.getProperty("user.home") + "\\Desktop\\";
    }

    /**
     * Con este metodo creo y escribo un fichero con el nombre y puntiacion del jugador
     * @param nameFile Aqui recive la ruta y nombre del archivo a creal
     * @param score Aqui recive la puntuacion que obtuvo en la partida
     */
    private static void writeScore(String nameFile, int score) {

        File fi = new File(nameFile + ".txt");
        PrintWriter esc = null;
        try {
            esc = new PrintWriter(fi);
            esc.println(JOptionPane.showInputDialog("Player name")+" "+ score);
        } catch (FileNotFoundException ex) {
            System.out.println("Erro escribirNumeros " + ex.toString());
        } finally {
            esc.close();
        }
    }

    /**
     * Este metodo lee el fichero ya esistente y en la ultima parte añade el nombre y la puntuacion del jugador
     * @param nomeFich Aqui recive la ruta y nombre del archivo
     * @param score Aqui recive la puntuacion que obtuvo en la partida
     */
    private static void readAndWrite(String nomeFich, int score) {
        PrintWriter f = null;
        File fich;
        try {
            FileWriter eng = new FileWriter(new File(nomeFich + ".txt"), true);
            f = new PrintWriter(eng);
            Score p = new Score(JOptionPane.showInputDialog("Player name"), score);
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
     * @param score recibe la puntuacion que consiguio el jugador en la partidda
     */
    public static void scoreWriteFinal(int score) {
        if (checkFile() == true) {
            writeAndOrderScore(desktop() + "Puntuacion", score);
        } else {
            writeScore(desktop() + "Puntuacion", score);
        }
    }

    /**
     * Metodo que lee, ecribe la nueva puntuación y ordena por puntuacion
     * @param nameFich nombre del fichero a usar
     * @param score puntuacion conseguida por el nuevo jugador
     */
    private static void writeAndOrderScore(String nameFich,int score){
        readAndWrite(nameFich,score);           //introducir nueva puntuación
        //Leer fichero e introducir valores en un array tipo Score
        File fich;
        Scanner sc=null;
        String line;
        List<Score> list=new ArrayList<Score>();
        fich=new File(nameFich+".txt");
        try {
            sc= new Scanner(fich);
            while(sc.hasNextLine()){
                line= sc.nextLine();
                String []separate=line.split(" ");
                Score al=new Score (separate[0],Integer.parseInt(separate[1]));
                list.add(al);
            }
        } catch (FileNotFoundException ex) {
            System.out.println("Error lectura "+ ex.toString());
        }finally{
            sc.close();
        }
        //Ordenar array
        Collections.sort(list);
        //Sobrescribir archivo con puntuaciones ordenadas
        PrintWriter f = null;
        try {
            FileWriter eng = new FileWriter(new File(nameFich + ".txt"), true);
            f = new PrintWriter(eng);
            f=new PrintWriter(fich);
            for(Score per:list){
                f.println(per);
            }

        } catch (IOException ex) {
            System.out.println("Erro" + ex.getMessage());
        } finally {
            f.close();
        }
    }
}
