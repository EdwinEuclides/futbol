package com.futbol.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Equipo {
    private String nombre;
    private LocalDate fechaCreacion;
    private Entrenador entrenador;
    private List<Jugador> jugadores;

    public Equipo() {
        this.jugadores = new ArrayList<Jugador>();
    }

    public Equipo(String nombre, LocalDate fechaCreacion, Entrenador entrenador) {
        this.nombre = nombre;
        this.fechaCreacion = fechaCreacion;
        this.entrenador = entrenador;
        this.jugadores = new ArrayList<Jugador>();
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

    public Entrenador getEntrenador() {
        return entrenador;
    }

    public void setEntrenador(Entrenador entrenador) {
        this.entrenador = entrenador;
    }

    public List<Jugador> getJugadores() {
        return jugadores;
    }

    public void setJugadores(List<Jugador> jugadores) {
        this.jugadores = jugadores;
    }

    public boolean addJugador(Jugador jugador) {
        boolean rta = true;

        // Metodo para la clase service: Determinar un un jugador ya esta en la lista
        for (Jugador j : this.jugadores) {
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

    public void removeJugador(Jugador jugador) {
        if (this.jugadores.contains(jugador))
            this.jugadores.remove(jugador);
    }

    public Jugador getCapitan() {
        Jugador jEncontr = null;

        for (Jugador jugador : jugadores) {
            if (jugador.getEsCapitan()) {
                jEncontr = jugador;
                break;
            }
        }

        return jEncontr;
    }

}
