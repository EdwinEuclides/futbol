package com.futbol.Servicio.Entrenador.impl;

import java.util.Scanner;

import com.futbol.App;
import com.futbol.Servicio.Entrenador.entrenadorServicio;
import com.futbol.domain.entrenador;
import com.futbol.domain.equipo;

public class entrenadorServicioImpl implements entrenadorServicio {

    @Override
    public entrenador crearEntreandor(equipo equipo) {
        Scanner sc = App.sc;

        System.out.println("Nombre del Entrenador:");
        String nom = sc.nextLine();

        System.out.println("Apellido del Entrenador:");
        String ap = sc.nextLine();

        boolean edadOk = false;
        int edad = 0;
        do {
            System.out.println("Edad del Entrenador (debe ser un nro entero): ");
            if (sc.hasNextInt()) {
                edad = sc.nextInt();
                edadOk = true;
            }

        } while (!edadOk);

        return new entrenador(nom, ap, edad, equipo);

    }

}
