package com.futbol.domain;

import java.util.UUID;

public class miembro {
    private UUID id;
    private String nombre;
    private String apellido;
    private equipo equipo;

    public miembro(String nombre, String apellido) {
        this.id = UUID.randomUUID();
        this.nombre = nombre;
        this.apellido = apellido;
        this.equipo = new equipo();
    }

    public miembro(String nombre, String apellido, equipo equipo) {
        this.id = UUID.randomUUID();
        this.nombre = nombre;
        this.apellido = apellido;
        this.equipo = equipo;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public equipo getEquipo() {
        return equipo;
    }
    public void setEquipo(equipo equipo) {
        this.equipo = equipo;
    }

    @Override
    public String toString() {
        return "miembro [nombre=" + nombre + ", apellido=" + apellido + ", equipo=" + equipo + "]";
    }

    
    
}
