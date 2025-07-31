/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package grupo3_sistema_gimnasio;

import javax.swing.JOptionPane;

/**
 *
 * @author isaacherrera
 */
public class cabinasInsonorizadas {
    
    
    
    private int capacidadMaxima = 10;
    private int capacidadActual = 0;

    public boolean registrarEntrada(Socio socio) {
        if (!socio.isMembresiaSocio()) {
            JOptionPane.showMessageDialog(null, "Membresía inactiva. No puede ingresar a la cabina.");
            return false;
        }
        if (capacidadActual >= capacidadMaxima) {
            JOptionPane.showMessageDialog(null, "Cabinas ocupadas. Espere su turno.");
            return false;
        }
        capacidadActual++;
        JOptionPane.showMessageDialog(null, "Ingreso registrado. Cabinas ocupadas: " + capacidadActual);
        return true;
    }

    public boolean registrarSalida(Socio socio) {
        if (capacidadActual <= 0) {
            JOptionPane.showMessageDialog(null, "No hay usuarios en las cabinas.");
            return false;
        }
        capacidadActual--;
        JOptionPane.showMessageDialog(null, "Salida registrada. Cabinas ocupadas: " + capacidadActual);
        return true;
    }

    public void verDisponibilidad() {
        JOptionPane.showMessageDialog(null, "Cabinas en uso: " + capacidadActual + " / " + capacidadMaxima);
    }
}


