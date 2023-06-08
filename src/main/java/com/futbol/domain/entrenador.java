package com.futbol.domain;

public class entrenador extends miembro {
    
    public entrenador(String nombre, String apellido, int edad) {
        super(nombre, apellido);
        this.edad = edad;
    }

    
    public entrenador(String nombre, String apellido, int edad, equipo equipo) {
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