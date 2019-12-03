package com.crisgon;

public class Main {

    public static void main(String[] args) {

        Cuenta cuenta = new Cuenta(400, 800);

        Persona personaX = new Persona("Cristhian", cuenta);
        Persona personaY = new Persona("Andrea", cuenta);

        personaX.start();
        personaY.start();
    }
}
