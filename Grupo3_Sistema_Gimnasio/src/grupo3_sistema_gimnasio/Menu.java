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
        gymnova.llenarCabinas();

        int opcion = JOptionPane.showConfirmDialog(null, "¿Desea ingresar al menu?");
        boolean estadoMenu = (opcion == JOptionPane.YES_OPTION);
        while (estadoMenu) {

            int option2 = Integer.parseInt(JOptionPane.showInputDialog("Menu \n"  /// CREAMOS LA VARIABLE PARA EL SWITCH
                    + "1. Clases Disponibles\n"
                    + "2. Editar Clases\n"
                    + "3. Crear Clase \n"
                    + "4. Registrasr a un socio en una clase \n"
                    + "5. Eliminar clase \n"
                    + "6. Eliminar Socio de una clase \n"
                    + "7. Sala de Pesas \n"
                    + "8. Cabinas Insonorizadas \n"
                    + "9. Parqueo \n"
                    + "10. Salir"));

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
                    gymnova.eliminarSocioDeClase();

                    break;
                case 7:
                    gymnova.salaPesas();

                    break;
                case 8:
                    int hacer = Integer.parseInt(JOptionPane.showInputDialog("Digite: \n"
                            + "1. Reservar a un Socio en una Cabina \n"
                            + "2. Ver horarios Resrvados \n"
                            + "3. Eliminar una reserva"));

                    switch (hacer) {
                        case 1:
                            gymnova.reservarCabina();
                            break;
                        case 2:
                            gymnova.mostrarReservarCabinas();
                            break;
                        case 3:
                            gymnova.eliminarReservaDecabina();
                            break;
                    }
                    break;
                case 9:
                    gymnova.salaPesas();

                    break;
                case 10:
                    estadoMenu = false;
                    continue;
                default:
                    JOptionPane.showMessageDialog(null, "La opcion no es valida");
                    break;

            }

            // consultamos si desea seguir editando 
            int respuesta = JOptionPane.showConfirmDialog(null, "¿Desea ingresar de nuevo al MENU");
            estadoMenu = (respuesta == JOptionPane.YES_OPTION);

        }

    }

}
