package com.futbol.servicio.equipo.impl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.futbol.App;
import com.futbol.domain.Entrenador;
import com.futbol.domain.Equipo;
import com.futbol.domain.Jugador;
import com.futbol.servicio.entrenador.EntrenadorServicio;
import com.futbol.servicio.entrenador.impl.EntrenadorServicioImpl;
import com.futbol.servicio.equipo.EquipoServicio;
import com.futbol.servicio.jugador.impl.JugadorServicioImpl;

public final class EquipoServicioImpl implements EquipoServicio {

    @Override
    public void crearEquipos(List<Equipo> lstEquipos) {
        Scanner sc = App.sc;
        boolean crearOtro = true;
        do {
            lstEquipos.add(this.crearEquipo());

            System.out.println("Desea crear otro Equipo?    0: No      1: Si :");
            crearOtro = sc.nextLine().equals("1") ? true : false;
        } while (crearOtro);
    }

    @Override
    public Equipo crearEquipo() {
        Equipo equipo = new Equipo();
        Scanner sc = App.sc;

        System.out.println("Nombre del Equipo: ");
        equipo.setNombre(sc.nextLine());

        System.out.println("Fecha de Creación del Equipo (dd-mm-aaaa): ");
        String strFech = sc.nextLine();
        DateTimeFormatter formateador = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate fechaCreacion = LocalDate.parse(strFech, formateador);
        equipo.setFechaCreacion(fechaCreacion);

        EntrenadorServicio entrServ = new EntrenadorServicioImpl();
        Entrenador entrenador = entrServ.crearEntreandor(equipo);
        equipo.setEntrenador(entrenador);

        List<Jugador> jugadoresList = this.crearListaJugadores(equipo);
        equipo.setJugadores(jugadoresList);
        return equipo;
    }

    @Override
    public List<Jugador> crearListaJugadores(Equipo equipo) {
        Scanner sc = App.sc;
        List<Jugador> lst = new ArrayList<Jugador>();
        boolean salir = false;
        JugadorServicioImpl jugadorServ = new JugadorServicioImpl();

        System.out.println("\nAgregará ahora los datos de los jugadores del equipo.\n");
        do {
            Jugador jugador = jugadorServ.crearJugador(equipo);

            if (jugador != null)
                lst.add(jugador);

            System.out.println("Agregar otro Jugador?    0: No      1: Si :");
            String siguienteJugador = sc.nextLine();
            salir = siguienteJugador.equals("0") ? true : false;
        } while (!salir);

        return lst;
    }

    @Override
    public void buscarJugadorXNom(List<Equipo> equipos) {
        Jugador jEncontrado = null;
        Scanner sc = App.sc;

        System.out.println("Nombre (Apellido, Nombres) del Jugador a buscar: ");
        String nomJugado = sc.nextLine();

        for (Equipo equipo : equipos) {
            List<Jugador> jugadors = equipo.getJugadores();
            for (Jugador jugador : jugadors) {

                if (nomJugado.equals(jugador.getApellido() + ", " + jugador.getNombre())) {
                    jEncontrado = jugador;
                    break;
                }
            }
        }

        if (jEncontrado != null) {
            String msg = String.format("Apellido: %S \nNombre: %S \n%S capitán \nEquipo: %S",
                    jEncontrado.getApellido(),
                    jEncontrado.getNombre(),
                    (jEncontrado.getEsCapitan() ? "Es" : "No es"),
                    jEncontrado.getEquipo().getNombre());
            System.out.println(msg);
        } else
            System.out.println("No se encontró el jugador");
    }

    @Override
    public void buscarEquipoJugadoresXNom(List<Equipo> equipos) {

        Equipo equipo = buscarEquipoXNombre(equipos);

        if (equipo != null) {
            Entrenador entrenador = equipo.getEntrenador();
            String msg = String.format("Equipo: %S - Entrenador: %S\nJugadores\n",
                    equipo.getNombre(),
                    entrenador.getApellido() + ", " + entrenador.getNombre());

            System.out.println(msg);

            for (Jugador j : equipo.getJugadores())
                System.out.println(j.toString());
        } else
            System.out.println("No se encontró el Equipo");
    }

    @Override
    public void buscarEquipoCapitanEntrenadorXNom(List<Equipo> equipos) {
        Equipo equipo = buscarEquipoXNombre(equipos);

        if (equipo != null) {
            Jugador capitan = equipo.getCapitan();
            String msg = String.format("Equipo: %s \nEntrenador: %s \nCapitan: %s",
                    equipo.getNombre(),
                    equipo.getEntrenador().getApellido() + ", " + equipo.getEntrenador().getNombre(),
                    (capitan != null ? capitan.getApellido() + ", " + capitan.getNombre() : "Sin Capitan"));

            System.out.println(msg);
        } else
            System.out.println("No se encontró el Equipo");
    }

    @Override
    public void EliminarEquipo(List<Equipo> equipos) {

        Equipo eqAEliminar = this.buscarEquipoXNombre(equipos);
        if (eqAEliminar != null)
            equipos.remove(eqAEliminar);
        else
            System.out.println("Equipo no encontrado.");

    }

    public Equipo buscarEquipoXNombre(List<Equipo> equipos) {
        Scanner sc = App.sc;
        Equipo equipoEncontrado = null;

        System.out.println("Nombre del Equipo: ");
        String nomEquipo = sc.nextLine();

        for (Equipo equipo : equipos) {
            if (equipo.getNombre().equals(nomEquipo)) {
                equipoEncontrado = equipo;
                break;
            }
        }

        return equipoEncontrado;
    }

}
