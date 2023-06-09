package com.futbol.Servicio.equipo;

import java.util.List;

import com.futbol.domain.equipo;
import com.futbol.domain.jugador;

public interface equipoServicio {

    equipo crearEquipo();

    void crearEquipos(List<equipo> equipos);

    List<jugador> crearListaJugadores(equipo equipo);

    void buscarJugadorXNom(List<equipo> equipos);

    void buscarEquipoJugadoresXNom(List<equipo> equipos);

    void buscarEquipoCapitanEntrenadorXNom(List<equipo> equipos);

}
