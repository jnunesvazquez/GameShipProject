package com.project.graphics;

import com.project.input.KeyBoard;
import com.project.input.MouseInput;
import com.project.states.MenuState;
import com.project.states.State;
import constants.Constants;

import javax.swing.JFrame;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

public class Window extends JFrame implements Runnable{         //JFrame nos permitira crear nuestra ventana de juego y Runnable para implementar un subproceso y no sobrecargar el JFrame

    private boolean running = false;                            //Variable que nos permite controlar el estado del juego

    private BufferStrategy bs;                                  //Clase que nos permite crear buffers
    private Graphics g;                                         //Clase que nos permite dibujar

    private Canvas canvas;                                      //Instanciamos la clase canvas que nos permite dibujar sobre nuestra ventana o incluso captar eventos de teclado
    private Thread thread;                                      //Instanciamos la clase de hilos para crear un subproceso en el metodo run()

    private final int FPS = 60;
    private double TARGETTIME = 1000000000/FPS;                 //Variable que indica el tiempo necesario para aumentar un fotograma
    private double delta = 0;                                   //Variable que almacena el tiempo transcurrido dentro de nuestro juego
    private int AVERAGEFPS = FPS;                               //Variable que indica a cuantos FPS funciona nuestro juego en un momento

    private KeyBoard keyBoard;
    private MouseInput mouseInput;

    /**
     * Constructor de nuestra ventana
     */
    public Window(){
        setTitle("Destructor");                                 //Metodo para definir el titulo de la ventana
        setSize(Constants.WIDTH, Constants.HEIGHT);             //Metodo para definir el tamaño de la ventana
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);         //Metodo que nos permite cerrar la ventana
        setResizable(false);                                    //Metodo que permite o no redimensionar la ventana
        setLocationRelativeTo(null);                            //Metodo para definir la visualizacion de la ventana en el centro de la pantalla


        canvas = new Canvas();
        keyBoard = new KeyBoard();
        mouseInput = new MouseInput();

        canvas.setPreferredSize(new Dimension(Constants.WIDTH, Constants.HEIGHT));  //Metodo que define el tamaño de nuestro rectangulo
        canvas.setMaximumSize(new Dimension(Constants.WIDTH, Constants.HEIGHT));    //Metodo que define el tamaño maximo de nuestro rectangulo
        canvas.setMinimumSize(new Dimension(Constants.WIDTH, Constants.HEIGHT));    //Metodo que define el tamaño minimo de nuestro rectangulo
        canvas.setFocusable(true);                              //Metodo que permita interactuar con el rectangulo

        add(canvas);
        canvas.addKeyListener(keyBoard);                        //Añadimos la interaccion por teclado a la ventana
        canvas.addMouseListener(mouseInput);                    //Añadimos la interaccion por raton a la ventana
        canvas.addMouseMotionListener(mouseInput);              //Añadimos el detector de movimiento al programa
        setVisible(true);                                       //Metodo para que la ventana sea visible
    }

    /**
     * Metodo que nos permite actualizar las interacciones o procesos secundarios durante el proceso principal
     */
    public void update() {
        keyBoard.update();
        State.getCurrentState().update();
    }

    /**
     * Metodo para dibujar con doble buffering e introducirlo en nuestro gameState
     */
    public void draw(){
        bs = canvas.getBufferStrategy();                        //Le pasamos al canvas el buffer strategy pero nos devuelve un nulo
        if (bs == null) {
            canvas.createBufferStrategy(3);
            return;
        }
        g = bs.getDrawGraphics();
        //------------------------------
        //Aqui dibujamos
        g.setColor(Color.BLACK);

        g.fillRect(0, 0, Constants.WIDTH, Constants.HEIGHT);

        State.getCurrentState().draw(g);

        g.setColor(Color.WHITE);

        g.drawString("" + AVERAGEFPS, 10, 20);

        //------------------------------
        g.dispose();
        bs.show();
    }

    /**
     * Metodo para iniciar los assets dentro de nuestro estado de juego
     */
    private void init(){
        Assets.init();
        State.changeState(new MenuState());
    }

    /**
     * Metodo para hacer correr a nuestro hilo a 60 fotogramas por segundo
     */
    @Override
    public void run() {
        long now;                                               //Variable que registra el tiempo
        long lastTime = System.nanoTime();                      //Variable que nos devuelve la hora exacta del sistema en nanosegundos
        int frames = 0;
        long time = 0;

        init();

        //este bucle restringe el tiempo a 60 fps
        while (running){
            now = System.nanoTime();
            delta += (now - lastTime) / TARGETTIME;                          //Sumamos el tiempo que haya pasado hasta este momento
            time += (now - lastTime);
            lastTime = now;
            if (delta >= 1){
                update();
                draw();
                delta--;
                frames++;
            }
            if (time >= 1000000000){
                AVERAGEFPS = frames;
                frames = 0;
                time = 0;
            }
        }
        stop();
    }

    /**
     * Metodo para empezar un hilo
     */
    public void start(){
        thread = new Thread(this);                      //Objeto de la clase Thread que recibe por parametro la implementacion de Runnable
        thread.start();                                         //Declaramos el comienzo del hilo
        running = true;
    }

    /**
     * Metodo para detener nuestro hilo
     */
    public void stop(){
        try {
            thread.join();                                      //El estado del hilo pasaria a un estado de espera
            running = false;
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());;                               //Metodo que imprime informacion de un error incluyendo las clases de donde proceden
        }
    }
}
