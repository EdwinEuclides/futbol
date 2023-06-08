package com.futbol.Servicio.equipo.impl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.futbol.App;
import com.futbol.Servicio.Entrenador.entrenadorServicio;
import com.futbol.Servicio.Entrenador.impl.entrenadorServicioImpl;
import com.futbol.Servicio.equipo.equipoServicio;
import com.futbol.Servicio.jugador.impl.jugadorServicioImpl;
import com.futbol.domain.entrenador;
import com.futbol.domain.equipo;
import com.futbol.domain.jugador;

public final class equipoServicioImpl implements equipoServicio {

    public void crearEquipos(List<equipo> lstEquipos)
    {
        Scanner sc= App.sc;
        boolean salir =false;
        do {
            lstEquipos.add(this.crearEquipo());
            System.out.println("Desea crear otro Equipo?    0: No      1: Si :");
            
            if("0".equals(sc.nextLine()))
                salir = true;
        } while (!salir);
    }

    @Override
    public equipo crearEquipo() {
        equipo equipo = new equipo();
        Scanner sc = App.sc;

        System.out.println("Nombre del Equipo: ");
        equipo.setNombre(sc.nextLine());

        System.out.println("Fecha de Creación del Equipo (dd-mm-aaaa): ");
        String strFech = sc.nextLine();
        DateTimeFormatter formateador = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate fechaCreacion = LocalDate.parse(strFech, formateador);
        equipo.setFechaCreacion(fechaCreacion);

        entrenadorServicio entrServ = new entrenadorServicioImpl();
        entrenador entrenador = entrServ.crearEntreandor(equipo);
        equipo.setEntrenador(entrenador);
        
        List<jugador> jugadoresList = this.crearListaJugadores(equipo);
        equipo.setJugadores(jugadoresList);
        return equipo;
    }


    @Override
    public jugador buscarJugador(List<equipo> equipos, String nomJugado) {
        jugador jEncontrado = null;

        for (equipo equipo : equipos) {

            List<jugador> jugadors = equipo.getJugadores();

            for (jugador jugador : jugadors) {
                if (jugador.getNombre().equals(nomJugado)) {
                    jEncontrado = jugador;
                    return jEncontrado;
                }
            }
        }

        return jEncontrado;
    }

    @Override
    public List<jugador> crearListaJugadores(equipo equipo) {
        Scanner sc = App.sc;
        List<jugador> lst = new ArrayList<jugador>();
        boolean salir = false;
        jugadorServicioImpl servicio = new jugadorServicioImpl();

        System.out.println("\nAgregará ahora los datos de los jugadores del equipo.\n");
        do {
            jugador j = servicio.crearJugador(equipo);
            if (j != null) {
                lst.add(j);
            }
            
            System.out.println("Agregar otro Jugador?    0: No      1: Si :");
            String siguienteJugador = sc.nextLine();
            salir = siguienteJugador.equals("0") ? true : false;
        } while (!salir);

        return lst;
    }

}
