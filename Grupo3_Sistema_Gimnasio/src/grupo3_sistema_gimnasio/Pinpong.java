/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package grupo3_sistema_gimnasio;

import static grupo3_sistema_gimnasio.cabinasInsonorizadas.maxReservas;

/**
 *
 * @author ashle
 */
public class Pinpong extends espacioRecreativo {
       private Reserva[] reserva; 
        private int cantidadReservas; 

    public Pinpong(int capacidadMaxima, int capacidadActual, String nombreEspacio) {
        super(capacidadMaxima, capacidadActual, nombreEspacio);
        this.reserva = new Reserva[maxReservas]; // valor fijo de bloque de 18 
        this.cantidadReservas = 0;
    }

    public Reserva[] getReserva() {
        return reserva;
    }

    public void setReserva(Reserva[] reserva) {
        this.reserva = reserva;
    }

    public int getCantidadReservas() {
        return cantidadReservas;
    }

    public void setCantidadReservas(int cantidadReservas) {
        this.cantidadReservas = cantidadReservas;
    }

        
        
        
    
}
