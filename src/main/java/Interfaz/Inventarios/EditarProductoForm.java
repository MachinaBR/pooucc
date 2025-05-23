package Interfaz.Inventarios;

import Modelo.Producto;
import Servicios.Inventario;

import javax.swing.*;
import java.awt.*;

public class EditarProductoForm extends JFrame {
    private final JTextField txtIdBuscar, txtNombre, txtPrecio, txtStock, txtCategoria, txtProveedor;
    private final JButton btnGuardar;

    public EditarProductoForm(JFrame parent, Inventario inventario) {
        setTitle("Editar Producto");
        setSize(400, 430);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.WHITE);

        JLabel titulo = new JLabel("Editar Producto", SwingConstants.CENTER);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 20));
        titulo.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10));
        add(titulo, BorderLayout.NORTH);

        JPanel panelCampos = new JPanel(new GridLayout(6, 2, 10, 10));
        panelCampos.setBorder(BorderFactory.createEmptyBorder(20, 40, 10, 40));
        panelCampos.setBackground(Color.WHITE);

        txtIdBuscar = new JTextField();
        JButton btnBuscar = new JButton("Buscar");

        txtNombre = new JTextField();
        txtPrecio = new JTextField();
        txtStock = new JTextField();
        txtCategoria = new JTextField();
        txtProveedor = new JTextField();

        panelCampos.add(new JLabel("ID del producto:"));
        JPanel buscarPanel = new JPanel(new BorderLayout(5, 0));
        buscarPanel.setBackground(Color.WHITE);
        buscarPanel.add(txtIdBuscar, BorderLayout.CENTER);
        buscarPanel.add(btnBuscar, BorderLayout.EAST);
        panelCampos.add(buscarPanel);

        panelCampos.add(new JLabel("Nombre:"));
        panelCampos.add(txtNombre);
        panelCampos.add(new JLabel("Precio:"));
        panelCampos.add(txtPrecio);
        panelCampos.add(new JLabel("Cantidad:"));
        panelCampos.add(txtStock);
        panelCampos.add(new JLabel("Categoría:"));
        panelCampos.add(txtCategoria);
        panelCampos.add(new JLabel("Proveedor:"));
        panelCampos.add(txtProveedor);

        add(panelCampos, BorderLayout.CENTER);

        // Botones inferiores
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

        // Acción: Buscar producto
        btnBuscar.addActionListener(e -> {
            try {
                int id = Integer.parseInt(txtIdBuscar.getText().trim());
                Producto producto = inventario.buscarProductoPorID(id);
                if (producto != null) {
                    txtNombre.setText(producto.getNombre());
                    txtPrecio.setText(String.valueOf(producto.getPrecio()));
                    txtStock.setText(String.valueOf(producto.getStock()));
                    txtCategoria.setText(producto.getCategoria());
                    txtProveedor.setText(producto.getProveedor());
                    btnGuardar.setEnabled(true);
                } else {
                    JOptionPane.showMessageDialog(this, "Producto no encontrado.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "ID inválido.");
            }
        });

        // Acción: Guardar cambios
        btnGuardar.addActionListener(e -> {
            try {
                int id = Integer.parseInt(txtIdBuscar.getText());
                String nombre = txtNombre.getText().trim();
                double precio = Double.parseDouble(txtPrecio.getText());
                int stock = Integer.parseInt(txtStock.getText());
                String categoria = txtCategoria.getText().trim();
                String proveedor = txtProveedor.getText().trim();

                boolean actualizado = inventario.editarProducto(id, nombre, precio, stock, categoria, proveedor);
                if (actualizado) {
                    JOptionPane.showMessageDialog(this, "Producto actualizado correctamente.");
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "No se pudo actualizar el producto.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Verifica los campos ingresados.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnCancelar.addActionListener(e -> dispose());

        setVisible(true);
    }
}
