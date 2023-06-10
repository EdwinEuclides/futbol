package com.futbol.Servicio.AccesoADatos.impl;

import org.apache.commons.io.FileUtils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import com.futbol.Servicio.AccesoADatos.accesoADatosServicio;

import com.futbol.domain.equipo;
import com.futbol.domain.jugador;

public class accesoADatosServicioImpl implements accesoADatosServicio {

    @Override
    public List<jugador> importarJugadores(List<equipo> equipos) {
        List<jugador> lstJugadores = new ArrayList<jugador>();

        String ruta = "src\\main\\java\\com\\futbol\\Resources\\jugadores.txt";
        try {
            List<String> lineas = FileUtils.readLines(new File(ruta), StandardCharsets.UTF_8);
            if (lineas.size() > 0) {

                String nomEq = "";// this.getEquipo(equipos, "eqnom");
                equipo eqAct = null;

                for (String linea : lineas) {

                    // String nombre, String apellido, equipo equipo, int altura, int posicion, int
                    // cantGoles, boolean esCapitan, int nroCamiseta
                    String[] sL = linea.split(";");
                    String ap = sL[0];
                    String nom = sL[1];

                    if (!nomEq.equals(sL[2])) {
                        nomEq = sL[2];
                        eqAct = this.getEquipo(equipos, nomEq);
                    }

                    int pos = Integer.parseInt(sL[3]);
                    int h = Integer.parseInt(sL[4]);
                    int cGoles = Integer.parseInt(sL[5]);
                    boolean esCap = Boolean.parseBoolean(sL[6]);
                    int nroCam = Integer.parseInt(sL[7]);
                    jugador j = new jugador(ap, nom, eqAct, pos, h, cGoles, esCap, nroCam);
                    lstJugadores.add(j);
                }
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return lstJugadores;
    }

    @Override
    public void exportarJugadores(List<equipo> equipos) {

        String ruta = "src\\main\\java\\com\\futbol\\Resources\\jugadores.txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ruta))) {

            for (equipo equipo : equipos) {
                for (jugador jugador : equipo.getJugadores()) {
                    // String nombre, String apellido, equipo equipo, int altura, int posicion, int
                    // cantGoles, boolean esCapitan, int nroCamiseta
                    String linea = jugador.getApellido() + ";" + jugador.getNombre() + ";" + equipo.getNombre() + ";"
                            + jugador.getAltura() +
                            ";" + jugador.getPosicion() + ";" + jugador.getCantGoles() + ";" + jugador.getEsCapitan()
                            + ";" + jugador.getNroCamiseta();

                    writer.write(linea);
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private equipo getEquipo(List<equipo> equipos, String nombre) {
        equipo equipoEncontrado = null;

        for (equipo equipo : equipos) {
            if (equipo.getNombre().equals(nombre)) {
                equipoEncontrado = equipo;
                break;
            }
        }

        return equipoEncontrado;
    }
}
