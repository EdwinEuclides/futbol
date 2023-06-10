package com.futbol;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.futbol.Servicio.AccesoADatos.accesoADatosServicio;
import com.futbol.Servicio.AccesoADatos.impl.accesoADatosServicioImpl;
import com.futbol.Servicio.equipo.equipoServicio;
import com.futbol.Servicio.equipo.impl.equipoServicioImpl;
import com.futbol.domain.equipo;

enum Menu {
    CrearEquipo, BuscarJugador, BuscarEquipo, BuscarEquipoYJugadores, EliminarEquipo, ImportarJugadores,
    ExportarJugadores, Salir
};

public class App {
    private static List<equipo> EQUIPOS;

    public static Scanner sc;

    public static void main(String[] args) {
        sc = new Scanner(System.in);

        EQUIPOS = new ArrayList<equipo>();
        equipoServicio equipoServ = new equipoServicioImpl();
        accesoADatosServicio accDatos = new accesoADatosServicioImpl();
        EQUIPOS.add(equipoServ.crearEquipo());

        Menu elecc = Menu.Salir;
        boolean exit = false;

        do {
            boolean eleccOk = false;
            do {
                System.out.println("\n      ELIJA UNA ACCION (escriba el número)\n");
                System.out.println("1 :  Crear Equipo.");
                System.out.println("2 :  Buscar Jugador y mostrar sus datos.");
                System.out.println("3 :  Buscar Equipo y mostrar su nombre, entrenador y capitán");
                System.out.println("4 :  Buscar Equipo y mostrar su datos y sus Jugadores.");
                System.out.println("5 :  Eliminar Equipo.");
                System.out.println("6 :  Importar Jugadores.");
                System.out.println("7 :  Exportar Jugadores.");
                System.out.println("8 :  Salir.");

                if (sc.hasNextInt()) {
                    String strElegido = sc.nextLine();
                    int nroElegido = Integer.parseInt(strElegido);
                    if (nroElegido > 0 && nroElegido < 9) {
                        elecc = Menu.values()[nroElegido - 1];
                        eleccOk = true;
                    } else
                        System.out.println("Eleccion no válida\n");
                } else
                    System.out.println("Eleccion no válida\n");

            } while (!eleccOk);

            switch (elecc) {
                case CrearEquipo:
                    equipoServ.crearEquipos(EQUIPOS); // 1
                    break;
                case BuscarJugador:
                    equipoServ.buscarJugadorXNom(EQUIPOS); // 2
                    break;
                case BuscarEquipo:
                    equipoServ.buscarEquipoJugadoresXNom(EQUIPOS); // 3
                    break;
                case BuscarEquipoYJugadores:
                    equipoServ.buscarEquipoCapitanEntrenadorXNom(EQUIPOS); // 4
                    break;
                case EliminarEquipo:
                    equipoServ.EliminarEquipo(EQUIPOS);
                    break;
                case ImportarJugadores:
                    accDatos.importarJugadores(EQUIPOS);
                    break;
                case ExportarJugadores:
                    accDatos.exportarJugadores(EQUIPOS);
                    break;
                case Salir:
                    System.out.println("Salir: " + elecc.toString());
                    exit = true;
            }

        } while (!exit);

        System.out.println("*************************************************");
        System.out.println("*****    GRACIAS POR USAR LA APLICACIÓN    *****");
        System.out.println("*************************************************");
        sc.close();
    }

}

/*
 * • Crear un equipo, sus jugadores y técnico. El menú debe permitir preguntar
 * si se desea cargar más equipos o salir (Por cada vez que se cargue equipos).
 * 
 * • Buscar un jugador por su nombre, donde se pide que se liste su nombre,
 * apellido, posición, si es capitán o no y el nombre de su equipo.
 * 
 * • Buscar un equipo por su nombre, donde se pide su nombre, nombre de
 * entrenador y nombre del capitán del equipo.
 * 
 * • Buscar un equipo por su nombre, donde se muestre su nombre, nombre del
 * entrenador y la lista de los jugadores del equipo.
 * 
 * • Eliminar un equipo dado su nombre (Se deberá eliminar jugadores y
 * entrenador)
 * 
 * • Importar la lista de jugadores de un equipo desde un archivo csv o txt.
 * • Exportar la lista de jugadores hacia un archivo ya sea csv o txt.
 * 
 */
