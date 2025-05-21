package Interfaz.Proveedores;

import Modelo.Proveedor;
import Servicios.ProveedorServicos;

import javax.swing.*;
import java.awt.*;

public class BuscarProveedorForm extends JDialog {

    public BuscarProveedorForm(VerProveedoresTabla parent, ProveedorServicos proveedorServicos) {
        super(parent, "Buscar Proveedor", true);
        setSize(450, 400);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());

        // 🧭 Título
        JLabel titulo = new JLabel("Buscar Proveedor", SwingConstants.CENTER);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titulo.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10));
        add(titulo, BorderLayout.NORTH);

        // 📋 Panel de formulario
        JPanel panelForm = new JPanel(new GridLayout(6, 2, 10, 12));
        panelForm.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        JTextField campoId = new JTextField();
        JTextField campoNombre = new JTextField();
        JTextField campoContacto = new JTextField();
        JTextField campoTelefono = new JTextField();
        JTextField campoEmail = new JTextField();

        // Deshabilitar campos (solo lectura)
        campoNombre.setEditable(false);
        campoContacto.setEditable(false);
        campoTelefono.setEditable(false);
        campoEmail.setEditable(false);

        panelForm.add(new JLabel("ID del Proveedor:"));
        panelForm.add(campoId);
        panelForm.add(new JLabel("Nombre:"));
        panelForm.add(campoNombre);
        panelForm.add(new JLabel("Contacto:"));
        panelForm.add(campoContacto);
        panelForm.add(new JLabel("Teléfono:"));
        panelForm.add(campoTelefono);
        panelForm.add(new JLabel("Email:"));
        panelForm.add(campoEmail);

        add(panelForm, BorderLayout.CENTER);

        // 🔎 Botón Buscar
        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.setBackground(new Color(33, 150, 243));
        btnBuscar.setForeground(Color.WHITE);
        btnBuscar.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnBuscar.setFocusPainted(false);
        btnBuscar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnBuscar.setPreferredSize(new Dimension(140, 40));

        btnBuscar.addActionListener(e -> {
            try {
                int id = Integer.parseInt(campoId.getText().trim());
                Proveedor proveedor = proveedorServicos.buscarPorId(id);

                if (proveedor == null) {
                    JOptionPane.showMessageDialog(this, "⚠️ No se encontró un proveedor con ese ID.", "Error", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                campoNombre.setText(proveedor.getNombre());
                campoContacto.setText(proveedor.getContacto());
                campoTelefono.setText(proveedor.getTelefono());
                campoEmail.setText(proveedor.getEmail());

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "⚠️ El ID debe ser un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        JPanel panelBoton = new JPanel();
        panelBoton.add(btnBuscar);
        add(panelBoton, BorderLayout.SOUTH);

        setVisible(true);
    }
}
