package Interfaz.Invetarios;

import Modelo.Producto;
import Servicios.Inventario;

import javax.swing.*;
import java.awt.*;

public class EliminarProductoForm extends JDialog {

    public EliminarProductoForm(JFrame parent, Inventario inventario) {
        super(parent, "Eliminar Producto", true);
        setSize(400, 180);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());

        JLabel titulo = new JLabel("Eliminar Producto", SwingConstants.CENTER);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 22));
        titulo.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10));
        add(titulo, BorderLayout.NORTH);

        JPanel panelCentro = new JPanel(new FlowLayout());
        panelCentro.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel lblId = new JLabel("Ingrese ID del producto:");
        JTextField campoId = new JTextField(10);
        panelCentro.add(lblId);
        panelCentro.add(campoId);

        add(panelCentro, BorderLayout.CENTER);

        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.setBackground(new Color(220, 53, 69));
        btnEliminar.setForeground(Color.WHITE);
        btnEliminar.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnEliminar.setFocusPainted(false);
        btnEliminar.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btnEliminar.addActionListener(e -> {
            try {
                int id = Integer.parseInt(campoId.getText().trim());
                Producto p = inventario.buscarProducto(id);

                if (p == null) {
                    JOptionPane.showMessageDialog(this, "Producto no encontrado.");
                    return;
                }

                int confirm = JOptionPane.showConfirmDialog(this,
                        "¿Está seguro de eliminar el producto \"" + p.getNombre() + "\"?",
                        "Confirmar eliminación", JOptionPane.YES_NO_OPTION);

                if (confirm == JOptionPane.YES_OPTION) {
                    inventario.eliminarProducto(id);
                    JOptionPane.showMessageDialog(this, "Producto eliminado.");
                    dispose();
                }

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        JPanel panelBoton = new JPanel();
        panelBoton.add(btnEliminar);
        add(panelBoton, BorderLayout.SOUTH);

        setVisible(true);
    }
}
