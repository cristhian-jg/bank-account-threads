package com.crisgon;

public class Persona extends Thread {

    private String name;
    private Cuenta cuenta;

    public Persona(String name, Cuenta cuenta) {
        super(name);
        this.cuenta = cuenta;
    }

    public void run() {

        int aleatorio;

        while (true) {
            aleatorio = ((int) (Math.random()*500+1));
        }

    }

}
