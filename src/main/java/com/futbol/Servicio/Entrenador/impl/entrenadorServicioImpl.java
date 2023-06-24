package com.futbol.servicio.entrenador.impl;

import java.util.Scanner;
import com.futbol.App;
import com.futbol.domain.Entrenador;
import com.futbol.domain.Equipo;
import com.futbol.servicio.entrenador.EntrenadorServicio;

public class EntrenadorServicioImpl implements EntrenadorServicio {

    @Override
    public Entrenador crearEntreandor(Equipo equipo) {
        Scanner sc = App.sc;

        System.out.println("Apellido del Entrenador:");
        String ap = sc.nextLine();

        System.out.println("Nombre del Entrenador:");
        String nom = sc.nextLine();

        boolean edadOk = false;
        String sEdad = "";
        int edad = 0;
        do {
            System.out.println("Edad del Entrenador (debe ser un nro entero): ");
            if (sc.hasNextInt()) {
                sEdad = sc.nextLine();
                edad = Integer.parseInt(sEdad);
                edadOk = true;
            }

        } while (!edadOk);

        return new Entrenador(nom, ap, edad, equipo);

    }

}
