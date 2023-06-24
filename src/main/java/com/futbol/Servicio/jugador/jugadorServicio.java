package com.futbol.servicio.jugador;

import com.futbol.domain.Equipo;
import com.futbol.domain.Jugador;

public interface JugadorServicio {

    Jugador crearJugador(Equipo equipo);

    Jugador crearJugador(String nombre, String apellido, int altura, int posicion, int cantGoles, boolean esCapitan,
            int nroCamiseta, Equipo equipo);
}
