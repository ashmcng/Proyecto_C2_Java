package grupo3_sistema_gimnasio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
// No need for LocalTime or DateTimeParseException imports here, as Metodos handles them internally with JOptionPane.

public class GUI extends JFrame {

    private Color fondo = Color.decode("#a7c4ce");
    private Color colorBoton = Color.decode("#416975");
    private Color colorLetraBoton = Color.decode("#FFFFFF");
    private Font nunitoBold;
    private JPanel mainContentPanel;
    // JTextArea estadoParqueo; // This will not be used as Metodos handles JOptionPane directly
    private Metodos gymnova = new Metodos(); // This refers to the original Metodos class

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
        mainContentPanel.setLayout(new GridBagLayout()); // Keep original layout for consistency
        mainContentPanel.setBackground(fondo);
        add(mainContentPanel, BorderLayout.CENTER);

        mostrarMenuPrincipal();

        setVisible(true);

        // Initialize data using Metodos methods
        gymnova.generarDataInicialActividaddes();
        gymnova.dividirClases();
        gymnova.llenarCabinas();
        gymnova.inicializarParqueo();
        gymnova.incializarEspaciosRecreativos();
    }

    private void mostrarMenuPrincipal() {
        mainContentPanel.removeAll();
        mainContentPanel.setLayout(new GridLayout(7, 2, 10, 10)); // Use GridLayout for menu buttons
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
            // The action listener will directly call the Metodos method
            btn.addActionListener(e -> manejarOpcion(texto.substring(0, texto.indexOf('.')).trim()));
            mainContentPanel.add(btn);
        }

        revalidate();
        repaint();
    }

    private void regresarMenuPrincipal() {
        // This method is still useful to clear any specific panel and show the main menu again.
        mainContentPanel.removeAll();
        mostrarMenuPrincipal();
        revalidate();
        repaint();
    }

    private JButton createReturnButton() {
        // This button is still useful for navigation, even if the Metodos methods block.
        JButton btnVolver = new JButton("Volver al menú");
        btnVolver.setFont(nunitoBold.deriveFont(14f));
        btnVolver.setBackground(Color.LIGHT_GRAY);
        btnVolver.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnVolver.addActionListener(e -> regresarMenuPrincipal());
        return btnVolver;
    }

    private void manejarOpcion(String opcion) {
        // Since Metodos uses JOptionPane directly, we just call the methods.
        // The GUI will be blocked by the JOptionPane dialogs from Metodos.
        switch (opcion) {
            case "1":
                gymnova.mostrarclases(); // This will show JOptionPanes
                break;
            case "2":
                gymnova.editarClases(); // This will show JOptionPanes
                break;
            case "3":
                gymnova.crearClases(); // This will show JOptionPanes
                break;
            case "4":
                gymnova.registrarSocioClase(); // This will show JOptionPanes
                break;
            case "5":
                gymnova.eliminarClase(); // This will show JOptionPanes
                break;
            case "6":
                gymnova.eliminarSocioDeClase(); // This will show JOptionPanes
                break;
            case "7":
                gymnova.verRegistradosEnClase(); // This will show JOptionPanes
                break;
            case "8":
                gymnova.salaPesas(); // This will show JOptionPanes
                break;
            case "9":
                gymnova.reservarCabina(); // This will show JOptionPanes (and potentially others like mostrar/eliminar from within Metodos)
                break;
            case "10":
                gymnova.inscribirSocioAuditorio(); // This will show JOptionPanes (and potentially others from within Metodos)
                break;
            case "11":
                gymnova.registrarSocioEnEspacio(); // This will show JOptionPanes (and potentially others from within Metodos)
                break;
            case "12":
                gymnova.parqueo(); // This will show JOptionPanes
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
        // After any operation that involves Metodos's JOptionPanes,
        // it's good practice to ensure the main menu is visible again.
        // This is because Metodos methods block the thread until their JOptionPanes are closed.
        regresarMenuPrincipal();
    }

    //---------------------------------------------------------
    // PANELES DE MENU - These methods are now simplified to just trigger Metodos's JOptionPane-based logic.
    //                   The custom JTextFields and JTextAreas in these panels are removed
    //                   because Metodos will use its own JOptionPane for input/output.
    //---------------------------------------------------------

    private void mostrarPanelClasesDisponibles() {
        // This panel is now simplified to just call the Metodos method
        // which will display its own JOptionPane.
        gymnova.mostrarclases();
        regresarMenuPrincipal(); // Return to main menu after JOptionPane is closed
    }

    private void mostrarPanelEditarClase() {
        // This panel is now simplified to just call the Metodos method
        // which will display its own JOptionPane.
        gymnova.editarClases();
        regresarMenuPrincipal(); // Return to main menu after JOptionPane is closed
    }

    private void mostrarPanelCrearClase() {
        // This panel is now simplified to just call the Metodos method
        // which will display its own JOptionPane.
        gymnova.crearClases();
        regresarMenuPrincipal(); // Return to main menu after JOptionPane is closed
    }

    private void mostrarPanelRegistrarSocioEnClase() {
        // This panel is now simplified to just call the Metodos method
        // which will display its own JOptionPane.
        gymnova.registrarSocioClase();
        regresarMenuPrincipal(); // Return to main menu after JOptionPane is closed
    }

    private void mostrarPanelEliminarClase() {
        // This panel is now simplified to just call the Metodos method
        // which will display its own JOptionPane.
        gymnova.eliminarClase();
        regresarMenuPrincipal(); // Return to main menu after JOptionPane is closed
    }

    private void mostrarPanelEliminarSocioDeClase() {
        // This panel is now simplified to just call the Metodos method
        // which will display its own JOptionPane.
        gymnova.eliminarSocioDeClase();
        regresarMenuPrincipal(); // Return to main menu after JOptionPane is closed
    }

    private void mostrarPanelVerInscritos() {
        // This panel is now simplified to just call the Metodos method
        // which will display its own JOptionPane.
        gymnova.verRegistradosEnClase();
        regresarMenuPrincipal(); // Return to main menu after JOptionPane is closed
    }

    private void mostrarPanelSalaPesas() {
        // This panel is now simplified to just call the Metodos method
        // which will display its own JOptionPane.
        gymnova.salaPesas();
        regresarMenuPrincipal(); // Return to main menu after JOptionPane is closed
    }

    private void mostrarPanelCabinas() {
        // This panel is now simplified to just call the Metodos method
        // which will display its own JOptionPane.
        gymnova.reservarCabina(); // This method also handles viewing and deleting internally via JOptionPane
        regresarMenuPrincipal(); // Return to main menu after JOptionPane is closed
    }

    private void mostrarPanelAuditorio() {
        // This panel is now simplified to just call the Metodos method
        // which will display its own JOptionPane.
        gymnova.inscribirSocioAuditorio(); // This method also handles viewing and deleting internally via JOptionPane
        regresarMenuPrincipal(); // Return to main menu after JOptionPane is closed
    }

    private void mostrarPanelEspaciosRecreativos() {
        // This panel is now simplified to just call the Metodos method
        // which will display its own JOptionPane.
        gymnova.registrarSocioEnEspacio(); // This method also handles viewing and deleting internally via JOptionPane
        regresarMenuPrincipal(); // Return to main menu after JOptionPane is closed
    }

    private void mostrarPanelParqueo() {
        // This panel is now simplified to just call the Metodos method
        // which will display its own JOptionPane.
        gymnova.parqueo();
        regresarMenuPrincipal(); // Return to main menu after JOptionPane is closed
    }
}
