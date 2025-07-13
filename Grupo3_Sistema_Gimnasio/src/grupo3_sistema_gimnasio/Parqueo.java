/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package grupo3_sistema_gimnasio;

import javax.swing.JOptionPane;

/**
 *
 * @author ashle
 */
public class Parqueo {

    /// DECLARAMOS LAS TRES MATRICES
    /// DE LOS NIVELES DEL PARQUEO Y SON CHAR YA QUE ES UNA SOLA LETRA
    private char[][] nivelG1;
    private char[][] nivelG2;
    private char[][] nivelG3;
   
    /// para asignar ID del socio en el campo 
    private int[][] idsG1;
    private int[][] idsG2;
    private int[][] idsG3;

    ///INICIALIZAMOS LAS MATRICES PUBLIC
    public Parqueo() {
        nivelG1 = new char[4][5];
        nivelG2 = new char[5][5];
        nivelG3 = new char[6][5];
        
        idsG1 = new int[4][5];
        idsG2 = new int[5][5];
        idsG3 = new int[6][5];
        
        inicializarNiveles(); //llamamos al inicializador
    }

    public void inicializarNiveles() {
        inicializarNivel(nivelG1);
        inicializarNivel(nivelG2);
        inicializarNivel(nivelG3);

    }

    // LLENAMOS LAS MATRICES DE LIBRE
    private void inicializarNivel(char[][] nivel) { // creamos nivel para definir el espacio
        for (int i = 0; i < nivel.length; i++) { // I fila
            for (int j = 0; j < nivel[0].length; j++) { // COLUMNA
                nivel[i][j] = 'L';   // Asignamos L al espacio = libre
            }

        }

    }

    public void mostrarNivel(char[][] nivel, String nombreNivel) {
        StringBuilder resultado = new StringBuilder(); // impresion mas compacta
        resultado.append("nivel ").append(nombreNivel).append("\n"); // inyectar los datos al nivel correspondiente

        for (int i = 0; i < nivel[0].length; i++) {
            resultado.append((i + 1)).append(" ");
        }
        resultado.append("\n");

        char fila = 'A';
        for (int i = 0; i < nivel.length; i++) {
            resultado.append(fila).append(" ");
            for (int j = 0; j < nivel[0].length; j++) {
                resultado.append("|").append(nivel[i][j]);
            }
            resultado.append("|\n");
            fila++;
        }

        JOptionPane.showMessageDialog(null, resultado.toString());

    }

    public boolean asignarEspacio(char[][] nivel) {
        String filaInput = JOptionPane.showInputDialog("Digite la letra de la fila que desea parquear A - B - C - D");
        String columnaInput = JOptionPane.showInputDialog("Digite el numero de la columna 1 - 2 - 3 ....");

        int fila = filaInput.toUpperCase().charAt(0) - 'A'; // Ponemos uppercase en caso de que entre lower y asiganmos al char la letra dada colocamos el espacio
        int columna = Integer.parseInt(columnaInput);

        if (fila >= 0 && fila < nivel.length && columna >= 0 && columna < nivel[0].length) { // que no entre vacio
            if (nivel[fila][columna] == 'L') { // si esta libre
                nivel[fila][columna] = 'O'; //  sale como ocupado
                JOptionPane.showInternalMessageDialog(null, "Se Asigno el parqueo correctamente");
                return true;
            } else {
                JOptionPane.showInternalMessageDialog(null, "El espacio ya esta ocupado. ");
                return false;
            }
        } else {
            JOptionPane.showMessageDialog(null, "Los espacios escogidos estan fuera de rango");
            return false;
        }

    }
}
