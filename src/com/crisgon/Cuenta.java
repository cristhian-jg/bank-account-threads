package com.crisgon;

public class Cuenta {

    private double saldo;
    private double cantidadPermitida;

    public Cuenta(double saldo, double cantidadPermitida) {
        this.cantidadPermitida = cantidadPermitida;
        this.saldo = saldo;
    }

    /**
     * Función de devuelve el saldo actual disponible.
     * @return saldo disponible.
     */
    public double getSaldo() {
        return saldo;
    }

    /**
     * Función que permite hacer un nuevo ingreso.
     * @param cantidad (Cantidad a ingresar)
     * @return boolean que controla si se ha hecho el ingreso o no.
     */
    synchronized boolean setIngreso(double cantidad) {
        if ((saldo + cantidad) < cantidadPermitida) {
            saldo = saldo + cantidad;
            return true;
        } else return false;
    }

    /**
     *  Función que permite hacer un reintegro.
     * @param cantidad (Cantidad a reintegrar)
     * @return boolean que controla si se ha hecho el reintegro o no.
     */
    synchronized boolean setReintegro(double cantidad) {
        if (saldo > cantidad ) {
            saldo = saldo - cantidad;
            return true;
        } else return false;
    }
}
