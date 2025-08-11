/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package grupo3_sistema_gimnasio;

import java.time.LocalTime;
import javax.swing.JOptionPane;

/**
 *
 * @author isaacherrera
 */
public class auditorioFitness {
    private String dia; 
    private String nombre; 
    private LocalTime hora; 
    private Socio[] inscritos; // ya que hay multiples eventos
    private int contadorInscritos; 

    public auditorioFitness(String dia, String nombre, LocalTime hora) {
        this.dia = dia;
        this.nombre = nombre;
        this.hora = hora;
        this.inscritos = new Socio[30]; //fijo
        this.contadorInscritos = 0; 
                
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }
    
    public boolean agregarSocio(Socio socio){
        if (contadorInscritos >= inscritos.length) {
            return false; 
        
        }
        inscritos[contadorInscritos++]= socio;
        return true; 
    
    }

    public Socio[] getInscritos() {
        return inscritos;
    }

    public void setInscritos(Socio[] inscritos) {
        this.inscritos = inscritos;
    }

    public int getContadorInscritos() {
        return contadorInscritos;
    }

    public void setContadorInscritos(int contadorInscritos) {
        this.contadorInscritos = contadorInscritos;
    }
    
    
    
    
}

