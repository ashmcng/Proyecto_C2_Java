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
    private int idUnicoEspacio; 
    private Socio[] socios = new Socio[50]; // array para guardar los que se inscriben   
    private int cantidadSocios = 0; 

    public Socio[] getSocios() {
        return socios;
    }

    public void setSocios(Socio[] socios) {
        this.socios = socios;
    }

    public int getCantidadSocios() {
        return cantidadSocios;
    }

    public void setCantidadSocios(int cantidadSocios) {
        this.cantidadSocios = cantidadSocios;
    }

    
    public int getIdUnicoEspacio() {
        return idUnicoEspacio;
    }

    public void setIdUnicoEspacio(int idUnicoEspacio) {
        this.idUnicoEspacio = idUnicoEspacio;
    }
    
    

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

    public espacioRecreativo(int capacidadMaxima, int capacidadActual, String nombreEspacio, int idUnicoEspacio) {
        this.capacidadMaxima = capacidadMaxima;
        this.capacidadActual = capacidadActual;
        this.nombreEspacio = nombreEspacio;
        this.idUnicoEspacio = idUnicoEspacio;
    }

  

    @Override
    public String toString() {
        return  "Nombre Espacio: " + nombreEspacio + "  ID Espacio: " + idUnicoEspacio + " " + "Capacidad Maxima: " + capacidadMaxima + " " + "Capacidad Actual: " + capacidadActual ;
    }

  
  
}

