package Interfaz.Inventarios;

import Modelo.Producto;
import Servicios.Inventario;

import javax.swing.*;
import java.awt.*;

public class EliminarProductoForm extends JFrame {
    public EliminarProductoForm(JFrame parent, Inventario inventario) {
        setTitle("Eliminar Producto");
        setSize(400, 250);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.WHITE);

        JLabel titulo = new JLabel("Eliminar Producto", SwingConstants.CENTER);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 20));
        titulo.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10));
        add(titulo, BorderLayout.NORTH);

        JPanel panelCampos = new JPanel(new GridLayout(2, 2, 10, 10));
        panelCampos.setBorder(BorderFactory.createEmptyBorder(20, 40, 10, 40));
        panelCampos.setBackground(Color.WHITE);

        JLabel lblId = new JLabel("ID del producto:");
        JTextField txtId = new JTextField();

        panelCampos.add(lblId);
        panelCampos.add(txtId);
        add(panelCampos, BorderLayout.CENTER);

        // Botones
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

        // Acción: Eliminar producto
        btnEliminar.addActionListener(e -> {
            try {
                int id = Integer.parseInt(txtId.getText().trim());
                Producto producto = inventario.buscarProductoPorID(id);
                if (producto != null) {
                    int confirm = JOptionPane.showConfirmDialog(this,
                            "¿Estás seguro de eliminar el producto:\n" +
                                    producto.getNombre() + " (ID: " + id + ")?",
                            "Confirmar eliminación",
                            JOptionPane.YES_NO_OPTION);
                    if (confirm == JOptionPane.YES_OPTION) {
                        inventario.eliminarProducto(id);
                        JOptionPane.showMessageDialog(this, "Producto eliminado con éxito.");
                        dispose();
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Producto no encontrado.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "ID inválido.");
            }
        });

        btnCancelar.addActionListener(e -> dispose());

        setVisible(true);
    }
}
