package com.project.gameObject;

public class Timer {
    private long delta, lastTime, time;                   //Delta acumulara el tiempo que lleva lastTime
    private boolean running;

    public Timer() {
        delta = 0;
        lastTime = 0;
        running = false;
    }

    public void run(long time){
        running = true;
        this.time = time;
    }

    public void update(){
        if (running){
            delta += System.currentTimeMillis() - lastTime;
        }
        if (delta >= time){
            running = false;
            delta = 0;
        }
        lastTime = System.currentTimeMillis();
    }

    public boolean isRunning(){
        return running;
    }
}
