package Interfaz.Proveedores;

import Modelo.Proveedor;
import Servicios.ProveedorServicos;

import javax.swing.*;
import java.awt.*;

public class EliminarProveedorForm extends JDialog {

    public EliminarProveedorForm(VerProveedoresTabla parent, ProveedorServicos proveedorServicos) {
        super(parent, "Eliminar Proveedor", true);
        setSize(420, 250);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());

        // 🧭 Título
        JLabel titulo = new JLabel("Eliminar Proveedor", SwingConstants.CENTER);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titulo.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10));
        add(titulo, BorderLayout.NORTH);

        // Formulario
        JPanel panelForm = new JPanel(new GridLayout(2, 2, 10, 15));
        panelForm.setBorder(BorderFactory.createEmptyBorder(30, 40, 20, 40));

        JTextField campoId = new JTextField();

        panelForm.add(new JLabel("ID del Proveedor:"));
        panelForm.add(campoId);

        add(panelForm, BorderLayout.CENTER);

        // Botón eliminar
        JButton btnEliminar = new JButton("Eliminar");
        btnEliminar.setBackground(new Color(244, 67, 54));
        btnEliminar.setForeground(Color.WHITE);
        btnEliminar.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnEliminar.setFocusPainted(false);
        btnEliminar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnEliminar.setPreferredSize(new Dimension(140, 40));

        btnEliminar.addActionListener(e -> {
            try {
                int id = Integer.parseInt(campoId.getText().trim());
                Proveedor proveedor = proveedorServicos.buscarPorId(id);

                if (proveedor == null) {
                    JOptionPane.showMessageDialog(this, "⚠ No se encontró un proveedor con ese ID.", "Error", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                int confirmacion = JOptionPane.showConfirmDialog(this,
                        "¿Estás seguro de eliminar al proveedor:\n" + proveedor.getNombre() + "?",
                        "Confirmar Eliminación", JOptionPane.YES_NO_OPTION);

                if (confirmacion == JOptionPane.YES_OPTION) {
                    proveedorServicos.getListaProveedores().remove(proveedor);
                    JOptionPane.showMessageDialog(this, " Proveedor eliminado correctamente.");
                    dispose();
                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "⚠ID inválido. Debe ser un número.", "Error", JOptionPane.WARNING_MESSAGE);
            }
        });

        JPanel panelBoton = new JPanel();
        panelBoton.add(btnEliminar);
        add(panelBoton, BorderLayout.SOUTH);

        setVisible(true);
    }
}
