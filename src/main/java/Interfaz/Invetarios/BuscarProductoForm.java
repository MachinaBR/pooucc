package Interfaz.Invetarios;

import Modelo.Producto;
import Servicios.Inventario;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class BuscarProductoForm extends JDialog {

    public BuscarProductoForm(JFrame parent, Inventario inventario) {
        super(parent, "Buscar Producto", true);
        setSize(600, 200);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());

        JLabel titulo = new JLabel("Buscar Producto por ID", SwingConstants.CENTER);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 22));
        titulo.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10));
        add(titulo, BorderLayout.NORTH);

        JPanel panelCentro = new JPanel(new FlowLayout());
        JLabel lblId = new JLabel("Ingrese ID:");
        JTextField campoId = new JTextField(10);
        JButton btnBuscar = new JButton("Buscar");

        panelCentro.add(lblId);
        panelCentro.add(campoId);
        panelCentro.add(btnBuscar);
        add(panelCentro, BorderLayout.CENTER);

        btnBuscar.setBackground(new Color(33, 150, 243));
        btnBuscar.setForeground(Color.WHITE);
        btnBuscar.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnBuscar.setFocusPainted(false);
        btnBuscar.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btnBuscar.addActionListener(e -> {
            try {
                int id = Integer.parseInt(campoId.getText().trim());
                Producto p = inventario.buscarProducto(id);

                if (p == null) {
                    JOptionPane.showMessageDialog(this, "No se encontró ningún producto con ID: " + id);
                    return;
                }

                // Mostrar en JTable
                String[] columnas = {"ID", "Nombre", "Precio", "Stock", "Categoría", "Proveedor"};
                Object[][] datos = {
                        {
                                p.getProductoId(),
                                p.getNombre(),
                                p.getPrecio(),
                                p.getStock(),
                                p.getCategoria(),
                                p.getProveedor()
                        }
                };

                JTable tabla = new JTable(new DefaultTableModel(datos, columnas));
                tabla.setFont(new Font("Segoe UI", Font.PLAIN, 14));
                tabla.setRowHeight(28);
                tabla.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));

                JOptionPane.showMessageDialog(this,
                        new JScrollPane(tabla),
                        "Resultado de búsqueda",
                        JOptionPane.INFORMATION_MESSAGE);

                dispose();

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Ingrese un ID válido (número entero)");
            }
        });

        setVisible(true);
    }
}
