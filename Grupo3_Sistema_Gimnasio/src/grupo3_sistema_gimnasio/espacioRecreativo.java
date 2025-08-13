/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package grupo3_sistema_gimnasio;

/**
 *
 * @author isaacherrera
 */
public class espacioRecreativo {
     private int capacidadMaxima;
    private int capacidadActual;
    private String nombreEspacio;

    public String getNombreEspacio() {
        return nombreEspacio;
    }

    public void setNombreEspacio(String nombreEspacio) {
        this.nombreEspacio = nombreEspacio;
    }
    

    public int getCapacidadMaxima() {
        return capacidadMaxima;
    }

    public void setCapacidadMaxima(int capacidadMaxima) {
        this.capacidadMaxima = capacidadMaxima;
    }

    public int getCapacidadActual() {
        return capacidadActual;
    }

    public void setCapacidadActual(int capacidadActual) {
        this.capacidadActual = capacidadActual;
    }

    public espacioRecreativo(int capacidadMaxima, int capacidadActual, String nombreEspacio) {
        this.capacidadMaxima = capacidadMaxima;
        this.capacidadActual = capacidadActual;
        this.nombreEspacio = nombreEspacio;
    }

    @Override
    public String toString() {
        return  "Nombre Espacio: " + nombreEspacio + "Capacidad Maxima: " + capacidadMaxima + "Capacidad Actual: " + capacidadActual ;
    }

  
  
}

