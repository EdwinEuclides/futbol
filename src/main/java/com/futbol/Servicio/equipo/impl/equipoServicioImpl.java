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

    @Override
    public void crearEquipos(List<equipo> lstEquipos) {
        Scanner sc = App.sc;
        boolean crearOtro = true;
        do {
            lstEquipos.add(this.crearEquipo());

            System.out.println("Desea crear otro Equipo?    0: No      1: Si :");
            crearOtro = sc.nextLine().equals("1") ? true : false;
        } while (crearOtro);
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
    public List<jugador> crearListaJugadores(equipo equipo) {
        Scanner sc = App.sc;
        List<jugador> lst = new ArrayList<jugador>();
        boolean salir = false;
        jugadorServicioImpl jugadorServ = new jugadorServicioImpl();

        System.out.println("\nAgregará ahora los datos de los jugadores del equipo.\n");
        do {
            jugador jugador = jugadorServ.crearJugador(equipo);

            if (jugador != null)
                lst.add(jugador);

            System.out.println("Agregar otro Jugador?    0: No      1: Si :");
            String siguienteJugador = sc.nextLine();
            salir = siguienteJugador.equals("0") ? true : false;
        } while (!salir);

        return lst;
    }

    @Override
    public void buscarJugadorXNom(List<equipo> equipos) {
        jugador jEncontrado = null;
        Scanner sc = App.sc;

        System.out.println("Nombre del Jugador a buscar: ");
        String nomJugado = sc.nextLine();

        for (equipo equipo : equipos) {
            List<jugador> jugadors = equipo.getJugadores();
            for (jugador jugador : jugadors) {
                if (jugador.getNombre().equals(nomJugado)) {
                    jEncontrado = jugador;
                    break;
                }
            }
        }

        if (jEncontrado != null) {
            String msg = String.format("Nombre: %S - Apellido: %S - %S capitán  - Equipo: %S",
                    jEncontrado.getNombre(),
                    jEncontrado.getApellido(),
                    (jEncontrado.getEsCapitan() ? "Es" : "No es"),
                    jEncontrado.getEquipo().getNombre());
            System.out.println(msg);
        } else
            System.out.println("No se encontró el jugador");
    }

    @Override
    public void buscarEquipoJugadoresXNom(List<equipo> equipos) {

        equipo equipo = buscarEquipoXNombre(equipos);

        if (equipo != null) {
            String msg = String.format("Equipo: %S - Entrenador: %S\nJugadores\n",
                    equipo.getNombre(),
                    equipo.getEntrenador().getApellido() + ", " + equipo.getNombre());
            System.out.println(msg);

            for (jugador j : equipo.getJugadores())
                System.out.println(j.toString());
        } else
            System.out.println("No se encontró el Equipo");
    }

    @Override
    public void buscarEquipoCapitanEntrenadorXNom(List<equipo> equipos) {
        equipo equipo = buscarEquipoXNombre(equipos);

        if (equipo != null) {
            jugador capitan = equipo.getCapitan();
            String msg = String.format("Equipo: %S - Entrenador: %S - Capitan: ",
                    equipo.getNombre(),
                    equipo.getEntrenador().getApellido() + ", " + equipo.getNombre(),
                    (capitan != null ? capitan.getApellido() + ", " + capitan.getNombre() : "Sin Capitan"));

            System.out.println(msg);
        } else
            System.out.println("No se encontró el Equipo");
    }

    @Override
    public void EliminarEquipo(List<equipo> equipos) {

        equipo eqAEliminar = this.buscarEquipoXNombre(equipos);
        if (eqAEliminar != null)
            equipos.remove(eqAEliminar);
        else
            System.out.println("Equipo no encontrado.");

    }

    private equipo buscarEquipoXNombre(List<equipo> equipos) {
        Scanner sc = App.sc;
        equipo equipoEncontrado = null;

        System.out.println("Nombre del Equipo: ");
        String nomEquipo = sc.nextLine();

        for (equipo equipo : equipos) {
            if (equipo.getNombre().equals(nomEquipo)) {
                equipoEncontrado = equipo;
                break;
            }
        }

        return equipoEncontrado;
    }

}
