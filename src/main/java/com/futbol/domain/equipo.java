package com.futbol.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class equipo {
    private String nombre;
    private LocalDate fechaCreacion;
    private entrenador entrenador;
    private List<jugador> jugadores;

    public equipo() {
        this.jugadores = new ArrayList<jugador>();
    }

    public equipo(String nombre, LocalDate fechaCreacion, entrenador entrenador) {
        this.nombre = nombre;
        this.fechaCreacion = fechaCreacion;
        this.entrenador = entrenador;
        this.jugadores = new ArrayList<jugador>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public entrenador getEntrenador() {
        return entrenador;
    }

    public void setEntrenador(entrenador entrenador) {
        this.entrenador = entrenador;
    }

    public List<jugador> getJugadores() {
        return jugadores;
    }

    public void setJugadores(List<jugador> jugadores) {
        this.jugadores = jugadores;
    }

    public boolean addJugador(jugador jugador) {
        boolean rta = true;

        // Metodo para la clase service: Determinar un un jugador ya esta en la lista
        for (jugador j : this.jugadores) {
            if (jugador.getId() == j.getId()) {
                rta = false;
                break;
            }
        }

        if (this.jugadores.size() == 5) {
            rta = false;
        }
        if (rta) {
            this.jugadores.add(jugador);
            // Metodo para service: Guardar Lista/Jugador en csv
        }

        return rta;
    }

    public void removeJugador(jugador jugador) {
        if (this.jugadores.contains(jugador))
            this.jugadores.remove(jugador);
    }

    public jugador getCapitan() {
        jugador jEncontr = null;

        for (jugador jugador : jugadores) {
            if (jugador.getEsCapitan()) {
                jEncontr = jugador;
                break;
            }
        }

        return jEncontr;
    }

}
