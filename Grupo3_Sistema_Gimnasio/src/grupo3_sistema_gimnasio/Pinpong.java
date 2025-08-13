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
       private int reservasDisponibles = maxReservas - cantidadReservas; 

    public Pinpong(int capacidadMaxima, int capacidadActual, String nombreEspacio, int idUnicoEspacio) {
        super(capacidadMaxima, capacidadActual, nombreEspacio, idUnicoEspacio);
        this.reserva = new Reserva[maxReservas];
        this.cantidadReservas = cantidadReservas;
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

    @Override
    public String toString() {
        return  "Espacio: " + this.getNombreEspacio() + " ID Espacio: " + this.getIdUnicoEspacio() + " " + "Reservas Disponibles: " + reservasDisponibles;
    }

        
        
        
    
}
