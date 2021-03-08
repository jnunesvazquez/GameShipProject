package com.project.graphics;

import javax.swing.*;

public class Window extends JFrame {                            //JFrame nos permitira crear nuestra ventana de juego

    public static final int WIDTH = 800, HEIGTH = 500;

    /**
     * Constructor de nuestra ventana
     */
    public Window(){
        setTitle("Destructor");                                 //Metodo para definir el titulo de la ventana
        setSize(WIDTH, HEIGTH);                                 //Metodo para definir el tamaño de la ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);         //Metodo que nos permite cerrar la ventana
        setResizable(false);                                    //Metodo que permite o no redimensionar la ventana
        setLocationRelativeTo(null);                            //Metodo para definir la visualizacion de la ventana en el centro de la pantalla
        setVisible(true);                                       //Metodo para que la ventana sea visible
    }
}
