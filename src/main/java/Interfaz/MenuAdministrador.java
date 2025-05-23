package Interfaz;

import Interfaz.Proveedores.ProveedorAdministrador;
import Interfaz.Ventas.VentaVendedorForm;

import Modelo.Administrador;
import Servicios.GestorProveedores;
import Servicios.Inventario;

import javax.swing.*;
import java.awt.*;

public class MenuAdministrador extends JFrame {

    public MenuAdministrador(Administrador admin, Inventario inventario, GestorProveedores gestor) {
        setTitle("Panel del Administrador");
        setSize(700, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(245, 245, 245));
        setLayout(new BorderLayout());

        // Título superior
        JLabel titulo = new JLabel("Bienvenido Administrador: " + admin.getNombre(), SwingConstants.CENTER);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titulo.setBorder(BorderFactory.createEmptyBorder(30, 10, 20, 10));
        add(titulo, BorderLayout.NORTH);

        // Panel central tipo tarjeta
        JPanel panelCentro = new JPanel();
        panelCentro.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 30));
        panelCentro.setBackground(new Color(245, 245, 245));

        panelCentro.add(crearTarjeta("Inventario", () -> {
            dispose();
            new InventarioAdministrador(admin, inventario, gestor);
        }));

        panelCentro.add(crearTarjeta("Proveedores", () -> {
            dispose();
            new ProveedorAdministrador(admin, inventario, gestor);
        }));

        panelCentro.add(crearTarjeta("Ventas", () -> {
            dispose();
            new VentaVendedorForm(admin, inventario, Start.Main.ventas);
        }));

        panelCentro.add(crearTarjeta("Cerrar Sesión", () -> {
            dispose();
            new LoginInterfaz(Start.Main.empleados, inventario, gestor);
        }));

        add(panelCentro, BorderLayout.CENTER);
        setVisible(true);
    }

    //
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
                tarjeta.setBackground(new Color(220, 235, 255));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                tarjeta.setBackground(Color.WHITE);
            }
        });

        return tarjeta;
    }
}
