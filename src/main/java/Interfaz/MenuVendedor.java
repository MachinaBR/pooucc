package Interfaz;

import Interfaz.Proveedores.ProveedorVendedor;
import Modelo.Vendedor;
import Servicios.GestorProveedores;
import Servicios.Inventario;

import javax.swing.*;
import java.awt.*;

public class MenuVendedor extends JFrame {

    public MenuVendedor(Vendedor vendedor, Inventario inventario, GestorProveedores proveedores) {
        setTitle("Panel del Vendedor");
        setSize(700, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(245, 245, 245));
        setLayout(new BorderLayout());

        // Título superior
        JLabel titulo = new JLabel("Bienvenido Vendedor: " + vendedor.getNombre(), SwingConstants.CENTER);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titulo.setBorder(BorderFactory.createEmptyBorder(30, 10, 20, 10));
        add(titulo, BorderLayout.NORTH);

        // Panel central con tarjetas
        JPanel panelCentro = new JPanel();
        panelCentro.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 30));
        panelCentro.setBackground(new Color(245, 245, 245));

        panelCentro.add(crearTarjeta(" Ver Inventario", () -> {
            dispose();
            new InventarioVendedor(vendedor, inventario, proveedores);
        }));

        panelCentro.add(crearTarjeta(" Ver Proveedores", () -> {
            dispose();
            new ProveedorVendedor(vendedor, inventario, proveedores);
        }));

        panelCentro.add(crearTarjeta(" Cerrar Sesión", () -> {
            dispose();
            new LoginInterfaz(Start.Main.empleados, inventario, proveedores);
        }));

        add(panelCentro, BorderLayout.CENTER);
        setVisible(true);
    }

    // Método para crear tarjetas modernas
    private JPanel crearTarjeta(String texto, Runnable accion) {
        JPanel tarjeta = new JPanel(new BorderLayout());
        tarjeta.setPreferredSize(new Dimension(180, 120));
        tarjeta.setBackground(Color.WHITE);
        tarjeta.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200), 1),
                BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));
        tarjeta.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        JLabel label = new JLabel(texto, SwingConstants.CENTER);
        label.setFont(new Font("Segoe UI", Font.BOLD, 16));
        tarjeta.add(label, BorderLayout.CENTER);

        tarjeta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                accion.run();
            }

            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tarjeta.setBackground(new Color(230, 240, 255));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                tarjeta.setBackground(Color.WHITE);
            }
        });

        return tarjeta;
    }
}
