package com.crisgon;

public class Cuenta {

    /* Controlará la cantidad máxima que se puede ingresar en la cuenta. */
    private static final double MAXIMUN_AMOUNT_ALLOWED = 20.0;

    private double balance; //Saldo

    /**
     * Función que devuelve el saldo actual.
     */
    public double getCurrentBalance() {
        return balance;
    }

    /**
     * Función que permite hacer un nuevo ingreso.
     * @param amount (Cantidad a ingresar)
     */
    public void setIngress(double amount) {
        if (balance < MAXIMUN_AMOUNT_ALLOWED) {
            balance = balance + amount;
        } else {
            System.out.println("No puedes ingresar más dinero en esta cuenta.");
        }
    }

    /**
     *  Función que permite hacer un reintegro.
     * @param amount (Cantidad a reintegrar)
     */
    public void setRefund(double amount) {
        if (balance > amount ) {
            balance = balance - amount;
        } else System.out.println("No hay saldo disponible.");
    }
}
