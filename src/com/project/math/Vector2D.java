package com.project.math;

/**
 * Clase para crear vetores
 */
public class Vector2D {

    private double x, y;            //Variables para calcular

    /**
     * Constructor
     * @param x Define la primera coordenada del vector
     * @param y Define la segunda coordenada del vector
     */
    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Constructor por defecto
     */
    public Vector2D() {
        x = 0;
        y = 0;
    }

    /**
     * Metodo para sumar vectores
     * @param v Vector2D
     * @return Vector2D
     */
    public Vector2D add(Vector2D v){
        return new Vector2D(x + v.getX(), y + v.getY());
    }

    /**
     * Metodo para restar vectores
     * @param v Vector2D
     * @return Vector2D
     */
    public Vector2D subtract(Vector2D v){
        return new Vector2D(x - v.getX(), y - v.getY());
    }

    /**
     * Metodo para escalar vectores segun un valor dado
     * @param value Valor a escalar
     * @return Vector2D
     */
    public Vector2D scale(double value){
        return new Vector2D(x * value, y * value);
    }

    /**
     * Metodo para definir el limite de un vector
     * @param value Valor a escalar
     * @return Vector2D
     */
    public Vector2D limit(double value) {
        if(getMagnitude() > value)
        {
            return this.normalize().scale(value);
        }
        return this;
    }

    /**
     * Metodo para definir un vector comun
     * @return Vector2D
     */
    public Vector2D normalize() {
        double magnitude = getMagnitude();
        return new Vector2D(x / magnitude, y / magnitude);
    }

    /**
     * Metodo para recoger la longitud de un vector
     * @return Vector2D
     */
    public double getMagnitude(){
        return Math.sqrt(x * x + y * y);
    }

    /**
     * Metodo para definir la direccion de un vector
     * @param angle Angulo del vector
     * @return  Vector2D
     */
    public Vector2D setDirection(double angle){
        double magnitude = getMagnitude();
        return new Vector2D(Math.cos(angle) * magnitude, Math.sin(angle) * magnitude);
    }

    /**
     * Getter
     * @return x
     */
    public double getX() {
        return x;
    }

    /**
     * Setter
     * @param x Define la primera coordenada del vector
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * Getter
     * @return y
     */
    public double getY() {
        return y;
    }

    /**
     * Setter
     * @param y Define la segunda coordenada del vector
     */
    public void setY(double y) {
        this.y = y;
    }
}
