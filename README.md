Cuenta bancaria

Alumno: Cristhian Gónzalez
Curso: 2º DAM
Modulo: Programación de servicios 
y procesos
Fecha: 03/11/2019


En esta práctica se busca ver como dos hilos Persona comparten un objeto Cuenta entre sí y hacen operaciones en ella de manera sincronizada. El programa estará disponible para su descarga también en mi GitHub en el repositorio Bank-Account. 

Código Cuenta.
```javascript
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
```
En esta primera clase que he creado se busca crear varios metodos que permitan a una Persona hacer tanto un ingreso como un reintegro. 

Cuenta con el atributos saldo, que indica el saldo de la cuenta, y el atributo cantidadPermitida, que indica el saldo máximo que puede haber ingresado en la cuenta. He añadido un metodo Getter para obtener el saldo actual de la cuenta, que me ayudará despues a comprobar el saldo almacenado en la cuenta. 

Tenemos los metodos para ingresar y retirar dinero, los he aplicado synchronized para que se evite que uno de estos metodos pueda ser ejecutado al mismo tiempo que el otro y así evitar errores de sincronización. Además hago que devuelvan valores booleanos, porque creo que es mejor que la salida de texto se trate fuera de esta clase.

Clase Persona:
```javascript
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
```
Esta clase Persona es la que funcionará cómo un hilo y la que operará con la clase Cuenta, esta clase unicamente con una Cuenta, tambien se podría decir que con un nombre heredado de la clase Thread y su correpondiente metodo run().

Dentro de este metodo run he implementado un bucle que seguirá ejecutandose hasta que ocurra un error el cual controlo mediante el boolean error, puedo hacer esto gracias a que los metodos de ingreso y reintegro devuelven valores booleanos, con lo que puedo saber más facilmente cuando se ha hecho un ingreso o no y dependiento de este resultado mostrar un texto u otro. 

Se ha añadido un Thread.sleep entre las llamadas a los metodos para provocar una desincronzación y comprobar que los metodos estan correctamente sincronizados. 

Cuando uno de los metodos (reintegro o ingreso) devuelve un error termina el bucle y la Persona deja de hacer ingresos o reintegros. 

Código Main:
```javascript
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
```
En el metodo Main he creado una nueva cuenta y dos personas que comparten esta misma cuenta y los he puesto a trabajar. 

Resultado:
![Resultado](https://imgur.com/a/4scyVOi)
