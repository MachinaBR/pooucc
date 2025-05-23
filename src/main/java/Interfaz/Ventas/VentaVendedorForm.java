package Interfaz.Ventas;

import Modelo.*;
import Servicios.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class VentaVendedorForm extends JFrame {

    private final Usuario usuario; // Puede ser Vendedor o Administrador
    private final Inventario inventario;
    private final GestorVentas gestorVentas;

    private JTable tablaProductos;
    private JTable tablaCarrito;
    private JLabel lblTotal;
    private JSpinner spinnerCantidad;
    private DefaultTableModel modeloCarrito;

    public VentaVendedorForm(Usuario usuario, Inventario inventario, GestorVentas gestorVentas) {
        this.usuario = usuario;
        this.inventario = inventario;
        this.gestorVentas = gestorVentas;

        setTitle("Panel de Ventas - Usuario");
        setSize(900, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(1, 2));

        initUI();
        setVisible(true);
    }

    private void initUI() {
        add(crearPanelProductos());
        add(crearPanelCarrito());
    }

    private JPanel crearPanelProductos() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Productos Disponibles"));

        String[] columnas = {"ID", "Nombre", "Precio", "Stock"};
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0);

        for (Producto p : usuario.verProducto(inventario)) {
            modelo.addRow(new Object[]{
                    p.getProductoId(), p.getNombre(), p.getPrecio(), p.getStock()
            });
        }

        tablaProductos = new JTable(modelo);
        JScrollPane scroll = new JScrollPane(tablaProductos);

        JPanel panelInferior = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        spinnerCantidad = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1));
        JButton btnAgregar = new JButton("Agregar al carrito");
        btnAgregar.addActionListener(e -> agregarAlCarrito());

        panelInferior.add(new JLabel("Cantidad:"));
        panelInferior.add(spinnerCantidad);
        panelInferior.add(btnAgregar);

        panel.add(scroll, BorderLayout.CENTER);
        panel.add(panelInferior, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel crearPanelCarrito() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Carrito de Compra"));

        String[] columnas = {"Producto", "Cantidad", "Subtotal"};
        modeloCarrito = new DefaultTableModel(columnas, 0);
        tablaCarrito = new JTable(modeloCarrito);

        lblTotal = new JLabel("Total: $0.0", SwingConstants.RIGHT);
        lblTotal.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblTotal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Botón Eliminar
        JButton btnEliminar = new JButton("Eliminar Seleccionado");
        btnEliminar.addActionListener(e -> eliminarDelCarrito());

        JButton btnFinalizar = new JButton("Finalizar Venta");
        btnFinalizar.addActionListener(e -> finalizarVenta());

        JButton btnVerVentas = new JButton("Ver mis ventas");
        btnVerVentas.addActionListener(e -> new HistorialVentasForm(usuario, gestorVentas));

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelBotones.add(btnVerVentas);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnFinalizar);

        panel.add(new JScrollPane(tablaCarrito), BorderLayout.CENTER);
        panel.add(panelBotones, BorderLayout.NORTH);
        panel.add(lblTotal, BorderLayout.SOUTH);

        return panel;
    }


    private void agregarAlCarrito() {
        int fila = tablaProductos.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Selecciona un producto.");
            return;
        }

        int id = (int) tablaProductos.getValueAt(fila, 0);
        int cantidad = (int) spinnerCantidad.getValue();

        Producto producto = usuario.buscarProductoParaVentaPorID(inventario, id);
        if (producto == null) {
            JOptionPane.showMessageDialog(this, "Producto no encontrado.");
            return;
        }

        if (producto.getStock() == 0) {
            JOptionPane.showMessageDialog(this, " El producto está agotado. No se puede agregar.");
            return;
        }

        if (cantidad > producto.getStock()) {
            JOptionPane.showMessageDialog(this, "Stock insuficiente. Solo hay " + producto.getStock() + " unidades.");
            return;
        }

        usuario.agregarProductoAlCarrito(producto, cantidad);

        modeloCarrito.addRow(new Object[]{
                producto.getNombre(),
                cantidad,
                producto.getPrecio() * cantidad
        });

        actualizarTotal();
    }

    private void finalizarVenta() {
        if (modeloCarrito.getRowCount() == 0) {
            JOptionPane.showMessageDialog(this, "El carrito está vacío.");
            return;
        }

        List<ItemVenta> carrito = usuario.obtenerCarrito();
        for (ItemVenta item : carrito) {
            Producto p = inventario.buscarProductoPorID(item.getProductoID());
            if (p == null || p.getStock() < item.getCantidad()) {
                JOptionPane.showMessageDialog(this,
                        " No hay suficiente stock para el producto: " + item.getNombre(),
                        "Stock insuficiente", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        for (ItemVenta item : carrito) {
            Producto p = inventario.buscarProductoPorID(item.getProductoID());
            p.setStock(p.getStock() - item.getCantidad());
        }

        usuario.finalizarVenta(gestorVentas);
        JOptionPane.showMessageDialog(this, "✅ Venta registrada por $" + usuario.calcularTotalCarrito());

        modeloCarrito.setRowCount(0);
        lblTotal.setText("Total: $0.0");

        dispose();
        new VentaVendedorForm(usuario, inventario, gestorVentas);
    }

    private void actualizarTotal() {
        double total = usuario.calcularTotalCarrito();
        lblTotal.setText("Total: $" + total);
    }

    private void verVentas() {
        List<Venta> ventas;
        try {
            if (usuario instanceof Administrador) {
                ventas = usuario.verTodasLasVentas(gestorVentas);
            } else {
                ventas = usuario.verMisVentas(gestorVentas);
            }

            StringBuilder mensaje = new StringBuilder("Ventas realizadas:\n");
            for (Venta v : ventas) {
                mensaje.append("ID: ").append(v.getVentaID())
                        .append(" - Total: $").append(v.getTotal())
                        .append(" - Fecha: ").append(v.getFecha()).append("\n");
            }

            JOptionPane.showMessageDialog(this, mensaje.toString());
        } catch (UnsupportedOperationException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }

    private void eliminarDelCarrito() {
        int fila = tablaCarrito.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Selecciona un producto del carrito para eliminar.");
            return;
        }

        String nombreProducto = (String) modeloCarrito.getValueAt(fila, 0);
        usuario.quitarProductoDelCarritoPorNombre(nombreProducto);
        modeloCarrito.removeRow(fila);
        actualizarTotal();
    }
}
