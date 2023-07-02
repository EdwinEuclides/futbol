package com.futbol.servicio.accesoADatos;

import java.util.List;

import com.futbol.domain.Equipo;

public interface AccesoADatosServicio {

    void importarJugadores(List<Equipo> equipos);

    void exportarJugadores(List<Equipo> equipos);
}
