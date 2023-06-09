package com.futbol.Servicio.AccesoADatos.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
//import org.apache.commons.io.FileUtils;

import com.futbol.Servicio.AccesoADatos.accesoADatosServicio;
import com.futbol.domain.equipo;
import com.futbol.domain.jugador;

public class accesoADatosServicioImpl implements accesoADatosServicio {

    @Override
    public List<jugador> importarJugadores(List<equipo> equipos) {
        List<jugador> lstJugadores = new ArrayList<jugador>();

        String ruta = "src\\main\\java\\com\\futbol\\Resources\\importar.txt";

        return lstJugadores;

    }

    @Override
    public List<jugador> exportarJugadores(List<equipo> equipos) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'exportarJugadores'");
    }

}
