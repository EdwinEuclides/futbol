package com.futbol.Servicio.equipo;

import java.util.List;

import com.futbol.domain.equipo;
import com.futbol.domain.jugador;

public interface equipoServicio {
    
    equipo crearEquipo();

    void crearEquipos(List<equipo> equipos);

    List<jugador> crearListaJugadores(equipo equipo);
    
    jugador buscarJugador(List<equipo> equipos, String nomJugado);

}