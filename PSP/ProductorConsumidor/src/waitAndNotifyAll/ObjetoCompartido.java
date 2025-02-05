/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package waitAndNotifyAll;

/**
 *
 * @author FP
 */
public class ObjetoCompartido {

    int valor;
    boolean disponible = false; //Inicialmente no tenemos valor

    public synchronized int get() {
        while (disponible == false) {
            try {
                wait();
            } catch (InterruptedException e) {

            }
        }
        this.disponible = false;
        notifyAll();
        return this.valor;
    }

    public synchronized void set(int val) {
        while (disponible == true) {
            try {
                wait();
            } catch (InterruptedException e) {

            }
        }
        //cuando se despierte, volvemos a establecer la disponibilidad a cierto, 
        //establecemos el valor generado por el productor y notificamos a todos
        //los consumidores de la disponibilidad
        this.valor = val;
        this.disponible = true;
        notifyAll();

    }
}
