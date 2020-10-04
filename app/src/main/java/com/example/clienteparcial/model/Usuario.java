package com.example.clienteparcial.model;

public class Usuario {

    String name;
    int numero;
    String id;

    public Usuario() {
    }

    public Usuario(String name, int numero, String id) {
        this.name = name;
        this.numero = numero;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
