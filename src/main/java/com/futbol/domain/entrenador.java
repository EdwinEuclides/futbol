package com.futbol.domain;

public class Entrenador extends Miembro {

    public Entrenador(String nombre, String apellido, int edad) {
        super(nombre, apellido);
        this.edad = edad;
    }

    public Entrenador(String nombre, String apellido, int edad, Equipo equipo) {
        super(nombre, apellido, equipo);
        this.edad = edad;
    }

    private int edad;

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

}