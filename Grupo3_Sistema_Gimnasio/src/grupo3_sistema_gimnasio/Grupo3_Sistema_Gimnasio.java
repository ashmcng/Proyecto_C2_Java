/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package grupo3_sistema_gimnasio;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

/**
 *
 * @author ashle
 */
public class Grupo3_Sistema_Gimnasio {

    /**
     * @param args the command line arguments
     */


    public static void main(String[] args) {
        

        // INTANCEAMOS EL GUI 
 
        int opcion1 = JOptionPane.showConfirmDialog(null, "¿Desea ingresar al menú gráfico?");

        if (opcion1 == JOptionPane.YES_OPTION) {
            SwingUtilities.invokeLater(() -> new GUI());
            
            return; //  esto evita que continúe el resto del código
        }
        
        // Llamamos al menu 
        
        Menu menu = new Menu(); 
        menu.mostrarMenu(); 
    }
      
}
