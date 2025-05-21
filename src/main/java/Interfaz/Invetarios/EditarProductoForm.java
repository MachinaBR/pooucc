package Interfaz.Invetarios;

import Modelo.Producto;
import Servicios.Inventario;

import javax.swing.*;
import java.awt.*;

public class EditarProductoForm extends JDialog {

    public EditarProductoForm(JFrame parent, Inventario inventario) {
        super(parent, "Editar Producto", true);
        setSize(420, 420);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());

        // Título
        JLabel titulo = new JLabel("Editar Producto", SwingConstants.CENTER);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 22));
        titulo.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10));
        add(titulo, BorderLayout.NORTH);

        // Campo para buscar ID
        String inputId = JOptionPane.showInputDialog(parent, "Ingrese el ID del producto a editar:");
        if (inputId == null) {
            dispose();
            return;
        }

        int id;
        try {
            id = Integer.parseInt(inputId.trim());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "ID inválido.");
            dispose();
            return;
        }

        Producto producto = inventario.buscarProducto(id);
        if (producto == null) {
            JOptionPane.showMessageDialog(this, "Producto no encontrado.");
            dispose();
            return;
        }

        // Formulario con datos actuales
        JPanel panelForm = new JPanel(new GridLayout(5, 2, 10, 15));
        panelForm.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        JTextField campoNombre = new JTextField(producto.getNombre());
        JTextField campoPrecio = new JTextField(String.valueOf(producto.getPrecio()));
        JTextField campoStock = new JTextField(String.valueOf(producto.getStock()));
        JTextField campoCategoria = new JTextField(producto.getCategoria());
        JTextField campoProveedor = new JTextField(producto.getProveedor());

        panelForm.add(new JLabel("Nombre:"));
        panelForm.add(campoNombre);
        panelForm.add(new JLabel("Precio:"));
        panelForm.add(campoPrecio);
        panelForm.add(new JLabel("Stock:"));
        panelForm.add(campoStock);
        panelForm.add(new JLabel("Categoría:"));
        panelForm.add(campoCategoria);
        panelForm.add(new JLabel("Proveedor:"));
        panelForm.add(campoProveedor);

        add(panelForm, BorderLayout.CENTER);

        JButton btnGuardar = new JButton("Guardar Cambios");
        btnGuardar.setBackground(new Color(255, 153, 0));
        btnGuardar.setForeground(Color.WHITE);
        btnGuardar.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnGuardar.setFocusPainted(false);
        btnGuardar.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btnGuardar.addActionListener(e -> {
            try {
                String nombre = campoNombre.getText().trim();
                double precio = Double.parseDouble(campoPrecio.getText().trim());
                int stock = Integer.parseInt(campoStock.getText().trim());
                String categoria = campoCategoria.getText().trim();
                String proveedor = campoProveedor.getText().trim();

                inventario.editarProducto(id, nombre, precio, stock, categoria, proveedor);
                JOptionPane.showMessageDialog(this, "Producto actualizado correctamente.");
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