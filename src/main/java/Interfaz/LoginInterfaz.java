package Interfaz;

import Modelo.Administrador;
import Modelo.Usuario;
import Modelo.Vendedor;
import Servicios.ControlLogin;
import Servicios.GestorProveedores;
import Servicios.Inventario;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class LoginInterfaz extends JFrame {

    private JTextField campoUsuario;
    private JPasswordField campoContrasena;
    private JButton botonLogin;
    private ControlLogin controlLogin;
    private Inventario inventario;
    private GestorProveedores proveedores;

    public LoginInterfaz(ArrayList<Usuario> empleados, Inventario inventario, GestorProveedores proveedores) {
        this.controlLogin = new ControlLogin(empleados);
        this.inventario = inventario;
        this.proveedores = proveedores;

        setTitle("Acceso al Sistema");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setPreferredSize(new Dimension(400, 300));
        panel.setBackground(Color.WHITE);
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
                BorderFactory.createEmptyBorder(30, 40, 30, 40)
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(12, 0, 8, 0);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;

        // Título
        JLabel titulo = new JLabel("Iniciar Sesión", SwingConstants.CENTER);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(titulo, gbc);

        // Nombre
        gbc.gridy++;
        JLabel lblUsuario = new JLabel("Nombre de Usuario:");
        lblUsuario.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        panel.add(lblUsuario, gbc);

        gbc.gridy++;
        campoUsuario = new JTextField();
        campoUsuario.setPreferredSize(new Dimension(300, 30));
        panel.add(campoUsuario, gbc);

        // Contraseña
        gbc.gridy++;
        JLabel lblContrasena = new JLabel("Contraseña:");
        lblContrasena.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        panel.add(lblContrasena, gbc);

        gbc.gridy++;
        campoContrasena = new JPasswordField();
        campoContrasena.setPreferredSize(new Dimension(300, 30));
        panel.add(campoContrasena, gbc);

        // Botón
        gbc.gridy++;
        botonLogin = new JButton("Iniciar Sesión");
        botonLogin.setBackground(new Color(45, 123, 246));
        botonLogin.setForeground(Color.WHITE);
        botonLogin.setFont(new Font("Segoe UI", Font.BOLD, 14));
        botonLogin.setFocusPainted(false);
        botonLogin.setCursor(new Cursor(Cursor.HAND_CURSOR));
        botonLogin.setPreferredSize(new Dimension(300, 35));
        panel.add(botonLogin, gbc);

        // Evento de login
        botonLogin.addActionListener(e -> {
            String nombre = campoUsuario.getText().trim();
            String contrasena = new String(campoContrasena.getPassword()).trim();

            Usuario usuario = controlLogin.iniciarSesion(nombre, contrasena);
            if (usuario != null) {
                JOptionPane.showMessageDialog(this, "Bienvenido " + usuario.getNombre());
                dispose();
                if (usuario instanceof Administrador) {
                    new MenuAdministrador((Administrador) usuario, inventario, proveedores);
                } else if (usuario instanceof Vendedor) {
                    new MenuVendedor((Vendedor) usuario, inventario, proveedores);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrectos", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        add(panel);
        setVisible(true);
    }
}
