package com.crisgon;

public class Persona extends Thread {

    private Cuenta cuenta;

    public Persona(String nombre, Cuenta cuenta) {
        super(nombre);
        this.cuenta = cuenta;
    }

    public void run() {

        boolean error = false;

        int ingreso;
        int reintegro;

        while (!error) {

            System.out.println("Saldo disponible: " + cuenta.getSaldo());

            ingreso = ((int) (Math.random()*500+1));

            if (cuenta.setIngreso(ingreso)) {
                System.out.println(getName() + " ha ingresado: " + ingreso);
            } else {
                System.out.println(getName() + " ha intentado ingresar " +
                        ingreso + " pero ha superado la cantidad máxima para la cuenta. (" +
                        ingreso + " + " + cuenta.getSaldo() + " = " + (ingreso+cuenta.getSaldo()) + ")");
                error = true;
            }

            try { Thread.sleep(2000); }
            catch (InterruptedException e) { e.printStackTrace(); }

            reintegro = ((int) (Math.random()*500+1));

            if (cuenta.setReintegro(reintegro)) {
                System.out.println(getName() + " ha reintegrado: " + reintegro);
            } else {
                System.out.println(getName() + " ha intentado reintegrar " +
                        reintegro + " pero el saldo es de " + cuenta.getSaldo());
                error = true;
            }
        }

        System.out.println("Como a " + getName() + " le ha salido un error en la cuenta se ha ido.");
    }

}
