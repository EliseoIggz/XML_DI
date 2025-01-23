package com.example.practica3.practicafxml.model;

public class Jugador {
    private String nombre;
    //private int tiempo;

    public Jugador(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

//    public int getTiempo() {
//        return tiempo;
//    }
//
//    public void setTiempo(int tiempo) {
//        this.tiempo = tiempo;
//    }

    /**
     * Método para validar que el nombre no esté vacío.
     * @return true o false
     */
    public boolean validarNombre(){
        if (nombre == null || nombre.trim().isEmpty()) {
            return false;
        }else{
            return true;
        }
    }
}

