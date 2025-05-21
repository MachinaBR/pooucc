package Interfaz;

import Modelo.Usuario;
import Servicios.ControlLogin;
import Servicios.Inventario;
import Servicios.ProveedorServicos;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class LoginInterfaz extends JFrame {

    private JTextField campoUsuario;
    private JPasswordField campoContrasena;
    private JButton botonLogin;
    private ControlLogin controlLogin;
    private Inventario inventario;
    private ProveedorServicos proveedorServicos;

    public LoginInterfaz(ArrayList<Usuario> empleados, Inventario inventario, ProveedorServicos proveedorServicos) {
        this.controlLogin = new ControlLogin(empleados);
        this.inventario = inventario;
        this.proveedorServicos = proveedorServicos;

        setTitle("Inicio de Sesión");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(245, 245, 245));

        // Título
        JLabel titulo = new JLabel("Inicio de Sesión", SwingConstants.CENTER);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 30));
        titulo.setBorder(BorderFactory.createEmptyBorder(30, 10, 20, 10));
        add(titulo, BorderLayout.NORTH);

        // Panel de login
        JPanel panelLogin = new JPanel(new GridBagLayout());
        panelLogin.setBackground(Color.WHITE);
        panelLogin.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 220, 220)),
                BorderFactory.createEmptyBorder(30, 40, 30, 40)
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0; gbc.gridy = 0;
        panelLogin.add(new JLabel("Usuario:"), gbc);

        gbc.gridy++;
        campoUsuario = new JTextField(20);
        panelLogin.add(campoUsuario, gbc);

        gbc.gridy++;
        panelLogin.add(new JLabel("Contraseña:"), gbc);

        gbc.gridy++;
        campoContrasena = new JPasswordField(20);
        panelLogin.add(campoContrasena, gbc);

        gbc.gridy++;
        botonLogin = new JButton("Iniciar Sesión");
        botonLogin.setBackground(new Color(33, 150, 243));
        botonLogin.setForeground(Color.WHITE);
        botonLogin.setFont(new Font("Segoe UI", Font.BOLD, 16));
        botonLogin.setFocusPainted(false);
        botonLogin.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panelLogin.add(botonLogin, gbc);

        // Centro visual
        JPanel panelContenedor = new JPanel(new GridBagLayout());
        panelContenedor.setBackground(new Color(245, 245, 245));
        panelContenedor.add(panelLogin);
        add(panelContenedor, BorderLayout.CENTER);

        // Acción de login
        botonLogin.addActionListener(e -> {
            String nombre = campoUsuario.getText().trim();
            String contrasena = new String(campoContrasena.getPassword()).trim();

            Usuario usuario = controlLogin.iniciarSesion(nombre, contrasena);

            if (usuario != null) {
                JOptionPane.showMessageDialog(this, "Bienvenido " + usuario.getNombre());
                dispose();
                new MenuPrincipal(inventario, proveedorServicos);
            } else {
                JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrectos", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        setVisible(true);
    }
}
