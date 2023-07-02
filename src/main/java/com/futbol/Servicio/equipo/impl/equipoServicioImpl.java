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

    private EntrenadorServicio entrenadorServicio;

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

        Entrenador entrenador = this.getEntrenadorServicio().crearEntreandor(equipo);
        equipo.setEntrenador(entrenador);

        List<Jugador> listaJugadores = this.crearListaJugadores(equipo);
        equipo.setJugadores(listaJugadores);

        return equipo;
    }

    private List<Jugador> crearListaJugadores(Equipo equipo) {
        Scanner sc = App.sc;
        List<Jugador> lst = new ArrayList<Jugador>();
        boolean salir = false;
        JugadorServicioImpl jugadorServ = new JugadorServicioImpl();

        System.out.println("\nAgregará ahora los datos de los jugadores del equipo.\n");
        String siguienteJugador = "1";
        do {
            Jugador jugador = jugadorServ.crearJugador(equipo);

            if (jugador != null)
                lst.add(jugador);

            if (lst.size() < 5) {
                System.out.println("Agregar otro Jugador?    0: No      1: Si :");
                siguienteJugador = sc.nextLine();
            }
            salir = siguienteJugador.equals("0") || lst.size() == 5 ? true : false;
        } while (!salir);

        return lst;
    }

    @Override
    public void buscarJugadorYMostrarSusDatos(List<Equipo> equipos) {
        Scanner sc = App.sc;

        System.out.println("Nombre (Apellido, Nombres) del Jugador a buscar: ");
        String nombreBuscado = sc.nextLine();

        for (Equipo equipo : equipos) {
            List<Jugador> jugadors = equipo.getJugadores();
            for (Jugador jugador : jugadors) {
                if (nombreBuscado.equals(jugador.getApellido() + ", " + jugador.getNombre())) {
                    String msg = String.format("Apellido: %S \nNombre: %S \n%S capitán \nEquipo: %S",
                            jugador.getApellido(),
                            jugador.getNombre(),
                            (jugador.getEsCapitan() ? "Es" : "No es"),
                            jugador.getEquipo().getNombre());
                    System.out.println(msg);
                    return;
                }
            }
        }

        System.out.println("No se encontró el jugador");
    }

    @Override
    public void buscarEquipoYMostrarNombreEntrenadoCapitan(List<Equipo> equipos) {

        System.out.println("Nombre del Equipo Buscado: ");
        String nombreEquipo = App.sc.nextLine();
        Equipo equipo = buscarEquipo(equipos, nombreEquipo);

        if (equipo == null) {
            System.out.println("No se encontró el Equipo");
            return;
        }

        Jugador capitan = equipo.getCapitan();
        String msg = String.format("Equipo: %s \nEntrenador: %s \nCapitan: %s",
                equipo.getNombre(),
                equipo.getEntrenador().getApellido() + ", " + equipo.getEntrenador().getNombre(),
                (capitan != null ? capitan.getApellido() + ", " + capitan.getNombre() : "Sin Capitan"));

        System.out.println(msg);
    }

    @Override
    public void buscarEquipoMostrarSusDatosYJugadores(List<Equipo> equipos) {

        Scanner sc = App.sc;
        System.out.println("Nombre del Equipo Buscado: ");
        String nombreEquipo = sc.nextLine();

        Equipo equipo = buscarEquipo(equipos, nombreEquipo);

        if (equipo == null) {
            System.out.println("No se encontró el Equipo");
            return;
        }

        Entrenador entrenador = equipo.getEntrenador();
        String msg = String.format("Equipo: %S - Entrenador: %S\nJugadores\n",
                equipo.getNombre(),
                entrenador.getApellido() + ", " + entrenador.getNombre());

        System.out.println(msg);

        for (Jugador jugador : equipo.getJugadores())
            System.out.println(jugador.toString());
    }

    @Override
    public void EliminarEquipo(List<Equipo> equipos) {
        System.out.println("Nombre del Equipo a Eliminar: ");
        String nombreEquipo = App.sc.nextLine();

        Equipo equipoAEliminar = this.buscarEquipo(equipos, nombreEquipo);
        if (equipoAEliminar != null)
            equipos.remove(equipoAEliminar);
        else
            System.out.println("Equipo no encontrado.");

    }

    private Equipo buscarEquipo(List<Equipo> equipos, String nombreEquipo) {

        for (Equipo equipo : equipos) {
            if (equipo.getNombre().equals(nombreEquipo)) {
                return equipo;
            }
        }

        return null;
    }

    public EntrenadorServicio getEntrenadorServicio() {
        entrenadorServicio = new EntrenadorServicioImpl();
        return entrenadorServicio;
    }

}
