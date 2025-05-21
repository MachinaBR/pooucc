package Interfaz.Invetarios;

import Modelo.Producto;
import Modelo.Proveedor;
import Servicios.Inventario;
import Servicios.ProveedorServicos;

import javax.swing.*;
import java.awt.*;

public class AgregarProductoForm extends JDialog {

    private final Inventario inventario;

    public AgregarProductoForm(JFrame parent, Inventario inventario, ProveedorServicos proveedorServicios) {
        super(parent, "Agregar Producto", true);
        this.inventario = inventario;

        setSize(420, 400);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());

        JLabel titulo = new JLabel("Agregar Nuevo Producto", SwingConstants.CENTER);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 22));
        titulo.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10));
        add(titulo, BorderLayout.NORTH);

        JPanel panelForm = new JPanel(new GridLayout(5, 2, 10, 15));
        panelForm.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        JTextField campoNombre = new JTextField();
        JTextField campoPrecio = new JTextField();
        JTextField campoStock = new JTextField();
        JTextField campoCategoria = new JTextField();
        JComboBox<String> comboProveedores = new JComboBox<>();

        // Rellenar combo con proveedores
        for (Proveedor p : proveedorServicios.getListaProveedores()) {
            comboProveedores.addItem(p.getNombre());
        }

        panelForm.add(new JLabel("Nombre:"));
        panelForm.add(campoNombre);
        panelForm.add(new JLabel("Precio:"));
        panelForm.add(campoPrecio);
        panelForm.add(new JLabel("Stock:"));
        panelForm.add(campoStock);
        panelForm.add(new JLabel("Categoría:"));
        panelForm.add(campoCategoria);
        panelForm.add(new JLabel("Proveedor:"));
        panelForm.add(comboProveedores);

        add(panelForm, BorderLayout.CENTER);

        JButton btnGuardar = new JButton("Guardar Producto");
        btnGuardar.setBackground(new Color(33, 150, 243));
        btnGuardar.setForeground(Color.WHITE);
        btnGuardar.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnGuardar.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btnGuardar.addActionListener(e -> {
            try {
                String nombre = campoNombre.getText().trim();
                double precio = Double.parseDouble(campoPrecio.getText().trim());
                int stock = Integer.parseInt(campoStock.getText().trim());
                String categoria = campoCategoria.getText().trim();
                String proveedor = (String) comboProveedores.getSelectedItem();

                Producto nuevo = new Producto(0, nombre, precio, stock, categoria, proveedor);
                inventario.agregarProducto(nuevo);

                JOptionPane.showMessageDialog(this, "Producto agregado correctamente.");
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
