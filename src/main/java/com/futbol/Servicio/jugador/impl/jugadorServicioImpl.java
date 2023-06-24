package com.futbol.servicio.jugador.impl;

import java.util.Scanner;

import com.futbol.App;
import com.futbol.domain.Equipo;
import com.futbol.domain.Jugador;
import com.futbol.servicio.jugador.JugadorServicio;

public class JugadorServicioImpl implements JugadorServicio {

    @Override
    public Jugador crearJugador(Equipo equipo) {
        Scanner sc = App.sc;

        System.out.println("Apellido:");
        String ap = sc.nextLine();

        System.out.println("Nombre:");
        String nom = sc.nextLine();

        System.out.println("Altura:");
        String strH = sc.nextLine();
        int h = Integer.parseInt(strH);

        System.out.println("Posición:");
        String strPos = sc.nextLine();
        int pos = Integer.parseInt(strPos);

        System.out.println("Cantidad de goles: ");
        String strCantGoles = sc.nextLine();
        int canGoles = Integer.parseInt(strCantGoles);

        System.out.println("Es Capitán \t (0: No \t 1: Si): ");
        String strEsCap = sc.nextLine();
        boolean esCap = strEsCap.equals("0") ? false : true;

        System.out.println("Númeor de camiseta:");
        String strNroCam = sc.nextLine();
        int nroCamiseta = Integer.parseInt(strNroCam);

        Jugador jugador = new Jugador(nom, ap, equipo, h, pos, canGoles, esCap, nroCamiseta);

        return jugador;
    }

    @Override
    public Jugador crearJugador(String nombre, String apellido, int altura, int posicion, int cantGoles,
            boolean esCapitan, int nroCamiseta, Equipo equipo) {

        return new Jugador(nombre, apellido, equipo, altura, posicion, cantGoles, esCapitan, nroCamiseta);
    }

}
