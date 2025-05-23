package Interfaz;

import Interfaz.Inventarios.AgregarProductoForm;
import Interfaz.Inventarios.BuscarProductoForm;
import Interfaz.Inventarios.EditarProductoForm;
import Interfaz.Inventarios.EliminarProductoForm;
import Modelo.Administrador;
import Modelo.Producto;
import Servicios.GestorProveedores;
import Servicios.Inventario;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class InventarioAdministrador extends JFrame {

    private final Administrador admin;
    private final Inventario inventario;
    private final GestorProveedores proveedores;
    private DefaultTableModel modeloTabla;
    private JTable tabla;

    public InventarioAdministrador(Administrador admin, Inventario inventario, GestorProveedores proveedores) {
        this.admin = admin;
        this.inventario = inventario;
        this.proveedores = proveedores;

        setTitle("Inventario - Administrador");
        setSize(900, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.WHITE);

        JLabel titulo = new JLabel("Inventario de Productos", SwingConstants.CENTER);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titulo.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
        add(titulo, BorderLayout.NORTH);

        String[] columnas = {"ID", "Nombre", "Precio", "Stock", "Categoría", "Proveedor"};
        modeloTabla = new DefaultTableModel(columnas, 0);
        tabla = new JTable(modeloTabla);
        tabla.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        tabla.setRowHeight(28);
        JScrollPane scroll = new JScrollPane(tabla);
        add(scroll, BorderLayout.CENTER);

        JPanel panelBotones = new JPanel(new GridLayout(1, 6, 10, 0));
        panelBotones.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
        panelBotones.setBackground(Color.WHITE);

        JButton btnAgregar = crearBoton("Agregar");
        JButton btnEditar = crearBoton("Editar");
        JButton btnEliminar = crearBoton("Eliminar");
        JButton btnBuscar = crearBoton("Buscar");
        JButton btnActualizar = crearBoton("Actualizar");
        btnActualizar.setBackground(new Color(76, 175, 80));
        JButton btnCerrar = crearBoton("Volver al Menú");

        btnAgregar.addActionListener(e -> new AgregarProductoForm(this, inventario));
        btnEditar.addActionListener(e -> new EditarProductoForm(this, inventario));
        btnEliminar.addActionListener(e -> new EliminarProductoForm(this, inventario));
        btnBuscar.addActionListener(e -> new BuscarProductoForm(this, inventario));
        btnActualizar.addActionListener(e -> cargarProductos());
        btnCerrar.addActionListener(e -> {
            dispose(); // Cierra esta ventana
            new MenuAdministrador(admin, inventario, proveedores); // Regresa al menú principal
        });

        panelBotones.add(btnAgregar);
        panelBotones.add(btnEditar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnBuscar);
        panelBotones.add(btnActualizar);
        panelBotones.add(btnCerrar);

        add(panelBotones, BorderLayout.SOUTH);

        cargarProductos();
        setVisible(true);
    }

    private void cargarProductos() {
        modeloTabla.setRowCount(0);
        for (Producto p : inventario.getListaProductos()) {
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
