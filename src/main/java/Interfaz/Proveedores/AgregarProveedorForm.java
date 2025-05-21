package Interfaz.Proveedores;

import Modelo.Proveedor;
import Servicios.ProveedorServicos;

import javax.swing.*;
import java.awt.*;

public class AgregarProveedorForm extends JDialog {

    public AgregarProveedorForm(VerProveedoresTabla parent, ProveedorServicos proveedorServicos) {
        super(parent, "Agregar Proveedor", true);
        setSize(450, 400);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());

        //título
        JLabel titulo = new JLabel("Agregar Nuevo Proveedor", SwingConstants.CENTER);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 22));
        titulo.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10));
        add(titulo, BorderLayout.NORTH);

        //Formulario
        JPanel panelForm = new JPanel(new GridLayout(5, 2, 10, 15));
        panelForm.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        JTextField campoNombre = new JTextField();
        JTextField campoContacto = new JTextField();
        JTextField campoTelefono = new JTextField();
        JTextField campoEmail = new JTextField();

        panelForm.add(new JLabel("Nombre:"));
        panelForm.add(campoNombre);
        panelForm.add(new JLabel("Contacto:"));
        panelForm.add(campoContacto);
        panelForm.add(new JLabel("Teléfono:"));
        panelForm.add(campoTelefono);
        panelForm.add(new JLabel("Email:"));
        panelForm.add(campoEmail);

        add(panelForm, BorderLayout.CENTER);

        //Botón Guardar
        JButton btnGuardar = new JButton("Guardar Proveedor");
        btnGuardar.setBackground(new Color(33, 150, 243));
        btnGuardar.setForeground(Color.WHITE);
        btnGuardar.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnGuardar.setFocusPainted(false);
        btnGuardar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnGuardar.setPreferredSize(new Dimension(180, 40));

        btnGuardar.addActionListener(e -> {
            try {
                String nombre = campoNombre.getText().trim();
                String contacto = campoContacto.getText().trim();
                String telefono = campoTelefono.getText().trim();
                String email = campoEmail.getText().trim();

                // Validación simple
                if (nombre.isEmpty() || contacto.isEmpty()) {
                    throw new IllegalArgumentException("Nombre y contacto son obligatorios.");
                }

                int nuevoId = proveedorServicos.getListaProveedores().size() + 1;
                Proveedor nuevo = new Proveedor(nuevoId, nombre, contacto, telefono, email);
                proveedorServicos.agregarProveedor(nuevo);

                JOptionPane.showMessageDialog(this, "Proveedor agregado correctamente.");
                dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        JPanel panelBoton = new JPanel();
        panelBoton.add(btnGuardar);
        add(panelBoton, BorderLayout.SOUTH);

        setVisible(true);
    }
}
