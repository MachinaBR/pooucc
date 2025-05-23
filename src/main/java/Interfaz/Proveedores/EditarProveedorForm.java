package Interfaz.Proveedores;

import Modelo.Proveedor;
import Servicios.GestorProveedores;

import javax.swing.*;
import java.awt.*;

public class EditarProveedorForm extends JFrame {

    private final JTextField txtIdBuscar, txtNombre, txtContacto, txtTelefono, txtEmail;
    private final JButton btnGuardar;

    public EditarProveedorForm(JFrame parent, GestorProveedores gestor) {
        setTitle("Editar Proveedor");
        setSize(400, 430);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.WHITE);

        JLabel titulo = new JLabel("Editar Proveedor", SwingConstants.CENTER);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 20));
        titulo.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10));
        add(titulo, BorderLayout.NORTH);

        JPanel panelCampos = new JPanel(new GridLayout(6, 2, 10, 10));
        panelCampos.setBorder(BorderFactory.createEmptyBorder(20, 40, 10, 40));
        panelCampos.setBackground(Color.WHITE);

        txtIdBuscar = new JTextField();
        JButton btnBuscar = new JButton("Buscar");

        txtNombre = new JTextField();
        txtContacto = new JTextField();
        txtTelefono = new JTextField();
        txtEmail = new JTextField();

        panelCampos.add(new JLabel("ID del proveedor:"));
        JPanel buscarPanel = new JPanel(new BorderLayout(5, 0));
        buscarPanel.setBackground(Color.WHITE);
        buscarPanel.add(txtIdBuscar, BorderLayout.CENTER);
        buscarPanel.add(btnBuscar, BorderLayout.EAST);
        panelCampos.add(buscarPanel);

        panelCampos.add(new JLabel("Nombre:"));
        panelCampos.add(txtNombre);
        panelCampos.add(new JLabel("Contacto:"));
        panelCampos.add(txtContacto);
        panelCampos.add(new JLabel("Teléfono:"));
        panelCampos.add(txtTelefono);
        panelCampos.add(new JLabel("Email:"));
        panelCampos.add(txtEmail);

        add(panelCampos, BorderLayout.CENTER);

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        panelBotones.setBackground(Color.WHITE);

        btnGuardar = new JButton("Guardar Cambios");
        btnGuardar.setBackground(new Color(33, 150, 243));
        btnGuardar.setForeground(Color.WHITE);
        btnGuardar.setFocusPainted(false);
        btnGuardar.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnGuardar.setEnabled(false);

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.setBackground(new Color(189, 189, 189));
        btnCancelar.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnCancelar.setFocusPainted(false);

        panelBotones.add(btnGuardar);
        panelBotones.add(btnCancelar);
        add(panelBotones, BorderLayout.SOUTH);

        // Acción: Buscar proveedor
        btnBuscar.addActionListener(e -> {
            try {
                int id = Integer.parseInt(txtIdBuscar.getText().trim());
                Proveedor proveedor = gestor.buscarProveedorPorId(id);
                if (proveedor != null) {
                    txtNombre.setText(proveedor.getNombre());
                    txtContacto.setText(proveedor.getContacto());
                    txtTelefono.setText(proveedor.getTelefono());
                    txtEmail.setText(proveedor.getEmail());
                    btnGuardar.setEnabled(true);
                } else {
                    JOptionPane.showMessageDialog(this, "Proveedor no encontrado.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "ID inválido.");
            }
        });

        // Acción: Guardar cambios
        btnGuardar.addActionListener(e -> {
            try {
                int id = Integer.parseInt(txtIdBuscar.getText().trim());
                String nombre = txtNombre.getText().trim();
                String contacto = txtContacto.getText().trim();
                String telefono = txtTelefono.getText().trim();
                String email = txtEmail.getText().trim();

                boolean actualizado = gestor.editarProveedor(id, nombre, contacto, telefono, email);
                if (actualizado) {
                    JOptionPane.showMessageDialog(this, "Proveedor actualizado correctamente.");
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "No se pudo actualizar el proveedor.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Verifica los campos ingresados.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnCancelar.addActionListener(e -> dispose());

        setVisible(true);
    }
}
