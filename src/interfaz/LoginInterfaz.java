package interfaz;

import Usuarios.Usuario;
import servicio.ControlLogin;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class LoginInterfaz extends JFrame {

    private JTextField txtUsuario;
    private JPasswordField txtContrasena;
    private JButton btnIngresar;
    private Usuario usuarioLogueado;

    public LoginInterfaz(List<Usuario> usuariosRegistrados) {
        setTitle("Inicio de Sesión");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel lblTitulo = new JLabel("Sistema de Inventario");
        lblTitulo.setBounds(110, 10, 250, 25);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 16));
        panel.add(lblTitulo);

        JLabel lblUsuario = new JLabel("Usuario:");
        lblUsuario.setBounds(50, 60, 100, 25);
        panel.add(lblUsuario);

        txtUsuario = new JTextField();
        txtUsuario.setBounds(150, 60, 180, 25);
        panel.add(txtUsuario);

        JLabel lblContrasena = new JLabel("Contraseña:");
        lblContrasena.setBounds(50, 100, 100, 25);
        panel.add(lblContrasena);

        txtContrasena = new JPasswordField();
        txtContrasena.setBounds(150, 100, 180, 25);
        panel.add(txtContrasena);

        btnIngresar = new JButton("Ingresar");
        btnIngresar.setBounds(150, 150, 100, 30);
        panel.add(btnIngresar);

        btnIngresar.addActionListener(e -> {
            String nombre = txtUsuario.getText();
            String contrasena = new String(txtContrasena.getPassword());

            for (Usuario u : usuariosRegistrados) {
                if (ControlLogin.iniciarSesion(u, nombre, contrasena)) {
                    usuarioLogueado = u;
                    JOptionPane.showMessageDialog(this, "Bienvenido " + u.getNombre());
                    dispose(); // cerrar esta ventana
                    return;
                }
            }

            JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrectos.");
        });

        add(panel);
        setVisible(true);
    }

    public Usuario getUsuarioLogueado() {
        return usuarioLogueado;
    }
}
