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
                break;
            case '2':
                labelContenido.setText(" Editar clases (pendiente implementación)");
                break;
            case '3':
                labelContenido.setText("Crear clase (pendiente implementación)");
                break;
            case '4':
                labelContenido.setText("Registrar socio (pendiente implementación)");
                break;
            case '5':
                labelContenido.setText(" Sala de pesas (pendiente implementación)");
                break;
            case '6':
                labelContenido.setText("Parqueo");
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GUI());
    }
}