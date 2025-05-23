package Interfaz;

import Interfaz.Inventarios.BuscarProductoForm;
import Modelo.Producto;
import Modelo.Vendedor;
import Servicios.GestorProveedores;
import Servicios.Inventario;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class InventarioVendedor extends JFrame {

    private final Vendedor vendedor;
    private final Inventario inventario;
    private DefaultTableModel modeloTabla;
    private JTable tabla;
    private final GestorProveedores proveedores;

    public InventarioVendedor(Vendedor vendedor, Inventario inventario, GestorProveedores proveedores) {
        this.vendedor = vendedor;
        this.inventario = inventario;
        this.proveedores = proveedores;

        setTitle("Inventario - Vendedor");
        setSize(850, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.WHITE);

        JLabel titulo = new JLabel("Inventario Disponible", SwingConstants.CENTER);
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

        JPanel panelBotones = new JPanel(new GridLayout(1, 3, 10, 0));
        panelBotones.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
        panelBotones.setBackground(Color.WHITE);

        JButton btnBuscar = crearBoton("Buscar Producto");
        JButton btnActualizar = crearBoton("Actualizar Lista");
        btnActualizar.setBackground(new Color(76, 175, 80));
        JButton btnCerrar = crearBoton("Volver al Menu");

        btnBuscar.addActionListener(e -> new BuscarProductoForm(this, inventario));
        btnActualizar.addActionListener(e -> cargarProductos());
        btnCerrar.addActionListener(e -> {
            dispose(); // Cierra solo esta ventana
            new MenuVendedor(vendedor, inventario, proveedores);});

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
