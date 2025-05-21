package Interfaz;

import Interfaz.Invetarios.*;
import Servicios.Inventario;
import Servicios.ProveedorServicos;
import Modelo.Producto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class InventarioInterfaz extends JFrame {

    private final Inventario inventario;
    private final ProveedorServicos proveedorServicos;
    private DefaultTableModel modeloTabla;
    private JTable tabla;

    public InventarioInterfaz(Inventario inventario, ProveedorServicos proveedorServicos) {
        this.inventario = inventario;
        this.proveedorServicos = proveedorServicos;

        setTitle("Gestión de Inventario");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(245, 245, 245));

        // Título superior
        JLabel titulo = new JLabel("Lista de Productos", SwingConstants.CENTER);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 26));
        titulo.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10));
        add(titulo, BorderLayout.NORTH);

        // Tabla
        String[] columnas = {"ID", "Nombre", "Precio", "Stock", "Categoría", "Proveedor"};
        modeloTabla = new DefaultTableModel(columnas, 0);
        tabla = new JTable(modeloTabla);
        tabla.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        tabla.setRowHeight(28);
        tabla.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        JScrollPane scroll = new JScrollPane(tabla);
        add(scroll, BorderLayout.CENTER);

        // Botones inferiores
        JPanel panelBotones = new JPanel(new GridLayout(1, 6, 10, 0));
        panelBotones.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));

        JButton btnAgregar = crearBoton("Agregar");
        JButton btnEditar = crearBoton("Editar");
        JButton btnEliminar = crearBoton("Eliminar");
        JButton btnBuscar = crearBoton("Buscar");
        JButton btnActualizar = crearBoton("Actualizar");
        btnActualizar.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnActualizar.setBackground(new Color(76, 175, 80)); // Verde
        btnActualizar.setForeground(Color.WHITE);
        btnActualizar.setFocusPainted(false);
        btnActualizar.setCursor(new Cursor(Cursor.HAND_CURSOR));

        JButton btnCerrar = crearBoton("Cerrar");

        btnAgregar.addActionListener(e -> new AgregarProductoForm(this, inventario, proveedorServicos));
        btnEditar.addActionListener(e -> new EditarProductoForm(this, inventario));
        btnEliminar.addActionListener(e -> new EliminarProductoForm(this, inventario));
        btnBuscar.addActionListener(e -> new BuscarProductoForm(this, inventario));
        btnActualizar.addActionListener(e -> cargarProductos());
        btnCerrar.addActionListener(e -> {
            dispose();
            new MenuPrincipal(inventario, proveedorServicos);
        });

        panelBotones.add(btnAgregar);
        panelBotones.add(btnEditar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnBuscar);
        panelBotones.add(btnActualizar);
        panelBotones.add(btnCerrar);

        add(panelBotones, BorderLayout.SOUTH);

        // Cargar tabla al iniciar
        cargarProductos();
        setVisible(true);
    }

    private void cargarProductos() {
        modeloTabla.setRowCount(0); // Limpiar tabla
        for (Producto p : inventario.listaProductoss()) {
            Object[] fila = {
                    p.getProductoId(),
                    p.getNombre(),
                    p.getPrecio(),
                    p.getStock(),
                    p.getCategoria(),
                    p.getProveedor()
            };
            modeloTabla.addRow(fila);
        }
    }

    private JButton crearBoton(String texto) {
        JButton boton = new JButton(texto);
        boton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        boton.setBackground(new Color(33, 150, 243));
        boton.setForeground(Color.WHITE);
        boton.setFocusPainted(false);
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return boton;
    }


}
