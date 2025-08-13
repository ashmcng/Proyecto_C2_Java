package grupo3_sistema_gimnasio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

public class GUI extends JFrame {

    private Color fondo = Color.decode("#a7c4ce");
    private Color colorBoton = Color.decode("#416975");
    private Color colorLetraBoton = Color.decode("#FFFFFF");
    private Font nunitoBold;
    private JPanel mainContentPanel;
    private JTextArea estadoParqueo;
    private Metodos gymnova = new Metodos();
    
    public GUI() {
        super("Nova Gym");

        try {
            nunitoBold = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/Nunito-Bold.ttf")).deriveFont(18f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(nunitoBold);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
            nunitoBold = new Font("SansSerif", Font.BOLD, 18);
        }

        setTitle("NOVA GYM");
        setResizable(false);
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(fondo);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        
        JPanel panelSuperior = new JPanel();
        panelSuperior.setLayout(new BoxLayout(panelSuperior, BoxLayout.Y_AXIS));
        panelSuperior.setBackground(fondo);
        
        ImageIcon originalIcon = new ImageIcon("iconos/Logo.png");
        Image scaledImage = originalIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        JLabel labelLogo = new JLabel(new ImageIcon(scaledImage));
        labelLogo.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel titulo = new JLabel("Sistema de Gestión del Gimnasio", SwingConstants.CENTER);
        titulo.setFont(nunitoBold.deriveFont(24f));
        titulo.setForeground(colorBoton);
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        panelSuperior.add(labelLogo);
        panelSuperior.add(titulo);
        add(panelSuperior, BorderLayout.NORTH);

        mainContentPanel = new JPanel();
        mainContentPanel.setLayout(new GridBagLayout());
        mainContentPanel.setBackground(fondo);
        add(mainContentPanel, BorderLayout.CENTER);

        mostrarMenuPrincipal();
        
        setVisible(true);

        gymnova.generarDataInicialActividaddes();
        gymnova.dividirClases();
        gymnova.llenarCabinas();
        gymnova.inicializarParqueo();
        gymnova.incializarEspaciosRecreativos();
    }
    
    private void mostrarMenuPrincipal() {
        mainContentPanel.removeAll();
        mainContentPanel.setLayout(new GridLayout(7, 2, 10, 10));
        mainContentPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        
        String[] textos = {
            "1. Clases Disponibles", "2. Editar Clases", "3. Crear Clase",
            "4. Registrar Socio en Clase", "5. Eliminar clase", "6. Eliminar Socio de Clase",
            "7. Ver inscritos en cada clase", "8. Sala de Pesas", "9. Cabinas Insonorizadas",
            "10. Auditorio Fitness", "11. Espacios Recreativos", "12. Parqueo", "13. Salir"
        };
        
        for (String texto : textos) {
            JButton btn = new JButton(texto);
            btn.setFont(nunitoBold.deriveFont(18f));
            btn.addActionListener(e -> manejarOpcion(texto.substring(0, texto.indexOf('.')).trim()));
            mainContentPanel.add(btn);
        }
        
        revalidate();
        repaint();
    }
    
    private void regresarMenuPrincipal() {
        mainContentPanel.removeAll();
        mostrarMenuPrincipal();
        revalidate();
        repaint();
    }
    
    private JButton createReturnButton() {
        JButton btnVolver = new JButton("Volver al menú");
        btnVolver.setFont(nunitoBold.deriveFont(14f));
        btnVolver.setBackground(Color.LIGHT_GRAY);
        btnVolver.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnVolver.addActionListener(e -> regresarMenuPrincipal());
        return btnVolver;
    }

    private void manejarOpcion(String opcion) {
        switch (opcion) {
            case "1":
                mostrarPanelClasesDisponibles();
                break;
            case "2":
                mostrarPanelEditarClase();
                break;
            case "3":
                mostrarPanelCrearClase();
                break;
            case "4":
                mostrarPanelRegistrarSocioEnClase();
                break;
            case "5":
                mostrarPanelEliminarClase();
                break;
            case "6":
                mostrarPanelEliminarSocioDeClase();
                break;
            case "7":
                mostrarPanelVerInscritos();
                break;
            case "8":
                mostrarPanelSalaPesas();
                break;
            case "9":
                mostrarPanelCabinas();
                break;
            case "10":
                mostrarPanelAuditorio();
                break;
            case "11":
                mostrarPanelEspaciosRecreativos();
                break;
            case "12":
                mostrarPanelParqueo();
                break;
            case "13":
                int res = JOptionPane.showConfirmDialog(this, "¿Desea salir del programa?");
                if (res == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
                break;
            default:
                JOptionPane.showMessageDialog(this, "Opción no válida");
        }
    }
    
    // PANELES DE MENU
    private void mostrarPanelClasesDisponibles() {
        mainContentPanel.removeAll();
        mainContentPanel.setLayout(new BoxLayout(mainContentPanel, BoxLayout.Y_AXIS));
        
        JLabel titulo = new JLabel("Clases Disponibles", SwingConstants.CENTER);
        titulo.setFont(nunitoBold.deriveFont(24f));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainContentPanel.add(titulo);

        JTextArea areaClases = new JTextArea(15, 50);
        areaClases.setEditable(false);
        areaClases.setFont(new Font("Monospaced", Font.PLAIN, 14));
        areaClases.setText(gymnova.verListaClasesGUI());
        JScrollPane scroll = new JScrollPane(areaClases);
        mainContentPanel.add(scroll);
        
        mainContentPanel.add(Box.createVerticalStrut(10));
        mainContentPanel.add(createReturnButton());
        
        revalidate();
        repaint();
    }
    
    private void mostrarPanelEditarClase() {
        mainContentPanel.removeAll();
        mainContentPanel.setLayout(new BoxLayout(mainContentPanel, BoxLayout.Y_AXIS));
        mainContentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titulo = new JLabel("Editar Clase", SwingConstants.CENTER);
        titulo.setFont(nunitoBold.deriveFont(24f));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainContentPanel.add(titulo);
        
        mainContentPanel.add(Box.createVerticalStrut(10));
        
        JTextField txtIdClase = new JTextField(10);
        JTextField txtNuevoNombre = new JTextField(10);
        JTextField txtNuevaCapacidad = new JTextField(10);
        
        mainContentPanel.add(new JLabel("ID de la clase a editar:"));
        mainContentPanel.add(txtIdClase);
        mainContentPanel.add(new JLabel("Nuevo nombre (opcional):"));
        mainContentPanel.add(txtNuevoNombre);
        mainContentPanel.add(new JLabel("Nueva capacidad (opcional):"));
        mainContentPanel.add(txtNuevaCapacidad);
        
        JButton btnEditar = new JButton("Editar Clase");
        btnEditar.addActionListener(e -> {
            try {
                int id = Integer.parseInt(txtIdClase.getText());
                String nuevoNombre = txtNuevoNombre.getText().isEmpty() ? null : txtNuevoNombre.getText();
                String capacidadStr = txtNuevaCapacidad.getText();
                int nuevaCapacidad = capacidadStr.isEmpty() ? -1 : Integer.parseInt(capacidadStr);

                if (gymnova.editarClase(id, nuevoNombre, nuevaCapacidad)) {
                    JOptionPane.showMessageDialog(this, "Clase editada correctamente.");
                } else {
                    JOptionPane.showMessageDialog(this, "Error al editar la clase. ID no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
                }
                regresarMenuPrincipal();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "ID o capacidad inválidos.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        
        mainContentPanel.add(btnEditar);
        mainContentPanel.add(createReturnButton());
        
        revalidate();
        repaint();
    }

    private void mostrarPanelCrearClase() {
        mainContentPanel.removeAll();
        mainContentPanel.setLayout(new BoxLayout(mainContentPanel, BoxLayout.Y_AXIS));
        mainContentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titulo = new JLabel("Crear Clase", SwingConstants.CENTER);
        titulo.setFont(nunitoBold.deriveFont(24f));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainContentPanel.add(titulo);

        mainContentPanel.add(Box.createVerticalStrut(10));
        
        JTextField txtNombre = new JTextField(10);
        JTextField txtCapacidad = new JTextField(10);
        JTextField txtHora = new JTextField(10);
        JTextField txtMinuto = new JTextField(10);

        mainContentPanel.add(new JLabel("Nombre:"));
        mainContentPanel.add(txtNombre);
        mainContentPanel.add(new JLabel("Capacidad:"));
        mainContentPanel.add(txtCapacidad);
        mainContentPanel.add(new JLabel("Hora (0-23):"));
        mainContentPanel.add(txtHora);
        mainContentPanel.add(new JLabel("Minuto (0-59):"));
        mainContentPanel.add(txtMinuto);

        JButton btnCrear = new JButton("Crear Clase");
        btnCrear.addActionListener(e -> {
            try {
                String nombre = txtNombre.getText();
                int capacidad = Integer.parseInt(txtCapacidad.getText());
                int hora = Integer.parseInt(txtHora.getText());
                int minuto = Integer.parseInt(txtMinuto.getText());
                
                if (gymnova.crearClase(nombre, capacidad, hora, minuto)) {
                     JOptionPane.showMessageDialog(this, "Clase creada correctamente.");
                } else {
                    JOptionPane.showMessageDialog(this, "Error al crear la clase.", "Error", JOptionPane.ERROR_MESSAGE);
                }
                regresarMenuPrincipal();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Datos inválidos.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        mainContentPanel.add(btnCrear);
        mainContentPanel.add(createReturnButton());
        
        revalidate();
        repaint();
    }

    private void mostrarPanelRegistrarSocioEnClase() {
        mainContentPanel.removeAll();
        mainContentPanel.setLayout(new BoxLayout(mainContentPanel, BoxLayout.Y_AXIS));
        mainContentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titulo = new JLabel("Registrar Socio en Clase", SwingConstants.CENTER);
        titulo.setFont(nunitoBold.deriveFont(24f));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainContentPanel.add(titulo);

        JTextArea areaClases = new JTextArea(5, 50);
        areaClases.setEditable(false);
        areaClases.setFont(new Font("Monospaced", Font.PLAIN, 14));
        areaClases.setText(gymnova.verListaClasesGUI());
        JScrollPane scroll = new JScrollPane(areaClases);
        mainContentPanel.add(scroll);
        
        mainContentPanel.add(Box.createVerticalStrut(10));
        
        JTextField txtIdSocio = new JTextField(10);
        JTextField txtIdClase = new JTextField(10);
        
        mainContentPanel.add(new JLabel("ID del Socio:"));
        mainContentPanel.add(txtIdSocio);
        mainContentPanel.add(new JLabel("ID de la Clase:"));
        mainContentPanel.add(txtIdClase);

        JButton btnRegistrar = new JButton("Registrar");
        btnRegistrar.addActionListener(e -> {
            try {
                int idSocio = Integer.parseInt(txtIdSocio.getText());
                int idClase = Integer.parseInt(txtIdClase.getText());
                
                String resultado = gymnova.registrarSocioEnClase(idSocio, idClase);
                JOptionPane.showMessageDialog(this, resultado);
                regresarMenuPrincipal();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "ID de Socio o Clase inválido.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        
        mainContentPanel.add(btnRegistrar);
        mainContentPanel.add(createReturnButton());
        
        revalidate();
        repaint();
    }
    
    private void mostrarPanelEliminarClase() {
        mainContentPanel.removeAll();
        mainContentPanel.setLayout(new BoxLayout(mainContentPanel, BoxLayout.Y_AXIS));
        mainContentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titulo = new JLabel("Eliminar Clase", SwingConstants.CENTER);
        titulo.setFont(nunitoBold.deriveFont(24f));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainContentPanel.add(titulo);
        
        JTextArea areaClases = new JTextArea(5, 50);
        areaClases.setEditable(false);
        areaClases.setFont(new Font("Monospaced", Font.PLAIN, 14));
        areaClases.setText(gymnova.verListaClasesGUI());
        JScrollPane scroll = new JScrollPane(areaClases);
        mainContentPanel.add(scroll);

        mainContentPanel.add(Box.createVerticalStrut(10));

        JTextField txtIdClase = new JTextField(10);
        mainContentPanel.add(new JLabel("ID de la clase a eliminar:"));
        mainContentPanel.add(txtIdClase);

        JButton btnEliminar = new JButton("Eliminar Clase");
        btnEliminar.addActionListener(e -> {
            try {
                int id = Integer.parseInt(txtIdClase.getText());
                if(gymnova.eliminarClase(id)) {
                    JOptionPane.showMessageDialog(this, "Clase eliminada correctamente.");
                } else {
                    JOptionPane.showMessageDialog(this, "Error al eliminar la clase. ID no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
                }
                regresarMenuPrincipal();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "ID inválido.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        
        mainContentPanel.add(btnEliminar);
        mainContentPanel.add(createReturnButton());
        
        revalidate();
        repaint();
    }
    
    private void mostrarPanelEliminarSocioDeClase() {
        mainContentPanel.removeAll();
        mainContentPanel.setLayout(new BoxLayout(mainContentPanel, BoxLayout.Y_AXIS));
        mainContentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titulo = new JLabel("Eliminar Socio de Clase", SwingConstants.CENTER);
        titulo.setFont(nunitoBold.deriveFont(24f));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainContentPanel.add(titulo);
        
        JTextArea areaClases = new JTextArea(5, 50);
        areaClases.setEditable(false);
        areaClases.setFont(new Font("Monospaced", Font.PLAIN, 14));
        areaClases.setText(gymnova.verListaClasesGUI());
        JScrollPane scroll = new JScrollPane(areaClases);
        mainContentPanel.add(scroll);

        mainContentPanel.add(Box.createVerticalStrut(10));

        JTextField txtIdSocio = new JTextField(10);
        JTextField txtIdClase = new JTextField(10);
        
        mainContentPanel.add(new JLabel("ID del Socio a eliminar:"));
        mainContentPanel.add(txtIdSocio);
        mainContentPanel.add(new JLabel("ID de la Clase:"));
        mainContentPanel.add(txtIdClase);

        JButton btnEliminar = new JButton("Eliminar Socio");
        btnEliminar.addActionListener(e -> {
            try {
                int idSocio = Integer.parseInt(txtIdSocio.getText());
                int idClase = Integer.parseInt(txtIdClase.getText());
                String resultado = gymnova.eliminarSocioDeClase(idSocio, idClase);
                JOptionPane.showMessageDialog(this, resultado);
                regresarMenuPrincipal();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "ID de Socio o Clase inválido.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        
        mainContentPanel.add(btnEliminar);
        mainContentPanel.add(createReturnButton());
        
        revalidate();
        repaint();
    }

    private void mostrarPanelVerInscritos() {
        mainContentPanel.removeAll();
        mainContentPanel.setLayout(new BoxLayout(mainContentPanel, BoxLayout.Y_AXIS));
        mainContentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titulo = new JLabel("Ver Inscritos por Clase", SwingConstants.CENTER);
        titulo.setFont(nunitoBold.deriveFont(24f));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainContentPanel.add(titulo);
        
        JTextArea areaInscritos = new JTextArea(15, 50);
        areaInscritos.setEditable(false);
        areaInscritos.setFont(new Font("Monospaced", Font.PLAIN, 14));
        areaInscritos.setText(gymnova.verInscritosEnTodasLasClases());
        JScrollPane scroll = new JScrollPane(areaInscritos);
        mainContentPanel.add(scroll);
        
        mainContentPanel.add(Box.createVerticalStrut(10));
        mainContentPanel.add(createReturnButton());
        
        revalidate();
        repaint();
    }

    private void mostrarPanelSalaPesas() {
        mainContentPanel.removeAll();
        mainContentPanel.setLayout(new BoxLayout(mainContentPanel, BoxLayout.Y_AXIS));
        mainContentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        JLabel titulo = new JLabel("Sala de Pesas", SwingConstants.CENTER);
        titulo.setFont(nunitoBold.deriveFont(24f));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainContentPanel.add(titulo);

        JLabel labelEstado = new JLabel();
        labelEstado.setFont(nunitoBold.deriveFont(20f));
        labelEstado.setText(gymnova.obtenerEstadoSalaPesas());
        labelEstado.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainContentPanel.add(labelEstado);
        
        mainContentPanel.add(Box.createVerticalStrut(20));

        JTextField txtIdSocio = new JTextField(10);
        JPanel inputPanel = new JPanel();
        inputPanel.setBackground(fondo);
        inputPanel.add(new JLabel("ID de Socio:"));
        inputPanel.add(txtIdSocio);
        mainContentPanel.add(inputPanel);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(fondo);
        
        JButton btnEntrar = new JButton("Ingresar");
        btnEntrar.addActionListener(e -> {
            try {
                int idSocio = Integer.parseInt(txtIdSocio.getText());
                String resultado = gymnova.ingresarSalaPesas(idSocio);
                JOptionPane.showMessageDialog(this, resultado);
                labelEstado.setText(gymnova.obtenerEstadoSalaPesas());
                regresarMenuPrincipal();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "ID inválido.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        
        JButton btnSalir = new JButton("Salir");
        btnSalir.addActionListener(e -> {
             try {
                int idSocio = Integer.parseInt(txtIdSocio.getText());
                String resultado = gymnova.salirSalaPesas(idSocio);
                JOptionPane.showMessageDialog(this, resultado);
                labelEstado.setText(gymnova.obtenerEstadoSalaPesas());
                regresarMenuPrincipal();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "ID inválido.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        
        buttonPanel.add(btnEntrar);
        buttonPanel.add(btnSalir);
        
        mainContentPanel.add(buttonPanel);
        mainContentPanel.add(createReturnButton());

        revalidate();
        repaint();
    }
    
    private void mostrarPanelCabinas() {
        mainContentPanel.removeAll();
        mainContentPanel.setLayout(new BoxLayout(mainContentPanel, BoxLayout.Y_AXIS));
        mainContentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titulo = new JLabel("Cabinas Insonorizadas", SwingConstants.CENTER);
        titulo.setFont(nunitoBold.deriveFont(24f));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainContentPanel.add(titulo);

        JTextArea areaCabinas = new JTextArea(5, 50);
        areaCabinas.setEditable(false);
        areaCabinas.setFont(new Font("Monospaced", Font.PLAIN, 14));
        JScrollPane scroll = new JScrollPane(areaCabinas);
        mainContentPanel.add(scroll);
        
        Runnable actualizarCabinas = () -> areaCabinas.setText(gymnova.mostrarReservarCabinas());
        actualizarCabinas.run();

        JTextField txtIdSocio = new JTextField(10);
        JTextField txtCabina = new JTextField(10);

        mainContentPanel.add(new JLabel("ID Socio:"));
        mainContentPanel.add(txtIdSocio);
        mainContentPanel.add(new JLabel("ID Cabina (1-3):"));
        mainContentPanel.add(txtCabina);

        JPanel buttonPanel = new JPanel();
        JButton btnReservar = new JButton("Reservar");
        btnReservar.addActionListener(e -> {
            try {
                int idSocio = Integer.parseInt(txtIdSocio.getText());
                int cabinaId = Integer.parseInt(txtCabina.getText());
                String resultado = gymnova.reservarCabina(idSocio, cabinaId);
                JOptionPane.showMessageDialog(this, resultado);
                actualizarCabinas.run();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "ID de Socio o Cabina inválido.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        JButton btnEliminarReserva = new JButton("Eliminar Reserva");
        btnEliminarReserva.addActionListener(e -> {
            try {
                int idSocio = Integer.parseInt(txtIdSocio.getText());
                int cabinaId = Integer.parseInt(txtCabina.getText());
                String resultado = gymnova.eliminarReservaDecabina(idSocio, cabinaId);
                JOptionPane.showMessageDialog(this, resultado);
                actualizarCabinas.run();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "ID de Socio o Cabina inválido.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        
        buttonPanel.add(btnReservar);
        buttonPanel.add(btnEliminarReserva);
        mainContentPanel.add(buttonPanel);
        mainContentPanel.add(createReturnButton());
        
        revalidate();
        repaint();
    }
    
    private void mostrarPanelAuditorio() {
        mainContentPanel.removeAll();
        mainContentPanel.setLayout(new BoxLayout(mainContentPanel, BoxLayout.Y_AXIS));
        mainContentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titulo = new JLabel("Auditorio Fitness", SwingConstants.CENTER);
        titulo.setFont(nunitoBold.deriveFont(24f));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainContentPanel.add(titulo);

        JTextArea areaAuditorio = new JTextArea(10, 50);
        areaAuditorio.setEditable(false);
        areaAuditorio.setFont(new Font("Monospaced", Font.PLAIN, 14));
        JScrollPane scroll = new JScrollPane(areaAuditorio);
        mainContentPanel.add(scroll);
        
        Runnable actualizarAuditorio = () -> areaAuditorio.setText(gymnova.mostrarHorarioAuditorio());
        actualizarAuditorio.run();

        JTextField txtIdSocio = new JTextField(10);
        JTextField txtIdEvento = new JTextField(10);

        mainContentPanel.add(new JLabel("ID Socio:"));
        mainContentPanel.add(txtIdSocio);
        mainContentPanel.add(new JLabel("ID Evento:"));
        mainContentPanel.add(txtIdEvento);

        JPanel buttonPanel = new JPanel();
        JButton btnInscribir = new JButton("Inscribir");
        btnInscribir.addActionListener(e -> {
            try {
                int idSocio = Integer.parseInt(txtIdSocio.getText());
                int idEvento = Integer.parseInt(txtIdEvento.getText());
                String resultado = gymnova.inscribirSocioAuditorio(idSocio, idEvento);
                JOptionPane.showMessageDialog(this, resultado);
                actualizarAuditorio.run();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "ID de Socio o Evento inválido.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        
        JButton btnEliminar = new JButton("Eliminar Inscripción");
        btnEliminar.addActionListener(e -> {
            try {
                int idSocio = Integer.parseInt(txtIdSocio.getText());
                int idEvento = Integer.parseInt(txtIdEvento.getText());
                String resultado = gymnova.eliminarSocioInscripcion(idSocio, idEvento);
                JOptionPane.showMessageDialog(this, resultado);
                actualizarAuditorio.run();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "ID de Socio o Evento inválido.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        buttonPanel.add(btnInscribir);
        buttonPanel.add(btnEliminar);
        mainContentPanel.add(buttonPanel);
        mainContentPanel.add(createReturnButton());
        
        revalidate();
        repaint();
    }
    
    private void mostrarPanelEspaciosRecreativos() {
        mainContentPanel.removeAll();
        mainContentPanel.setLayout(new BoxLayout(mainContentPanel, BoxLayout.Y_AXIS));
        mainContentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel titulo = new JLabel("Espacios Recreativos", SwingConstants.CENTER);
        titulo.setFont(nunitoBold.deriveFont(24f));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainContentPanel.add(titulo);

        JTextArea areaEspacios = new JTextArea(10, 50);
        areaEspacios.setEditable(false);
        areaEspacios.setFont(new Font("Monospaced", Font.PLAIN, 14));
        JScrollPane scroll = new JScrollPane(areaEspacios);
        mainContentPanel.add(scroll);
        
        Runnable actualizarEspacios = () -> areaEspacios.setText(gymnova.visualizarEspaciosRecreativos());
        actualizarEspacios.run();

        JTextField txtIdSocio = new JTextField(10);
        JTextField txtIdEspacio = new JTextField(10);

        mainContentPanel.add(new JLabel("ID Socio:"));
        mainContentPanel.add(txtIdSocio);
        mainContentPanel.add(new JLabel("ID Espacio:"));
        mainContentPanel.add(txtIdEspacio);

        JPanel buttonPanel = new JPanel();
        JButton btnInscribir = new JButton("Inscribir");
        btnInscribir.addActionListener(e -> {
            try {
                int idSocio = Integer.parseInt(txtIdSocio.getText());
                int idEspacio = Integer.parseInt(txtIdEspacio.getText());
                String resultado = gymnova.registrarSocioEnEspacio(idSocio, idEspacio);
                JOptionPane.showMessageDialog(this, resultado);
                actualizarEspacios.run();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "ID de Socio o Espacio inválido.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        JButton btnEliminar = new JButton("Eliminar Inscripción");
        btnEliminar.addActionListener(e -> {
            try {
                int idSocio = Integer.parseInt(txtIdSocio.getText());
                int idEspacio = Integer.parseInt(txtIdEspacio.getText());
                String resultado = gymnova.eliminarSocioEspacio(idSocio, idEspacio);
                JOptionPane.showMessageDialog(this, resultado);
                actualizarEspacios.run();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "ID de Socio o Espacio inválido.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        buttonPanel.add(btnInscribir);
        buttonPanel.add(btnEliminar);
        mainContentPanel.add(buttonPanel);
        mainContentPanel.add(createReturnButton());
        
        revalidate();
        repaint();
    }
    
    private void mostrarPanelParqueo() {
        mainContentPanel.removeAll();
        mainContentPanel.setLayout(new BoxLayout(mainContentPanel, BoxLayout.Y_AXIS));
        mainContentPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        JLabel titulo = new JLabel("Gestión de Parqueo", SwingConstants.CENTER);
        titulo.setFont(nunitoBold.deriveFont(24f));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
        titulo.setForeground(colorBoton);
        mainContentPanel.add(titulo);
        mainContentPanel.add(Box.createVerticalStrut(20));

        String[] niveles = {"G1", "G2", "G3"};
        JComboBox<String> comboNiveles = new JComboBox<>(niveles);
        comboNiveles.setFont(nunitoBold);
        comboNiveles.setMaximumSize(new Dimension(200, 30));
        mainContentPanel.add(new JLabel("Seleccione Nivel:"));
        mainContentPanel.add(comboNiveles);
        mainContentPanel.add(Box.createVerticalStrut(15));
        
        estadoParqueo = new JTextArea(8, 40);
        estadoParqueo.setEditable(false);
        estadoParqueo.setFont(new Font("Monospaced", Font.PLAIN, 14));
        JScrollPane scroll = new JScrollPane(estadoParqueo);
        mainContentPanel.add(scroll);
        mainContentPanel.add(Box.createVerticalStrut(15));
        
        Runnable actualizarEstado = () -> {
            String nivelSeleccionado = (String) comboNiveles.getSelectedItem();
            if (nivelSeleccionado != null) {
                estadoParqueo.setText(gymnova.getEstadoNivelParqueo(nivelSeleccionado));
            }
        };

        comboNiveles.addActionListener(e -> actualizarEstado.run());
        actualizarEstado.run();
        
        JTextField txtIdSocio = new JTextField(10);
        JPanel inputPanel = new JPanel();
        inputPanel.setBackground(fondo);
        inputPanel.add(new JLabel("ID de Socio:"));
        inputPanel.add(txtIdSocio);
        mainContentPanel.add(inputPanel);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(fondo);
        
        JButton btnAsignar = new JButton("Asignar espacio");
        btnAsignar.setFont(nunitoBold.deriveFont(16f));
        btnAsignar.setBackground(colorBoton);
        btnAsignar.setForeground(colorLetraBoton);
        btnAsignar.addActionListener(e -> {
            String nivelSeleccionado = (String) comboNiveles.getSelectedItem();
            String idSocioStr = txtIdSocio.getText();
            if (idSocioStr == null || idSocioStr.trim().isEmpty()) {
                JOptionPane.showMessageDialog(GUI.this, "Ingrese el ID del socio.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            try {
                int idSocio = Integer.parseInt(idSocioStr);
                boolean asignado = gymnova.asignarEspacioParqueo(nivelSeleccionado, idSocio);
                if (asignado) {
                    JOptionPane.showMessageDialog(GUI.this, "Espacio asignado correctamente.");
                } else {
                    JOptionPane.showMessageDialog(GUI.this, "No se pudo asignar el espacio.", "Error", JOptionPane.ERROR_MESSAGE);
                }
                actualizarEstado.run();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(GUI.this, "ID de socio inválido.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        
        JButton btnLiberar = new JButton("Liberar espacio");
        btnLiberar.setFont(nunitoBold.deriveFont(16f));
        btnLiberar.setBackground(colorBoton);
        btnLiberar.setForeground(colorLetraBoton);
        btnLiberar.addActionListener(e -> {
            String nivelSeleccionado = (String) comboNiveles.getSelectedItem();
            String idSocioStr = txtIdSocio.getText();
            if (idSocioStr == null || idSocioStr.trim().isEmpty()) {
                JOptionPane.showMessageDialog(GUI.this, "Ingrese el ID del socio.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            try {
                int idSocio = Integer.parseInt(idSocioStr);
                boolean liberado = gymnova.liberarEspacioParqueo(nivelSeleccionado, idSocio);
                if (liberado) {
                    JOptionPane.showMessageDialog(GUI.this, "Espacio liberado correctamente.");
                } else {
                    JOptionPane.showMessageDialog(GUI.this, "No se pudo liberar el espacio.", "Error", JOptionPane.ERROR_MESSAGE);
                }
                actualizarEstado.run();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(GUI.this, "ID de socio inválido.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        
        buttonPanel.add(btnAsignar);
        buttonPanel.add(btnLiberar);
        
        mainContentPanel.add(buttonPanel);
        mainContentPanel.add(createReturnButton());

        revalidate();
        repaint();
    }
}
