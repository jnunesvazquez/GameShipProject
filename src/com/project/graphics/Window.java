package com.project.graphics;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

public class Window extends JFrame implements Runnable{         //JFrame nos permitira crear nuestra ventana de juego y Runnable para implementar un subproceso y no sobrecargar el JFrame

    public static final int WIDTH = 800, HEIGTH = 500;
    private boolean running;                                    //Variable que nos permite controlar el estado del juego

    private BufferStrategy bs;
    private Graphics g;                                         //Clase que nos permite dibujar

    private Canvas canvas;                                      //Instanciamos la clase canvas que nos permite dibujar sobre nuestra ventana o incluso captar eventos de teclado
    private Thread thread;                                      //Instanciamos la clase de hilos para crear un subproceso en el metodo run()

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

        canvas = new Canvas();

        canvas.setPreferredSize(new Dimension(WIDTH, HEIGTH));  //Metodo que define el tamaño de nuestro rectangulo
        canvas.setMaximumSize(new Dimension(WIDTH, HEIGTH));    //Metodo que define el tamaño maximo de nuestro rectangulo
        canvas.setMinimumSize(new Dimension(WIDTH, HEIGTH));    //Metodo que define el tamaño minimo de nuestro rectangulo
        canvas.setFocusable(true);                              //Metodo que permita interactuar con el rectangulo

        add(canvas);
    }

    public void update(){

    }

    public void draw(){
        bs = canvas.getBufferStrategy();                        //Le pasamos al canvas el buffer strategy pero nos devuelve un nulo
        if (bs == null) {
            canvas.createBufferStrategy(3);
            return;
        }
        g = bs.getDrawGraphics();
        g.dispose();
        bs.show();
    }

    private void init(){
        Assets.init();
    }

    @Override
    public void run() {
        while (running){
            update();
            draw();
        }
        stop();
    }

    public void start(){
        thread = new Thread(this);                      //Objeto de la clase Thread que recibe por parametro la implementacion de Runnable
        thread.start();                                         //Declaramos el comienzo del hilo
        running = true;
    }

    public void stop(){
        try {
            thread.join();                                      //El estado del hilo pasaria a un estado de espera
            running = false;
        } catch (InterruptedException e) {
            e.printStackTrace();                                //Metodo que imprime informacion de un error incluyendo las clases de donde proceden
        }
    }
}
