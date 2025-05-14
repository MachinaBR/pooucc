package interfaz;

import servicio.Inventario;

import javax.swing.*;
import java.awt.*;

public class MenuPrincipal extends JFrame {

    public MenuPrincipal(Inventario inventario) {
        setTitle("Menú Principal");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        JLabel titulo = new JLabel("Sistema de Inventario", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 18));
        panel.add(titulo);

        JButton btnProductos = new JButton("Gestión de Productos");
        JButton btnProveedores = new JButton("Gestión de Proveedores");
        JButton btnSalir = new JButton("Cerrar Sesión");

        // Acciones de los botones
        btnProductos.addActionListener(e -> {
            new MenuProductos(inventario);
        });

        btnProveedores.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Abrir módulo de proveedores (por construir)");
            // Aquí puedes abrir: new MenuProveedores(inventario);
        });

        btnSalir.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Sesión cerrada. ¡Hasta pronto!");
            dispose(); // Cierra el menú principal
            System.exit(0);
        });

        panel.add(btnProductos);
        panel.add(btnProveedores);
        panel.add(btnSalir);

        add(panel);
        setVisible(true);
    }
}
