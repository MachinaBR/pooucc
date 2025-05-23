package Interfaz.Inventarios;

import Modelo.Producto;
import Servicios.Inventario;

import javax.swing.*;
import java.awt.*;

public class AgregarProductoForm extends JFrame {

    public AgregarProductoForm(JFrame parent, Inventario inventario) {
        setTitle("Agregar Producto");
        setSize(450, 350);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(250, 250, 250));

        // Título
        JLabel titulo = new JLabel("Agregar Nuevo Producto", SwingConstants.CENTER);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 20));
        titulo.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10));
        add(titulo, BorderLayout.NORTH);

        // Panel de campos
        JPanel panelCampos = new JPanel(new GridBagLayout());
        panelCampos.setBackground(Color.WHITE);
        panelCampos.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.anchor = GridBagConstraints.WEST;

        // Campos
        JTextField txtNombre = new JTextField(20);
        JTextField txtPrecio = new JTextField(20);
        JTextField txtStock = new JTextField(20);
        JTextField txtCategoria = new JTextField(20);
        JTextField txtProveedor = new JTextField(20);

        // Añadir etiquetas y campos al panel
        gbc.gridx = 0; gbc.gridy = 0; panelCampos.add(new JLabel("Nombre:"), gbc);
        gbc.gridx = 1; panelCampos.add(txtNombre, gbc);

        gbc.gridx = 0; gbc.gridy++; panelCampos.add(new JLabel("Precio:"), gbc);
        gbc.gridx = 1; panelCampos.add(txtPrecio, gbc);

        gbc.gridx = 0; gbc.gridy++; panelCampos.add(new JLabel("Cantidad:"), gbc);
        gbc.gridx = 1; panelCampos.add(txtStock, gbc);

        gbc.gridx = 0; gbc.gridy++; panelCampos.add(new JLabel("Categoría:"), gbc);
        gbc.gridx = 1; panelCampos.add(txtCategoria, gbc);

        gbc.gridx = 0; gbc.gridy++; panelCampos.add(new JLabel("Proveedor:"), gbc);
        gbc.gridx = 1; panelCampos.add(txtProveedor, gbc);

        add(panelCampos, BorderLayout.CENTER);

        // Panel de botones
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        JButton btnAgregar = new JButton("Agregar");
        JButton btnCancelar = new JButton("Cancelar");

        btnAgregar.setBackground(new Color(33, 150, 243));
        btnAgregar.setForeground(Color.WHITE);
        btnAgregar.setFocusPainted(false);
        btnAgregar.setFont(new Font("Segoe UI", Font.BOLD, 14));

        btnCancelar.setBackground(new Color(200, 200, 200));
        btnCancelar.setFocusPainted(false);
        btnCancelar.setFont(new Font("Segoe UI", Font.BOLD, 14));

        panelBotones.setBackground(Color.WHITE);
        panelBotones.add(btnAgregar);
        panelBotones.add(btnCancelar);
        add(panelBotones, BorderLayout.SOUTH);

        // Acción botón Agregar
        btnAgregar.addActionListener(e -> {
            try {
                int id = inventario.generarIdAuto(); // ID automática
                String nombre = txtNombre.getText().trim();
                double precio = Double.parseDouble(txtPrecio.getText());
                int stock = Integer.parseInt(txtStock.getText());
                String categoria = txtCategoria.getText().trim();
                String proveedor = txtProveedor.getText().trim();

                Producto nuevo = new Producto(id, nombre, precio, stock, categoria, proveedor);
                inventario.agregarProducto(nuevo);

                JOptionPane.showMessageDialog(this, "✅ Producto agregado con éxito.");
                dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "❌ Error: Verifica los datos ingresados.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Acción botón Cancelar
        btnCancelar.addActionListener(e -> dispose());

        setVisible(true);
    }
}
