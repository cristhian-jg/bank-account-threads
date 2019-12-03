package com.crisgon;

public class Persona extends Thread {

    private String nombre;
    private Cuenta cuenta;

    public Persona(String nombre, Cuenta cuenta) {
        super(nombre);
        this.cuenta = cuenta;
    }

    public void run() {

        boolean error = false; // Controla si ha habido un error en el proceso.

        int ingreso;
        int reintegro;

        while (!error) {

            ingreso = ((int) (Math.random()*500+1));

            if (cuenta.setIngreso(ingreso)) {
                System.out.println("Se han ingresado: " + ingreso);
            } else {
                System.out.println("Se ha superado la cantidad máxima para la cuenta.");
                error = true;
            }

            reintegro = ((int) (Math.random()*500+1));

            if (cuenta.setReintegro(reintegro)) {
                System.out.println("Se han reintegrado: " + reintegro);
            } else {
                System.out.println("No hay saldo disponible");
                error = true;
            }
        }
    }

}
