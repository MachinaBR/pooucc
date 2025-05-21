package Interfaz;

import Interfaz.Proveedores.VerProveedoresTabla;
import Servicios.Inventario;
import Servicios.ProveedorServicos;

import javax.swing.*;
import java.awt.*;

public class MenuPrincipal extends JFrame {

    private final Inventario inventario;
    private final ProveedorServicos proveedorServicos;

    public MenuPrincipal(Inventario inventario, ProveedorServicos proveedorServicos) {
        this.inventario = inventario;
        this.proveedorServicos = proveedorServicos;

        setTitle("Menú Principal - Sistema de Inventario");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        getContentPane().setBackground(new Color(245, 245, 245)); // fondo claro

        //Título superior
        JLabel titulo = new JLabel("Bienvenido al Sistema de Inventario", SwingConstants.CENTER);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 28));
        titulo.setBorder(BorderFactory.createEmptyBorder(40, 10, 10, 10));
        add(titulo, BorderLayout.NORTH);

        //Panel de botones central
        JPanel panelCentral = new JPanel(new GridLayout(2, 2, 30, 30));
        panelCentral.setBackground(Color.WHITE);
        panelCentral.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 220, 220)),
                BorderFactory.createEmptyBorder(40, 50, 40, 50)
        ));

        // Botones
        JButton btnInventario = crearBoton("Gestionar Inventario");
        JButton btnProveedores = crearBoton("Gestionar Proveedores");
        JButton btnSalir = crearBoton("Cerrar sesión");

        // Acción: Inventario
        btnInventario.addActionListener(e -> {
            dispose();
            new InventarioInterfaz(inventario, proveedorServicos);
        });

        // Acción: Proveedores
        btnProveedores.addActionListener(e -> {
            new VerProveedoresTabla(this, proveedorServicos);
        });

        // Acción: Salir
        btnSalir.addActionListener(e -> {
            dispose();
            JOptionPane.showMessageDialog(this, "Sesión cerrada.");
            System.exit(0);
        });

        // Añadir botones al panel
        panelCentral.add(btnInventario);
        panelCentral.add(btnProveedores);
        panelCentral.add(btnSalir);

        // 📐 Centrado general
        JPanel panelContenedor = new JPanel(new GridBagLayout());
        panelContenedor.setBackground(new Color(245, 245, 245));
        panelContenedor.add(panelCentral);
        add(panelContenedor, BorderLayout.CENTER);

        setVisible(true);
    }

    private JButton crearBoton(String texto) {
        JButton boton = new JButton(texto);
        boton.setFont(new Font("Segoe UI", Font.BOLD, 16));
        boton.setFocusPainted(false);
        boton.setBackground(new Color(33, 150, 243));
        boton.setForeground(Color.WHITE);
        boton.setPreferredSize(new Dimension(220, 80));
        boton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return boton;
    }
}
