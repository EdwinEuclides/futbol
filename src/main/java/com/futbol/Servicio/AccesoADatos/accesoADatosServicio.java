package com.futbol.Servicio.AccesoADatos;

import java.util.List;

import com.futbol.domain.equipo;
import com.futbol.domain.jugador;

public interface accesoADatosServicio {

    List<jugador> importarJugadores(List<equipo> equipos);

    List<jugador> exportarJugadores(List<equipo> equipos);
}
