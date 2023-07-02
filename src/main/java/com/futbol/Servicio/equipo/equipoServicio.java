package com.futbol.servicio.equipo;

import java.util.List;
import com.futbol.domain.Equipo;

public interface EquipoServicio {

    Equipo crearEquipo();

    void crearEquipos(List<Equipo> equipos);

    void buscarJugadorYMostrarSusDatos(List<Equipo> equipos);

    void buscarEquipoYMostrarNombreEntrenadoCapitan(List<Equipo> equipos);

    void buscarEquipoMostrarSusDatosYJugadores(List<Equipo> equipos);

    void EliminarEquipo(List<Equipo> equipos);
}
