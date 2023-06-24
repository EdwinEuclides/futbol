package com.futbol.servicio.equipo;

import java.util.List;
import com.futbol.domain.Equipo;
import com.futbol.domain.Jugador;

public interface EquipoServicio {

    Equipo crearEquipo();

    void crearEquipos(List<Equipo> equipos);

    List<Jugador> crearListaJugadores(Equipo equipo);

    void buscarJugadorXNom(List<Equipo> equipos);

    void buscarEquipoJugadoresXNom(List<Equipo> equipos);

    void buscarEquipoCapitanEntrenadorXNom(List<Equipo> equipos);

    void EliminarEquipo(List<Equipo> equipos);
}
