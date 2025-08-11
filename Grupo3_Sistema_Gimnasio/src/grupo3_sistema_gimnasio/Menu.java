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
public class Menu {

    /// para crear un metodo para poder llamar los metodos de metodos valga la redondancia
    
    private Metodos gymnova;

    public Menu() {
        gymnova = new Metodos();  // vamos a usar para llamar los metodos 
    }

    public void mostrarMenu() {
        // inicializamos independimente de la opcion que escoja la secretaria 
        gymnova.generarDataInicialActividaddes();
        gymnova.dividirClases();

        int opcion = JOptionPane.showConfirmDialog(null, "¿Desea ingresar al menu?");
        boolean estadoMenu = (opcion == JOptionPane.YES_OPTION);
        while (estadoMenu) {

            int option2 = Integer.parseInt(JOptionPane.showInputDialog("Menu \n"  /// CREAMOS LA VARIABLE PARA EL SWITCH
                    + "1. Clases Disponibles\n"
                    + "2. Editar Clases\n"
                    + "3. Crear Clase \n"
                    + "4. Registrasr a un socio en una clase \n"
                    + "5. Eliminar clase \n"
                    + "6. Sala de Pesas \n"
                    + "7. Cabinas Insonorizadas \n"
                    + "8. Parqueo \n"
                    + "9. Salir"));

            switch (option2) {
                case 1:
                    gymnova.mostrarclases();
                    break;
                case 2:
                    gymnova.editarClases();
                    gymnova.dividirClases();

                    break;

                case 3:
                    gymnova.crearClases();
                    gymnova.dividirClases();

                    break;
                case 4:
                    gymnova.registrarSocioClase();

                    break;
                case 5:
                    gymnova.eliminarClase();

                    break;
                case 6:
                    gymnova.salaPesas();

                    break;
                case 7:
                    gymnova.llenarCabinas();
                    gymnova.reservarCabina();

                    break;
                case 8:
                    gymnova.salaPesas();

                    break;
                case 9:
                    estadoMenu = false;
                    continue;
                default:
                    JOptionPane.showMessageDialog(null, "La opcion no es valida");
                    break;

            }

        }
        // consultamos si desea seguir editando 
        int respuesta = JOptionPane.showConfirmDialog(null, "¿Desea ingresar de nuevo al MENU");
        estadoMenu = (respuesta == JOptionPane.YES_OPTION);

    }

}
