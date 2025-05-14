package interfaz;

import modelo.Producto;
import modelo.Proveedor;
import servicio.Inventario;

import javax.swing.*;
import java.awt.*;

public class MenuProductos extends JFrame {

    public MenuProductos(Inventario inventario) {
        setTitle("Gestión de Productos");
        setSize(400, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(7, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JButton btnAgregar = new JButton("  Agregar producto    ");
        JButton btnBuscar = new JButton("   Buscar producto  ");
        JButton btnEditar = new JButton("   Editar producto  ");
        JButton btnEliminar = new JButton(" Eliminar producto   ");
        JButton btnListar = new JButton("   Listar productos    ");
        JButton btnVolver = new JButton("   Volver  ");

        // Acción: Agregar
        btnAgregar.addActionListener(e -> {
            try {
                int id = Integer.parseInt(JOptionPane.showInputDialog("ID del producto:"));
                String nombre = JOptionPane.showInputDialog("Nombre:");
                double precio = Double.parseDouble(JOptionPane.showInputDialog("Precio:"));
                int stock = Integer.parseInt(JOptionPane.showInputDialog("Stock:"));
                String categoria = JOptionPane.showInputDialog("Categoría:");

                Producto nuevo = new Producto(id, nombre, precio, stock, categoria, null);
                boolean agregado = inventario.agregarProducto(nuevo);

                if (agregado) {
                    JOptionPane.showMessageDialog(this, "✅ Producto agregado.");
                } else {
                    JOptionPane.showMessageDialog(this, "❌ Ya existe un producto con ese ID.");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "⚠ Error al ingresar los datos.");
            }
        });

        // Acción: Buscar
        btnBuscar.addActionListener(e -> {
            int id = Integer.parseInt(JOptionPane.showInputDialog("ID del producto a buscar:"));
            Producto p = inventario.buscarProducto(id);
            if (p != null) {
                JOptionPane.showMessageDialog(this,
                        "ID: " + p.getProducto_id() + "\n" +
                                "Nombre: " + p.getNombre() + "\n" +
                                "Precio: " + p.getPrecio() + "\n" +
                                "Stock: " + p.getStock() + "\n" +
                                "Categoría: " + p.getCategoria());
            } else {
                JOptionPane.showMessageDialog(this, "❌ Producto no encontrado.");
            }
        });

        // Acción: Editar
        btnEditar.addActionListener(e -> {
            int id = Integer.parseInt(JOptionPane.showInputDialog("ID del producto a editar:"));
            Producto actual = inventario.buscarProducto(id);
            if (actual != null) {
                String nombre = JOptionPane.showInputDialog("Nuevo nombre:", actual.getNombre());
                double precio = Double.parseDouble(JOptionPane.showInputDialog("Nuevo precio:", actual.getPrecio()));
                int stock = Integer.parseInt(JOptionPane.showInputDialog("Nuevo stock:", actual.getStock()));
                String categoria = JOptionPane.showInputDialog("Nueva categoría:", actual.getCategoria());

                Producto actualizado = new Producto(id, nombre, precio, stock, categoria, actual.getProveedor());
                inventario.editarProducto(id, actualizado);
                JOptionPane.showMessageDialog(this, "✅ Producto actualizado.");
            } else {
                JOptionPane.showMessageDialog(this, "❌ Producto no encontrado.");
            }
        });

        // Acción: Eliminar
        btnEliminar.addActionListener(e -> {
            int id = Integer.parseInt(JOptionPane.showInputDialog("ID del producto a eliminar:"));
            boolean eliminado = inventario.eliminarProducto(id);
            if (eliminado) {
                JOptionPane.showMessageDialog(this, "✅ Producto eliminado.");
            } else {
                JOptionPane.showMessageDialog(this, "❌ Producto no encontrado.");
            }
        });

        // Acción: Listar todos
        btnListar.addActionListener(e -> {
            if (inventario.getProductos().isEmpty()) {
                JOptionPane.showMessageDialog(this, "⚠ No hay productos registrados.");
            } else {
                StringBuilder lista = new StringBuilder();
                for (Producto p : inventario.getProductos()) {
                    lista.append("ID: ").append(p.getProducto_id()).append("\n")
                            .append("Nombre: ").append(p.getNombre()).append("\n")
                            .append("Precio: ").append(p.getPrecio()).append("\n")
                            .append("Stock: ").append(p.getStock()).append("\n")
                            .append("Categoría: ").append(p.getCategoria()).append("\n")
                            .append("-----------------------\n");
                }
                JOptionPane.showMessageDialog(this, lista.toString());
            }
        });

        btnVolver.addActionListener(e -> dispose());

        panel.add(btnAgregar);
        panel.add(btnBuscar);
        panel.add(btnEditar);
        panel.add(btnEliminar);
        panel.add(btnListar);
        panel.add(btnVolver);

        add(panel);
        setVisible(true);
    }
}
