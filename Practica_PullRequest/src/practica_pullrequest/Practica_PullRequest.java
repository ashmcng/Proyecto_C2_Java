/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package practica_pullrequest;

import javax.swing.JOptionPane;

/**
 *
 * @author ashle
 */
public class Practica_PullRequest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
                // Este progama es una calculadora aritmetrica sensilla 
        
        // Defenimos las variables 
        
        int N1;
        int N2;
        int suma;
        int resta;
        int multiplicacion; 
        int division;
        String Lectura1; 
        String Lectura2; 
        
        // Les damos valor 
                
        N1 = 0;
        N2 = 0;
        suma = 0;
        resta = 0;
        multiplicacion = 0;
        division = 0; 
        
        // Mensaje de bienvenida 
        
        JOptionPane.showMessageDialog(null, "Bienvenido a la calculadora aritmetrica clasica");
        
        // Pedimos al usuario que ingrese 1 y 2 
        
        Lectura1 = JOptionPane.showInputDialog("Digite un numero");
        Lectura2 = JOptionPane.showInputDialog("Digite otro numero");
        
        // Le asignamos el valor de lectura 1 a N1 y el valor de lectura2 a N2 
        
        N1 = Integer.parseInt(Lectura1);
        N2 = Integer.parseInt(Lectura2);
        
        // Ya qwe tenemos los valores vamos a hacer los calculos aritmetricos 
        
        suma = N1 + N2;  
        resta = N1 - N2;
        multiplicacion =  N1 * N2; 
        division = N1 / N2;
        
        // Vamos a imprinmir el resultado de las cuatro operaciones 
        
        JOptionPane.showMessageDialog(null, "El resultado de la suma es de:" + " " + suma + ", " + "El resultado de la resta es de:" + "\n" +
        resta + ", " + "El resultado de la multiplicacion es de:" + " " + multiplicacion + ", " + "El resultado de la division es de:" + "\n" + 
                division);
        
        
    
    }
    
}

