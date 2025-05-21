package Interfaz.Proveedores;

import Modelo.Proveedor;
import Servicios.ProveedorServicos;

import javax.swing.*;
import java.awt.*;

public class EditarProveedorForm extends JDialog {

    public EditarProveedorForm(VerProveedoresTabla parent, ProveedorServicos proveedorServicos) {
        super(parent, "Editar Proveedor", true);
        setSize(500, 420);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());

        // Título
        JLabel titulo = new JLabel("Editar Proveedor", SwingConstants.CENTER);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titulo.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10));
        add(titulo, BorderLayout.NORTH);

        // Panel formulario
        JPanel panelForm = new JPanel(new GridLayout(6, 2, 10, 12));
        panelForm.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        JTextField campoId = new JTextField();
        JTextField campoNombre = new JTextField();
        JTextField campoContacto = new JTextField();
        JTextField campoTelefono = new JTextField();
        JTextField campoEmail = new JTextField();

        JButton btnBuscar = new JButton("Buscar");

        // Buscar proveedor por ID
        btnBuscar.addActionListener(e -> {
            try {
                int id = Integer.parseInt(campoId.getText().trim());
                Proveedor proveedor = proveedorServicos.buscarPorId(id);

                if (proveedor == null) {
                    throw new Exception("Proveedor no encontrado con ID: " + id);
                }

                campoNombre.setText(proveedor.getNombre());
                campoContacto.setText(proveedor.getContacto());
                campoTelefono.setText(proveedor.getTelefono());
                campoEmail.setText(proveedor.getEmail());
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this,  ex.getMessage(), "Error", JOptionPane.WARNING_MESSAGE);
            }
        });

        panelForm.add(new JLabel("ID del Proveedor:"));
        panelForm.add(campoId);
        panelForm.add(new JLabel(""));
        panelForm.add(btnBuscar);

        panelForm.add(new JLabel("Nombre:"));
        panelForm.add(campoNombre);
        panelForm.add(new JLabel("Contacto:"));
        panelForm.add(campoContacto);
        panelForm.add(new JLabel("Teléfono:"));
        panelForm.add(campoTelefono);
        panelForm.add(new JLabel("Email:"));
        panelForm.add(campoEmail);

        add(panelForm, BorderLayout.CENTER);

        // Botón guardar cambios
        JButton btnGuardar = new JButton("Guardar Cambios");
        btnGuardar.setBackground(new Color(255, 152, 0));
        btnGuardar.setForeground(Color.WHITE);
        btnGuardar.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnGuardar.setFocusPainted(false);
        btnGuardar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnGuardar.setPreferredSize(new Dimension(180, 40));

        btnGuardar.addActionListener(e -> {
            try {
                int id = Integer.parseInt(campoId.getText().trim());
                Proveedor proveedor = proveedorServicos.buscarPorId(id);

                if (proveedor == null) throw new Exception("Proveedor no encontrado.");

                proveedor.setNombre(campoNombre.getText().trim());
                proveedor.setContacto(campoContacto.getText().trim());
                proveedor.setTelefono(campoTelefono.getText().trim());
                proveedor.setEmail(campoEmail.getText().trim());

                JOptionPane.showMessageDialog(this, "Proveedor actualizado correctamente.");
                dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this,  ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        JPanel panelBoton = new JPanel();
        panelBoton.add(btnGuardar);
        add(panelBoton, BorderLayout.SOUTH);

        setVisible(true);
    }
}
