package com.example.clienteparcial.model;

public class Coordenadas {

    int x;
    int y;
    int r;
    int g;
    int b;
    String id;

    public Coordenadas(int x, int y, int r,int g, int b,String id) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.g=g;
        this.b =b;
        this.id = id;
    }


    public Coordenadas() {
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getR() {
        return r;
    }

    public void setR(int r) {
        this.r = r;
    }

    public int getG() {
        return g;
    }

    public void setG(int g) {
        this.g = g;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }
}
