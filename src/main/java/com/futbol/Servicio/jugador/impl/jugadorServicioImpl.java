package com.futbol.Servicio.jugador.impl;

import java.util.Scanner;

import com.futbol.App;
import com.futbol.Servicio.jugador.jugadorServicio;
import com.futbol.domain.equipo;
import com.futbol.domain.jugador;

public class jugadorServicioImpl implements jugadorServicio {


    @Override
    public jugador crearJugador(equipo equipo) {
        Scanner sc = App.sc;

        System.out.println("Nombre:");
        String nom = sc.nextLine();
        
        System.out.println("Apellido:");
        String ap = sc.nextLine();
        
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
        boolean esCap = strEsCap.equals("0")? false:true;

        System.out.println("Númeor de camiseta:");
        String strNroCam = sc.nextLine();
        int nroCamiseta = Integer.parseInt(strNroCam);
        
        jugador j = new jugador(nom, ap, equipo, h, pos, canGoles, esCap, nroCamiseta);
                
        
        return j;
    }

}
