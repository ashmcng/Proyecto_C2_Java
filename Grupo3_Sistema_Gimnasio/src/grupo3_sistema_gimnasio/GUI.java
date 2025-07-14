/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package grupo3_sistema_gimnasio;

import javax.swing.JFrame;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;
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
    private JPanel panelMenuOpciones; // panel con los botones del menú
    
     // Botón custom con bordes redondeados
    class RoundedButton extends JButton {
        private int radius;

        public RoundedButton(String text, int radius) {
            super(text);
            this.radius = radius;
            setContentAreaFilled(false);
            setFocusPainted(false);
            setForeground(colorLetraBoton);
            setBackground(colorBoton);
            setFont(nunitoBold);
            setCursor(new Cursor(Cursor.HAND_CURSOR));
            setBorder(BorderFactory.createEmptyBorder(5, 15, 5, 15));
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(getBackground());
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), radius, radius);
            super.paintComponent(g2);
            g2.dispose();
        }

        @Override
        protected void paintBorder(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(Color.WHITE);
            g2.drawRoundRect(0, 0, getWidth()-1, getHeight()-1, radius, radius);
            g2.dispose();
        }

        @Override
        public void setContentAreaFilled(boolean b) {
            // no relleno default para que se use paintComponent
            super.setContentAreaFilled(false);
        }
    }

    public GUI() {
        super("Nova Gym");
        
        //logo para la pestaña
        ImageIcon icon = new ImageIcon("iconos/Logo.png");
        setIconImage(icon.getImage());

        // Try-catch para cargar la fuente Nunito-Bold
        try {
            nunitoBold = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/Nunito-Bold.ttf")).deriveFont(18f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(nunitoBold);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
            nunitoBold = new Font("SansSerif", Font.BOLD, 18); // fallback
        }

        setTitle(" NOVA GYM ");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // CIERRA CON EXIT 
        getContentPane().setBackground(fondo); // COLOR DEFINIDO ARRIBA
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        setVisible(true);

        // main titulo 
        JLabel titulo = new JLabel("¡Bienvenido a la app de Nova Gym!", SwingConstants.CENTER);
        titulo.setFont(nunitoBold.deriveFont(24f)); // tamaño más grande para título
        titulo.setForeground(colorBoton);
        titulo.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
        add(titulo, BorderLayout.NORTH);

        // Panel central para centrar el botón
        JPanel panelCentral = new JPanel();
        panelCentral.setBackground(fondo);
        panelCentral.setLayout(new GridBagLayout()); // para centrar contenido

        //imagen main Logo arriba:
        ImageIcon logoIcon = new ImageIcon("iconos/Logo.png");
        JLabel labelLogo = new JLabel(logoIcon);
        labelLogo.setHorizontalAlignment(SwingConstants.CENTER);

        add(labelLogo, BorderLayout.NORTH);
        
        /// BOTONES DREDONDOS 

        RoundedButton btnMenu = new RoundedButton("Menu", 25); // radio 25 para bordes super redonditos
        btnMenu.setFont(nunitoBold.deriveFont(18f));
        btnMenu.setPreferredSize(new Dimension(140, 50));
        panelCentral.add(btnMenu);
        add(panelCentral, BorderLayout.CENTER);

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
            RoundedButton btn = new RoundedButton(texto, 25);
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
    }

    private void manejarOpcion(char opcion) {
        switch (opcion) {
            case '1':
                JOptionPane.showMessageDialog(this, "Mostrar clases disponibles (pendiente implementación)");
                break;
            case '2':
                JOptionPane.showMessageDialog(this, "Editar clases (pendiente implementación)");
                break;
            case '3':
                JOptionPane.showMessageDialog(this, "Crear clase (pendiente implementación)");
                break;
            case '4':
                JOptionPane.showMessageDialog(this, "Registrar socio (pendiente implementación)");
                break;
            case '5':
                JOptionPane.showMessageDialog(this, "Sala de pesas (pendiente implementación)");
                break;
            case '6':
                JOptionPane.showMessageDialog(this, "Parqueo (pendiente implementación)");
                break;
            case '7':
                int res = JOptionPane.showConfirmDialog(this, "¿Quieres salir, queen?");
                if (res == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
                break;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GUI());
    }
}