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
public class Actividad {

    /// DEFINIMOS LAS VARIABLES
    
    private String nombreActividad;
    private String horario;
    private int capacidadActividad;
    private int cantidadActual;

    /// INICIALIZAMOS 

    public String getNombreActividad() {
        return nombreActividad;
    }

    public void setNombreActividad(String nombreActividad) {
        this.nombreActividad = nombreActividad;
    }

    public int getCapacidadActividad() {
        return capacidadActividad;
    }

    public void setCapacidadActividad(int capacidadActividad) {
        this.capacidadActividad = capacidadActividad;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public int getCantidadActual() {
        return cantidadActual;
    }

    public void setCantidadActual(int cantidadActual) {
        this.cantidadActual = cantidadActual;
    }

    public Actividad(String nombreActividad, String horario, int capacidadActividad, int cantidadActual) {
        this.nombreActividad = nombreActividad;
        this.horario = horario;
        this.capacidadActividad = capacidadActividad;
        this.cantidadActual = cantidadActual;
    }

    ///METODO PARA CREAR UNA ACTIVIDAD
    public void crearActividad() {

        /// SE SOLICITAN LOS VALORES DE LA CLASE
        
        String inputNombre = JOptionPane.showInputDialog("Digite el nombre de la actividad que desea crear: ");
        String inputHorario = JOptionPane.showInputDialog("Digite el horario de la clase en estandar militar: *ESCRIBA Horario ho:ra ");
        int inputcapacidadActividad = Integer.parseInt(JOptionPane.showInputDialog("Digite la capacidad maxima de personas que se pueden inscribir SOLO SE PERMITEN NUMEROS:"));
        int inputCantidad = Integer.parseInt(JOptionPane.showInputDialog("Digite la cantidad de personas ya inscritas en caso de que aplique si no ingrese 0: "));

        /// SE ASIGNAN LOS VALORES THIS.SET PARA CREAR MULTIPLES ACTIVIDADES 
        
        this.setNombreActividad(inputNombre);
        this.setCapacidadActividad(inputcapacidadActividad);
        this.setHorario(inputHorario);
        this.setCantidadActual(inputCantidad);
    }

    public void editarCuestionario() {

        // CREMOS WHILE DE EDITAR DATOS INGRESADOS UNA  DETRMINE CUANDO SALIR DEL WHILE 
        // VAMOS A USAR THIS.SET PARA PODER EDITAR SOLO EL OBJETO LLAMADO 
        
        boolean editarDatos = true;  // creamos este dato booleano para que se pueda volver a preguntar si desea cambiar algo + y salir del while

        while (editarDatos) {

            int option2 = Integer.parseInt(JOptionPane.showInputDialog("¿Que Deseas Editar?\n"  /// CREAMOS LA VARIABLE PARA EL SWITCH 
                    + "1. Nombre de la activdad\n"
                    + "2. Horario de la activdad\n"
                    + "3. Capacidad Maxima \n"
                    + "4. Cantidad de inscritos  \n"
                    + "5. Salir"));
            switch (option2) {

                case 1:
                    String inputNombre1 = JOptionPane.showInputDialog("Digite el nombre de la actividad que desea crear: ");
                    this.setNombreActividad(inputNombre1);
                    break;
                case 2:
                    String inputHorario1 = JOptionPane.showInputDialog("Digite el horario de la clase en estandar militar: *ESCRIBA Horario ho:ra ");
                    this.setHorario(inputHorario1);
                    break;
                case 3:
                    /// VALIDAMOS QUE LA NUEVA CAPACIDAD MÁXIMA NO SEA MENOR A LAS PERSONAS ACTUALES
                    int inputcapacidadActividad1 = Integer.parseInt(JOptionPane.showInputDialog("Digite la capacidad maxima de personas que se pueden inscribir SOLO SE PERMITEN NUMEROS:"));
                    if (inputcapacidadActividad1 < this.getCantidadActual()) {
                        JOptionPane.showMessageDialog(null, "Error: ya hay más personas inscritas que esa capacidad.");
                    } else {
                        this.setCapacidadActividad(inputcapacidadActividad1);
                    }
                    break;
                case 4:
                    int inputCantidad1 = Integer.parseInt(JOptionPane.showInputDialog("Digite la cantidad de personas ya inscritas en caso de que aplique si no ingrese 0: "));
                    this.setCantidadActual(inputCantidad1);
                    break;
                case 5:
                    editarDatos = false;
                    continue; //continua con el codigo  
                default:
                    JOptionPane.showMessageDialog(null, "La opcion no es valida");
                    continue;
            }

            /// PREGUNTAMOS SI DESEA EDITAR ALGO MAS
            
            int respuesta = JOptionPane.showConfirmDialog(null, "¿Desea cambiar algo más?");
            editarDatos = (respuesta == JOptionPane.YES_OPTION);
        }

    }
    
    public boolean registrarSocio(Socio socio) { // para pruebas socio1
        if ((!socio.isMembresiaSocio())) { // diferente a activa
            JOptionPane.showInternalMessageDialog(null, "La membresia no esta activa no se puede registrar en la sala de pesas....");
            return false;
        }
        if (cantidadActual >= capacidadActividad) { // VALIDACION SI YA ESTRA LLENO 
            JOptionPane.showInternalMessageDialog(null, "La sala de pesas ya esta llena...");
            return false;
        }
        // si cumple lo requerido se registra
        cantidadActual++;
        JOptionPane.showInternalMessageDialog(null, "SE HA REGISTRADO CON EXITO, PERSONAS EN LA SALA:  " + cantidadActual);
        return true;
    }

    @Override
    public String toString() {
        return "Nombre Actividad: " + nombreActividad + " " + "Capacidad Maxima: " + capacidadActividad + " " + "Cantidad actual: " + cantidadActual + "  " + horario;
    }

}
