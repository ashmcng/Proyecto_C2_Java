/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package grupo3_sistema_gimnasio;

import javax.swing.JFrame;
import javax.swing.*;
import java.awt.*;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;
import java.time.LocalTime;

/**
 *
 * @author ashle
 */
public class GUI extends JFrame {

    // COLORES DEL GUI Y BOTONES
    private Color fondo = Color.decode("#a7c4ce");
    private Color colorBoton = Color.decode("#416975");
    private Color colorLetraBoton = Color.decode("#FFFFFF");
    private Font nunitoBold;
    private JPanel panelMenuOpciones; // panel con los botones del menÃº
    private JLabel labelContenido;
    private Parqueo parqueo = new Parqueo();  // ahora es un atributo global para no se elimine los estados del parqueo 
    private JTextArea estadoParqueo;

    public GUI() {

        super("Nova Gym");
        System.out.println("Constructor GUI iniciado");

        // Try-catch para cargar la fuente Nunito-Bold
        try {
            nunitoBold = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/Nunito-Bold.ttf")).deriveFont(18f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(nunitoBold);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
            nunitoBold = new Font("SansSerif", Font.BOLD, 18); // fallback
        }

        // Configuracion de ventana
        setTitle("NOVA GYM");
        setResizable(false);
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(fondo);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        // Panel para el logo (arriba)
        JPanel panelLogo = new JPanel();
        panelLogo.setBackground(Color.decode("#a7c4ce"));
        ImageIcon originalIcon = new ImageIcon("iconos/Logo.png");
        Image scaledImage = originalIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);  // Escalamos para que no tape todo
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        JLabel labelLogo = new JLabel(scaledIcon);
        panelLogo.add(labelLogo);
        add(panelLogo, BorderLayout.NORTH);

        // Panel central (título y otros)
        JPanel panelCentro = new JPanel();
        panelCentro.setBackground(Color.decode("#a7c4ce"));
        panelCentro.setLayout(new BoxLayout(panelCentro, BoxLayout.Y_AXIS));

        JLabel titulo = new JLabel("Sistema de Gestión del Gimnasio");
        titulo.setFont(nunitoBold);
        titulo.setForeground(Color.BLACK);
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelCentro.add(titulo);

        add(panelCentro, BorderLayout.CENTER);

        // MAIN 
        JPanel main = new JPanel();
        JLabel label = new JLabel("BIENVENIDO AL NOVA GYM", SwingConstants.CENTER);
        titulo.setFont(nunitoBold.deriveFont(24f));
        titulo.setForeground(colorBoton);
        titulo.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        main.add(titulo);

        // Mensaje dinamico
        labelContenido = new JLabel("Selecciona una opción del menú.", SwingConstants.CENTER);
        labelContenido.setFont(nunitoBold.deriveFont(20f));
        labelContenido.setForeground(colorBoton);
        labelContenido.setBorder(BorderFactory.createEmptyBorder(5, 10, 15, 10));
        labelContenido.setAlignmentX(Component.CENTER_ALIGNMENT);
        main.add(labelContenido);

        // Agregar parte superior
        add(main, BorderLayout.NORTH);

        // Panel central con boton "Menu"
        JPanel panelCentral = new JPanel();
        panelCentral.setBackground(fondo);
        panelCentral.setLayout(new GridBagLayout());

        JButton btnMenu = new JButton("Menu");
        btnMenu.setFont(nunitoBold.deriveFont(18f));
        btnMenu.setPreferredSize(new Dimension(140, 50));
        panelCentral.add(btnMenu);
        add(panelCentral, BorderLayout.CENTER);

        // Panel con botones de opciones
        panelMenuOpciones = new JPanel();
        panelMenuOpciones.setBackground(fondo);
        panelMenuOpciones.setLayout(new GridLayout(4, 2, 10, 10));
        panelMenuOpciones.setVisible(false);
        panelMenuOpciones.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        String[] textos = {
            "1. Clases Disponibles", "2. Editar Clases", "3. Crear Clase",
            "4. Registrar Socio", "5. Sala de Pesas", "6. Parqueo", "7. Salir"
        };

        for (String texto : textos) {
            JButton btn = new JButton(texto);
            btn.setFont(nunitoBold.deriveFont(18f));
            btn.setPreferredSize(new Dimension(140, 45));
            btn.addActionListener(e -> manejarOpcion(texto.charAt(0)));
            panelMenuOpciones.add(btn);
        }

        add(panelMenuOpciones, BorderLayout.SOUTH);

        btnMenu.addActionListener(e -> {
            btnMenu.setVisible(false);
            panelMenuOpciones.setVisible(true);
            revalidate();
            repaint();
        });

        setVisible(true);

        // Forzar repaint y revalidate después de mostrar
        SwingUtilities.invokeLater(() -> {
            repaint();
            revalidate();
        });

    }

    private void manejarOpcion(char opcion) {
        switch (opcion) {
            case '1':
                labelContenido.setText("Clases disponibles (pendiente implementación)");
                mostrarPanelClasesDisponibles();
                break;
            case '2':
                labelContenido.setText(" Editar clases (pendiente implementación)");
                mostrarPanelEditarActividad();
                break;
            case '3':
                labelContenido.setText("Crear clase (pendiente implementación)");
                mostrarPanelCrearActividad();
                break;
            case '4':
                labelContenido.setText("Registrar socio (pendiente implementación)");
                break;
            case '5':
                labelContenido.setText(" Sala de pesas (pendiente implementación)");
                break;
            case '6':
                labelContenido.setText("Parqueo");
                mostrarPanelParqueo();
                break;
            case '7':
                int res = JOptionPane.showConfirmDialog(this, "¿Desea salir del Menu");
                if (res == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
                break;
            default:
                labelContenido.setText("️Opción no válida");
        }

    }

    // CREAMOS PANEL PARA MODULO DE PARQUE, SU PROPIO VOID PARA SOLO INSTANSEARLO
    private void mostrarPanelParqueo() {
        parqueo.inicializarNiveles();

        // Limpiar panel central y menú
        getContentPane().removeAll();
        repaint();
        revalidate();

        JPanel panelParqueo = new JPanel();
        panelParqueo.setLayout(new BoxLayout(panelParqueo, BoxLayout.Y_AXIS));
        panelParqueo.setBackground(fondo);
        panelParqueo.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        JLabel titulo = new JLabel("Gestión de Parqueo");
        titulo.setFont(nunitoBold.deriveFont(24f));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        titulo.setForeground(colorBoton);
        panelParqueo.add(titulo);
        panelParqueo.add(Box.createVerticalStrut(20));

        // Combo niveles
        String[] niveles = {"G1", "G2", "G3"};
        JComboBox<String> comboNiveles = new JComboBox<>(niveles);
        comboNiveles.setFont(nunitoBold);
        comboNiveles.setMaximumSize(new Dimension(200, 30));
        panelParqueo.add(new JLabel("Seleccione Nivel:"));
        panelParqueo.add(comboNiveles);
        panelParqueo.add(Box.createVerticalStrut(15));

        /** DA ERROR 
         * 
        
        // Combo socios
        String[] socios = {
            Grupo3_Sistema_Gimnasio.getSocio1().getNombreSocio() + " (ID: " + Grupo3_Sistema_Gimnasio.getSocio1().getIdSocio() + ")",
            Grupo3_Sistema_Gimnasio.getSocio3().getNombreSocio() + " (ID: " + Grupo3_Sistema_Gimnasio.getSocio3().getIdSocio() + ")"
        };
        
        * 
        JComboBox<String> comboSocios = new JComboBox<>(socios);
        comboSocios.setFont(nunitoBold);
        comboSocios.setMaximumSize(new Dimension(300, 30));
        panelParqueo.add(new JLabel("Seleccione Socio:"));
        panelParqueo.add(comboSocios);
        panelParqueo.add(Box.createVerticalStrut(15));
*/
        // Área de estado (la hacemos global)
        estadoParqueo = new JTextArea(5, 40);
        estadoParqueo.setEditable(false);
        estadoParqueo.setFont(new Font("Monospaced", Font.PLAIN, 14));
        JScrollPane scroll = new JScrollPane(estadoParqueo);
        panelParqueo.add(scroll);
        panelParqueo.add(Box.createVerticalStrut(15));

        actualizarEstadoParqueo(); // Mostrar el estado al entrar

        // Botón Asignar
        JButton btnAsignar = new JButton("Asignar espacio");
        btnAsignar.setFont(nunitoBold.deriveFont(16f));
        btnAsignar.setBackground(colorBoton);
        btnAsignar.setForeground(colorLetraBoton);
        btnAsignar.setAlignmentX(Component.CENTER_ALIGNMENT);
        panelParqueo.add(btnAsignar);

        /** DA ERROR 
        btnAsignar.addActionListener(e -> {
            String nivel = (String) comboNiveles.getSelectedItem();
            int idSocio = comboSocios.getSelectedIndex() == 0
                    ? Grupo3_Sistema_Gimnasio.getSocio1().getIdSocio()
                    : Grupo3_Sistema_Gimnasio.getSocio3().getIdSocio();

            switch (nivel) {
                case "G1" ->
                    parqueo.asignarEspacio(parqueo.getNivelG1(), parqueo.getIdsG1(), idSocio);
                case "G2" ->
                    parqueo.asignarEspacio(parqueo.getNivelG2(), parqueo.getIdsG2(), idSocio);
                case "G3" ->
                    parqueo.asignarEspacio(parqueo.getNivelG3(), parqueo.getIdsG3(), idSocio);
            }

            actualizarEstadoParqueo();
        });
*/
        // Botón Volver
        JButton btnVolver = new JButton("Volver al menú");
        btnVolver.setFont(nunitoBold.deriveFont(14f));
        btnVolver.setBackground(Color.LIGHT_GRAY);
        btnVolver.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnVolver.addActionListener(e -> {
            getContentPane().removeAll();
            mostrarMenuPrincipal(); 
            revalidate();
            repaint();
        });

        panelParqueo.add(Box.createVerticalStrut(20));
        panelParqueo.add(btnVolver);

        getContentPane().add(panelParqueo, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

// Método auxiliar para actualizar el estado
    private void actualizarEstadoParqueo() {
        estadoParqueo.setText(
                "Nivel G1: " + parqueo.getEstadoNivel(parqueo.getNivelG1()) + "\n"
                + "Nivel G2: " + parqueo.getEstadoNivel(parqueo.getNivelG2()) + "\n"
                + "Nivel G3: " + parqueo.getEstadoNivel(parqueo.getNivelG3())
        );
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GUI());
    }

    private void mostrarPanelClasesDisponibles() {
        // Limpiar el contenido actual
        getContentPane().removeAll();

        JPanel panelClases = new JPanel();
        panelClases.setLayout(new BoxLayout(panelClases, BoxLayout.Y_AXIS));
        panelClases.setBackground(fondo);
        panelClases.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        JLabel titulo = new JLabel("Clases Disponibles");
        titulo.setFont(nunitoBold.deriveFont(24f));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        titulo.setForeground(colorBoton);
        panelClases.add(titulo);
        panelClases.add(Box.createVerticalStrut(20));

        // Panel para las clases
        JPanel listaClases = new JPanel();
        listaClases.setLayout(new BoxLayout(listaClases, BoxLayout.Y_AXIS));
        listaClases.setBackground(fondo);
/** DA ERROR 
        // Clases de la mañana
        JLabel maniana = new JLabel("MAÑANA:");
        maniana.setFont(nunitoBold.deriveFont(18f));
        maniana.setAlignmentX(Component.LEFT_ALIGNMENT);
        listaClases.add(maniana);

        String[] clasesManiana = {
            Grupo3_Sistema_Gimnasio.getYoga().toString(),
            Grupo3_Sistema_Gimnasio.getCrossfit().toString(),
            Grupo3_Sistema_Gimnasio.getZumba().toString()
        };

        for (String clase : clasesManiana) {
            JLabel claseLabel = new JLabel(" • " + clase);
            claseLabel.setFont(nunitoBold.deriveFont(16f));
            claseLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
            listaClases.add(claseLabel);
        }

        listaClases.add(Box.createVerticalStrut(20));

        
        // Clases de la tarde
        JLabel tarde = new JLabel("TARDE:");
        tarde.setFont(nunitoBold.deriveFont(18f));
        tarde.setAlignmentX(Component.LEFT_ALIGNMENT);
        listaClases.add(tarde);

        String[] clasesTarde = {
            Grupo3_Sistema_Gimnasio.getPilates().toString(),
            Grupo3_Sistema_Gimnasio.getFuncionales().toString(),
            Grupo3_Sistema_Gimnasio.getBoxeo().toString()
        };

        for (String clase : clasesTarde) {
            JLabel claseLabel = new JLabel(" • " + clase);
            claseLabel.setFont(nunitoBold.deriveFont(16f));
            claseLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
            listaClases.add(claseLabel);
        }

        // Scroll pane para la lista de clases
        JScrollPane scrollPane = new JScrollPane(listaClases);
        panelClases.add(scrollPane);
        panelClases.add(Box.createVerticalStrut(20));
**/
        // Botón para volver
        JButton volverButton = new JButton("Volver al Menú");
        volverButton.setFont(nunitoBold.deriveFont(16f));
        volverButton.setBackground(colorBoton);
        volverButton.setForeground(colorLetraBoton);
        volverButton.setAlignmentX(Component.CENTER_ALIGNMENT);

    volverButton.addActionListener(e -> mostrarMenuPrincipal());
    panelClases.add(volverButton);

        getContentPane().add(panelClases, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    private void mostrarPanelCrearActividad() {
        getContentPane().removeAll();
        repaint();
        revalidate();

        JPanel panelActividad = new JPanel();
        panelActividad.setLayout(new BoxLayout(panelActividad, BoxLayout.Y_AXIS));
        panelActividad.setBackground(fondo);
        panelActividad.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        JLabel titulo = new JLabel("Crear Nueva Actividad");
        titulo.setFont(nunitoBold.deriveFont(24f));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        titulo.setForeground(colorBoton);
        panelActividad.add(titulo);
        panelActividad.add(Box.createVerticalStrut(20));

        // Campos del formulario
        JTextField campoNombre = new JTextField();
        campoNombre.setMaximumSize(new Dimension(200, 30));
        panelActividad.add(new JLabel("Nombre:"));
        panelActividad.add(campoNombre);
        panelActividad.add(Box.createVerticalStrut(15));

        JTextField campoHorario = new JTextField();
        campoHorario.setMaximumSize(new Dimension(200, 30));
        panelActividad.add(new JLabel("Horario:"));
        panelActividad.add(campoHorario);
        panelActividad.add(Box.createVerticalStrut(15));

        JTextField campoCapacidad = new JTextField();
        campoCapacidad.setMaximumSize(new Dimension(200, 30));
        panelActividad.add(new JLabel("Capacidad:"));
        panelActividad.add(campoCapacidad);
        panelActividad.add(Box.createVerticalStrut(20));

        // Botón para crear la actividad
        JButton btnCrear = new JButton("Crear Actividad");
        btnCrear.setFont(nunitoBold.deriveFont(16f));
        btnCrear.setBackground(colorBoton);
        btnCrear.setForeground(colorLetraBoton);
        btnCrear.setAlignmentX(Component.CENTER_ALIGNMENT);

        btnCrear.addActionListener(e -> {
            try {
                String nombre = campoNombre.getText();
                String horario = campoHorario.getText();
                int capacidad = Integer.parseInt(campoCapacidad.getText());

                // Crea una nueva actividad y agregarla a las existentes
                // ESTA DANDO ERROR Actividad nuevaActividad = new Actividad(nombre, horario, capacidad);

                // Se llaman a los métodos originales para incluir la nueva actividad
               // ESTA DANDO ERROR  agregarActividadASistema(nuevaActividad);
                mostrarPanelClasesDisponibles();

                //Por si se ingresa una letra en donde va la capacidad del numero
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "La capacidad debe ser un número", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        panelActividad.add(btnCrear);
        panelActividad.add(Box.createVerticalStrut(20));

        JButton btnVolver = new JButton("Volver al menú");
        btnVolver.setFont(nunitoBold.deriveFont(14f));
        btnVolver.setBackground(Color.LIGHT_GRAY);
        btnVolver.setAlignmentX(Component.CENTER_ALIGNMENT);
    btnVolver.addActionListener(e -> mostrarMenuPrincipal());
         
        panelActividad.add(btnVolver);

        getContentPane().add(panelActividad, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    private void agregarActividadASistema(Actividad nuevaActividad) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void mostrarPanelEditarActividad() {
    getContentPane().removeAll();
    repaint();
    revalidate();

    JPanel panelPrincipal = new JPanel();
    panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));
    panelPrincipal.setBackground(fondo);
    panelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
    
    JLabel titulo = new JLabel("Editar Actividad");
    titulo.setFont(nunitoBold.deriveFont(24f));
    titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
    titulo.setForeground(colorBoton);
    panelPrincipal.add(titulo);
    panelPrincipal.add(Box.createVerticalStrut(30));

    // Panel de selección de actividad
    JPanel panelSeleccion = new JPanel();
    panelSeleccion.setLayout(new BoxLayout(panelSeleccion, BoxLayout.Y_AXIS));
    panelSeleccion.setBackground(fondo);
    panelSeleccion.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

    JLabel lblActividades = new JLabel("Seleccione la actividad:");
    lblActividades.setFont(nunitoBold.deriveFont(16f));
    panelSeleccion.add(lblActividades);
    panelSeleccion.add(Box.createVerticalStrut(10));

    String[] actividades = {"Yoga", "Crossfit", "Zumba", "Pilates", "Funcionales", "Boxeo"};
    JComboBox<String> comboActividades = new JComboBox<>(actividades);
    comboActividades.setFont(nunitoBold);
    comboActividades.setMaximumSize(new Dimension(300, 30));
    panelSeleccion.add(comboActividades);
    panelPrincipal.add(panelSeleccion);
    panelPrincipal.add(Box.createVerticalStrut(20));

    // Panel de edición
    JPanel panelEdicion = new JPanel();
    panelEdicion.setLayout(new BoxLayout(panelEdicion, BoxLayout.Y_AXIS));
    panelEdicion.setBackground(fondo);
    panelEdicion.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

    // Campo Nombre
    JPanel panelNombre = new JPanel(new FlowLayout(FlowLayout.LEFT));
    panelNombre.setBackground(fondo);
    JLabel lblNombre = new JLabel("Nombre:");
    lblNombre.setFont(nunitoBold);
    JTextField txtNombre = new JTextField(20);
    txtNombre.setFont(nunitoBold);
    panelNombre.add(lblNombre);
    panelNombre.add(txtNombre);
    panelEdicion.add(panelNombre);

    // Campo Horario
    JPanel panelHorario = new JPanel(new FlowLayout(FlowLayout.LEFT));
    panelHorario.setBackground(fondo);
    JLabel lblHorario = new JLabel("Horario:");
    lblHorario.setFont(nunitoBold);
    JTextField txtHorario = new JTextField(10);
    txtHorario.setFont(nunitoBold);
    panelHorario.add(lblHorario);
    panelHorario.add(txtHorario);
    panelEdicion.add(panelHorario);

    // Campo Capacidad
    JPanel panelCapacidad = new JPanel(new FlowLayout(FlowLayout.LEFT));
    panelCapacidad.setBackground(fondo);
    JLabel lblCapacidad = new JLabel("Capacidad:");
    lblCapacidad.setFont(nunitoBold);
    JTextField txtCapacidad = new JTextField(5);
    txtCapacidad.setFont(nunitoBold);
    panelCapacidad.add(lblCapacidad);
    panelCapacidad.add(txtCapacidad);
    panelEdicion.add(panelCapacidad);

    // Campo Inscritos
    JPanel panelInscritos = new JPanel(new FlowLayout(FlowLayout.LEFT));
    panelInscritos.setBackground(fondo);
    JLabel lblInscritos = new JLabel("Inscritos:");
    lblInscritos.setFont(nunitoBold);
    JTextField txtInscritos = new JTextField(5);
    txtInscritos.setFont(nunitoBold);
    panelInscritos.add(lblInscritos);
    panelInscritos.add(txtInscritos);
    panelEdicion.add(panelInscritos);

    panelPrincipal.add(panelEdicion);
    panelPrincipal.add(Box.createVerticalStrut(30));

    // Botón Guardar
    JButton btnGuardar = new JButton("Guardar Cambios");
    btnGuardar.setFont(nunitoBold.deriveFont(16f));
    btnGuardar.setBackground(colorBoton);
    btnGuardar.setForeground(colorLetraBoton);
    btnGuardar.setAlignmentX(Component.CENTER_ALIGNMENT);
    btnGuardar.addActionListener(e -> {
        // Validar campos
        if (txtNombre.getText().trim().isEmpty() || txtHorario.getText().trim().isEmpty() ||
            txtCapacidad.getText().trim().isEmpty() || txtInscritos.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Complete todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            // Obtener valores
            String nombre = txtNombre.getText().trim();
            String horario = txtHorario.getText().trim();
            int capacidad = Integer.parseInt(txtCapacidad.getText().trim());
            int inscritos = Integer.parseInt(txtInscritos.getText().trim());

            // Validar que inscritos no exceda capacidad
            if (inscritos > capacidad) {
                JOptionPane.showMessageDialog(null, 
                    "Los inscritos no pueden exceder la capacidad", 
                    "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Actualizar la actividad seleccionada
            String actividadSeleccionada = (String)comboActividades.getSelectedItem();
            JOptionPane.showMessageDialog(null, 
                "Cambios guardados exitosamente!", 
                "Éxito", JOptionPane.INFORMATION_MESSAGE);

            mostrarMenuPrincipal();
       
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, 
                "Capacidad e Inscritos deben ser números", 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    });

    panelPrincipal.add(btnGuardar);
    panelPrincipal.add(Box.createVerticalStrut(20));

    // Botón Volver
    JButton btnVolver = new JButton("Volver al Menú");
    btnVolver.setFont(nunitoBold.deriveFont(16f));
    btnVolver.setBackground(Color.LIGHT_GRAY);
    btnVolver.setAlignmentX(Component.CENTER_ALIGNMENT);
    btnVolver.addActionListener(e -> mostrarMenuPrincipal());

    panelPrincipal.add(btnVolver);

    getContentPane().add(panelPrincipal, BorderLayout.CENTER);
    revalidate();
    repaint();
}

    private void mostrarMenuPrincipal() {
    // Limpiar el panel principal completamente
    getContentPane().removeAll();
    repaint();
    revalidate();

    // Se usa las mismas lineas de codigo del inicio
    setLayout(new BorderLayout());
    getContentPane().setBackground(fondo);

    JPanel panelLogo = new JPanel();
    panelLogo.setBackground(Color.decode("#a7c4ce"));
    ImageIcon originalIcon = new ImageIcon("iconos/Logo.png");
    Image scaledImage = originalIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
    ImageIcon scaledIcon = new ImageIcon(scaledImage);
    JLabel labelLogo = new JLabel(scaledIcon);
    panelLogo.add(labelLogo);
    add(panelLogo, BorderLayout.NORTH);

    JPanel panelCentro = new JPanel();
    panelCentro.setLayout(new BoxLayout(panelCentro, BoxLayout.Y_AXIS));
    panelCentro.setBackground(Color.decode("#a7c4ce"));

    JLabel titulo = new JLabel("Sistema de Gestión del Gimnasio");
    titulo.setFont(nunitoBold.deriveFont(24f));
    titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
    titulo.setForeground(colorBoton);
    panelCentro.add(titulo);
    panelCentro.add(Box.createVerticalStrut(20));

    labelContenido = new JLabel("Selecciona una opción del menú.", SwingConstants.CENTER);
    labelContenido.setFont(nunitoBold.deriveFont(20f));
    labelContenido.setForeground(colorBoton);
    labelContenido.setBorder(BorderFactory.createEmptyBorder(5, 10, 15, 10));
    labelContenido.setAlignmentX(Component.CENTER_ALIGNMENT);
    panelCentro.add(labelContenido);

    add(panelCentro, BorderLayout.CENTER);

    panelMenuOpciones = new JPanel();
    panelMenuOpciones.setBackground(fondo);
    panelMenuOpciones.setLayout(new GridLayout(4, 2, 10, 10));
    panelMenuOpciones.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

    String[] textos = {
        "1. Clases Disponibles", "2. Editar Clases", "3. Crear Clase",
        "4. Registrar Socio", "5. Sala de Pesas", "6. Parqueo", "7. Salir"
    };

    for (String texto : textos) {
        JButton btn = new JButton(texto);
        btn.setFont(nunitoBold.deriveFont(18f));
        btn.setPreferredSize(new Dimension(140, 45));
        btn.addActionListener(e -> manejarOpcion(texto.charAt(0)));
        panelMenuOpciones.add(btn);
    }

    add(panelMenuOpciones, BorderLayout.SOUTH);

 
    setVisible(true);
    revalidate();
    repaint();
}

}
