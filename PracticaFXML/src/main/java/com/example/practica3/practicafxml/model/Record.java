package com.example.practica3.practicafxml.model;

import java.io.Serializable;

public class Record implements Comparable<Record>, Serializable {
    private String nombre;
    private int tiempo;

    public Record(String nombre, int tiempo) {
        this.nombre = nombre;
        this.tiempo = tiempo;
    }

    public String getNombre() {
        return nombre;
    }

    public int getTiempo() {
        return tiempo;
    }

    @Override
    public int compareTo(Record o) {
        return Integer.compare(this.tiempo, o.tiempo);
    }

    @Override
    public String toString() {
        return nombre + " - " + tiempo + "s";
    }
}
