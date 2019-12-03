package com.crisgon;

public class Cuenta {

    private double balance; // Saldo
    private double amountAllowed; // Cantidad permitida

    public Cuenta(double balance, double amountAllowed) {
        this.amountAllowed = amountAllowed;
        this.balance = balance;
    }

    /**
     * Función que devuelve el saldo actual.
     */
    public double getCurrentBalance() {
        return balance;
    }

    /**
     * Función que permite hacer un nuevo ingreso.
     * @param amount (Cantidad a ingresar)
     * @return boolean que controla si se ha hecho el ingreso o no.
     */
    synchronized boolean setIngress(double amount) {
        if (balance < amountAllowed) {
            balance = balance + amount;
            return true;
        } else return false;
    }

    /**
     *  Función que permite hacer un reintegro.
     * @param amount (Cantidad a reintegrar)
     * @return boolean que controla si se ha hecho el reintegro o no.
     */
    synchronized boolean setRefund(double amount) {
        if (balance > amount ) {
            balance = balance - amount;
            return true;
        } else return false;
    }
}
