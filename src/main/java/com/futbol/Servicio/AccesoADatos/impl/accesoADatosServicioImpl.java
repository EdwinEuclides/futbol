package com.futbol.servicio.accesoADatos.impl;

import org.apache.commons.io.FileUtils;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import com.futbol.servicio.jugador.JugadorServicio;
import com.futbol.servicio.jugador.impl.JugadorServicioImpl;
import com.futbol.domain.Equipo;
import com.futbol.servicio.accesoADatos.AccesoADatosServicio;
import com.futbol.domain.Jugador;

public class AccesoADatosServicioImpl implements AccesoADatosServicio {

    @Override
    public void importarJugadores(List<Equipo> equipos) {

        String ruta = "src\\main\\java\\com\\futbol\\Resources\\jugadores.txt";
        try {
            List<String> lineas = FileUtils.readLines(new File(ruta), StandardCharsets.UTF_8);
            List<Equipo> equiposIncompletos = this.getEquiposIncompletos(equipos);

            if (!validarParaImportar(lineas, equiposIncompletos))
                return;

            JugadorServicio jugadorServ = new JugadorServicioImpl();

            for (Equipo equipo : equiposIncompletos) {

                for (String linea : lineas) {
                    String[] sL = linea.split(";");

                    if (equipo.getNombre().equals(sL[2].toString())) {
                        String ap = sL[0].toString();
                        String nom = sL[1].toString();
                        int pos = Integer.parseInt(sL[3]);
                        int h = Integer.parseInt(sL[4]);
                        int cGoles = Integer.parseInt(sL[5]);
                        boolean esCap = Boolean.parseBoolean(sL[6]);
                        int nroCam = Integer.parseInt(sL[7]);
                        Jugador j = jugadorServ.crearJugador(nom, ap, h, pos, cGoles, esCap, nroCam, equipo);
                        equipo.getJugadores().add(j);
                    }
                    if (equipo.getJugadores().size() == 5)
                        break;
                }
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void exportarJugadores(List<Equipo> equipos) {

        if (equipos.size() == 0) {
            System.out.println("No hay Equipo para exportar sus jugadores.");
            return;
        }

        String ruta = "src\\main\\java\\com\\futbol\\Resources\\jugadores.txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ruta))) {

            for (Equipo equipo : equipos) {
                for (Jugador jugador : equipo.getJugadores()) {

                    String linea = jugador.getApellido() + ";" + jugador.getNombre() + ";" + equipo.getNombre() + ";"
                            + jugador.getAltura() + ";" + jugador.getPosicion() + ";" + jugador.getCantGoles() + ";"
                            + jugador.getEsCapitan() + ";" + jugador.getNroCamiseta();

                    writer.write(linea);
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    // -------- Metodos Privados (funciones auxiliares de la clase)

    private List<Equipo> getEquiposIncompletos(List<Equipo> equipos) {
        List<Equipo> equiposRta = new ArrayList<Equipo>();

        for (Equipo equipo : equipos)
            if (equipo.getJugadores().size() < 5)
                equiposRta.add(equipo);

        return equiposRta;
    }

    private boolean validarParaImportar(List<String> lineas, List<Equipo> equiposIncompl) {

        if (lineas.size() == 0)
            System.out.println("No hay jugadores para importar.");

        if (equiposIncompl.size() == 0)
            System.out.println("No hay ningún Equipo al que le fanten jugadores. No se puede importar");

        return lineas.size() > 0 && equiposIncompl.size() > 0;
    }
}
