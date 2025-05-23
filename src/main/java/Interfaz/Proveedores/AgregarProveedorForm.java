package Interfaz.Proveedores;

import Modelo.Proveedor;
import Servicios.GestorProveedores;

import javax.swing.*;
import java.awt.*;

public class AgregarProveedorForm extends JFrame {

    private JTextField campoNombre, campoContacto, campoTelefono, campoEmail;
    private final GestorProveedores gestor;
    private final JFrame parent;

    public AgregarProveedorForm(JFrame parent, GestorProveedores gestor) {
        this.parent = parent;
        this.gestor = gestor;

        setTitle("Agregar Proveedor");
        setSize(400, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new GridBagLayout());
        getContentPane().setBackground(Color.WHITE);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 5, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;

        JLabel titulo = new JLabel("Agregar Nuevo Proveedor", SwingConstants.CENTER);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 20));
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(titulo, gbc);


        // Nombre
        gbc.gridy++;
        gbc.gridwidth = 1;
        add(new JLabel("Nombre:"), gbc);
        gbc.gridx = 1;
        campoNombre = new JTextField();
        campoNombre.setPreferredSize(new Dimension(400, 28)); // ⬅️ campo más largo
        add(campoNombre, gbc);

        // Contacto
        gbc.gridy++;
        gbc.gridx = 0;
        add(new JLabel("Contacto:"), gbc);
        gbc.gridx = 1;
        campoContacto = new JTextField();
        campoContacto.setPreferredSize(new Dimension(400, 28)); // ⬅️ campo más largo
        add(campoContacto, gbc);

        // Teléfono
        gbc.gridy++;
        gbc.gridx = 0;
        add(new JLabel("Teléfono:"), gbc);
        gbc.gridx = 1;
        campoTelefono = new JTextField();
        campoTelefono.setPreferredSize(new Dimension(400, 28)); // ⬅️ campo más largo
        add(campoTelefono, gbc);

        // Email
        gbc.gridy++;
        gbc.gridx = 0;
        add(new JLabel("Email:"), gbc);
        gbc.gridx = 1;
        campoEmail = new JTextField();
        campoEmail.setPreferredSize(new Dimension(400, 28)); // ⬅️ campo más largo
        add(campoEmail, gbc);


        // Botones
        gbc.gridy++;
        gbc.gridx = 0;
        JButton btnAgregar = new JButton("Agregar");
        btnAgregar.setBackground(new Color(33, 150, 243));
        btnAgregar.setForeground(Color.WHITE);
        btnAgregar.setFocusPainted(false);
        btnAgregar.setFont(new Font("Segoe UI", Font.BOLD, 14));
        add(btnAgregar, gbc);

        gbc.gridx = 1;
        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.setBackground(new Color(189, 189, 189));
        btnCancelar.setFont(new Font("Segoe UI", Font.BOLD, 14));
        add(btnCancelar, gbc);

        btnAgregar.addActionListener(e -> agregarProveedor());
        btnCancelar.addActionListener(e -> dispose());

        setVisible(true);
    }

    private void agregarProveedor() {
        String nombre = campoNombre.getText().trim();
        String contacto = campoContacto.getText().trim();
        String telefono = campoTelefono.getText().trim();
        String email = campoEmail.getText().trim();

        if (nombre.isEmpty() || contacto.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nombre y contacto son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Proveedor nuevo = new Proveedor(gestor.generarNuevoId(), nombre, contacto, telefono, email);
        gestor.agregarProveedor(nuevo);
        JOptionPane.showMessageDialog(this, "Proveedor agregado exitosamente.");
        dispose();
    }
}
