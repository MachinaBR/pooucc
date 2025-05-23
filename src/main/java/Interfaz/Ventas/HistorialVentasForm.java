package Interfaz.Ventas;

import Modelo.*;
import Servicios.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class HistorialVentasForm extends JFrame {

    public HistorialVentasForm(Usuario usuario, GestorVentas gestorVentas) {
        setTitle("Historial de Ventas");
        setSize(700, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        List<Venta> ventas;
        if (usuario instanceof Administrador) {
            ventas = usuario.verTodasLasVentas(gestorVentas);
        } else {
            ventas = usuario.verMisVentas(gestorVentas);
        }

        String[] columnas = {"ID", "Total", "Fecha", "Items", "Ver Detalle"};
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0);

        for (Venta v : ventas) {
            modelo.addRow(new Object[]{
                    v.getVentaID(),
                    "$" + v.getTotal(),
                    v.getFecha(),
                    v.getItems().size(),
                    "Ver"
            });
        }

        JTable tabla = new JTable(modelo);
        JScrollPane scroll = new JScrollPane(tabla);

        // Acción para ver detalles
        tabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int fila = tabla.getSelectedRow();
                int col = tabla.getSelectedColumn();
                if (col == 4) {
                    int idVenta = (int) tabla.getValueAt(fila, 0);
                    Venta venta = gestorVentas.buscarVentaPorID(idVenta);
                    if (venta != null) {
                        mostrarDetalleVenta(venta);
                    }
                }
            }
        });

        add(scroll);
        setVisible(true);
    }

    private void mostrarDetalleVenta(Venta venta) {
        StringBuilder detalle = new StringBuilder();
        detalle.append("Detalles de Venta ID: ").append(venta.getVentaID()).append("\n");
        for (ItemVenta item : venta.getItems()) {
            detalle.append("- ").append(item.getNombre())
                    .append(" x").append(item.getCantidad())
                    .append(" = $").append(item.getSubtotal()).append("\n");
        }
        detalle.append("\nTotal: $").append(venta.getTotal());

        JOptionPane.showMessageDialog(this, detalle.toString(), "Detalle de Venta", JOptionPane.INFORMATION_MESSAGE);
    }
}
