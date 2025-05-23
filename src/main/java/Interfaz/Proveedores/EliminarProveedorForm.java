package Interfaz.Proveedores;

import Modelo.Proveedor;
import Servicios.GestorProveedores;

import javax.swing.*;
import java.awt.*;

public class EliminarProveedorForm extends JFrame {

    public EliminarProveedorForm(JFrame parent, GestorProveedores gestor) {
        setTitle("Eliminar Proveedor");
        setSize(400, 250);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.WHITE);

        JLabel titulo = new JLabel("Eliminar Proveedor", SwingConstants.CENTER);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 20));
        titulo.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10));
        add(titulo, BorderLayout.NORTH);

        JPanel panelCampos = new JPanel(new GridLayout(2, 2, 10, 10));
        panelCampos.setBorder(BorderFactory.createEmptyBorder(20, 40, 10, 40));
        panelCampos.setBackground(Color.WHITE);

        JLabel lblId = new JLabel("ID del proveedor:");
        JTextField txtId = new JTextField();

        panelCampos.add(lblId);
        panelCampos.add(txtId);
        add(panelCampos, BorderLayout.CENTER);

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        panelBotones.setBackground(Color.WHITE);

        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.setBackground(new Color(244, 67, 54)); // Rojo
        btnEliminar.setForeground(Color.WHITE);
        btnEliminar.setFocusPainted(false);
        btnEliminar.setFont(new Font("Segoe UI", Font.BOLD, 14));

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.setBackground(new Color(189, 189, 189)); // Gris
        btnCancelar.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnCancelar.setFocusPainted(false);

        panelBotones.add(btnEliminar);
        panelBotones.add(btnCancelar);
        add(panelBotones, BorderLayout.SOUTH);

        // Acción: Eliminar proveedor
        btnEliminar.addActionListener(e -> {
            try {
                int id = Integer.parseInt(txtId.getText().trim());
                Proveedor proveedor = gestor.buscarProveedorPorId(id);
                if (proveedor != null) {
                    int confirm = JOptionPane.showConfirmDialog(this,
                            "¿Estás seguro de eliminar al proveedor:\n" +
                                    proveedor.getNombre() + " (ID: " + id + ")?",
                            "Confirmar eliminación",
                            JOptionPane.YES_NO_OPTION);
                    if (confirm == JOptionPane.YES_OPTION) {
                        gestor.eliminarProveedor(id);
                        JOptionPane.showMessageDialog(this, "Proveedor eliminado con éxito.");
                        dispose();
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Proveedor no encontrado.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "ID inválido.");
            }
        });

        btnCancelar.addActionListener(e -> dispose());

        setVisible(true);
    }
}
