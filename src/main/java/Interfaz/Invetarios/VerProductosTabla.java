package Interfaz.Invetarios;

import Modelo.Producto;
import Servicios.Inventario;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class VerProductosTabla extends JDialog {

    public VerProductosTabla(JFrame parent, Inventario inventario) {
        super(parent, "Lista de Productos", true);
        setSize(800, 400);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());

        JLabel titulo = new JLabel("Productos Registrados", SwingConstants.CENTER);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 22));
        titulo.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10));
        add(titulo, BorderLayout.NORTH);

        // Columnas
        String[] columnas = {"ID", "Nombre", "Precio", "Stock", "Categoría", "Proveedor"};
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0);

        // Obtener productos
        ArrayList<Producto> productos = inventario.listaProductoss();
        for (Producto p : productos) {
            Object[] fila = {
                    p.getProductoId(),
                    p.getNombre(),
                    p.getPrecio(),
                    p.getStock(),
                    p.getCategoria(),
                    p.getProveedor()
            };
            modelo.addRow(fila);
        }

        JTable tabla = new JTable(modelo);
        tabla.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        tabla.setRowHeight(28);
        tabla.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));

        JScrollPane scroll = new JScrollPane(tabla);
        add(scroll, BorderLayout.CENTER);

        setVisible(true);
    }
}
