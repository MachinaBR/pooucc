package Interfaz.Proveedores;

import Interfaz.MenuVendedor;
import Modelo.Proveedor;
import Modelo.Vendedor;
import Servicios.GestorProveedores;
import Servicios.Inventario;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ProveedorVendedor extends JFrame {

    private final Vendedor vendedor;
    private final GestorProveedores gestor;
    private final Inventario inventario;
    private DefaultTableModel modeloTabla;
    private JTable tabla;

    public ProveedorVendedor(Vendedor vendedor, Inventario inventario, GestorProveedores gestor) {
        this.vendedor = vendedor;
        this.gestor = gestor;
        this.inventario = inventario;

        setTitle("Proveedores - Vendedor");
        setSize(850, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.WHITE);

        // Título
        JLabel titulo = new JLabel("Listado de Proveedores", SwingConstants.CENTER);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titulo.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
        add(titulo, BorderLayout.NORTH);

        // Tabla
        String[] columnas = {"ID", "Nombre", "Contacto", "Teléfono", "Email"};
        modeloTabla = new DefaultTableModel(columnas, 0);
        tabla = new JTable(modeloTabla);
        tabla.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        tabla.setRowHeight(28);
        JScrollPane scroll = new JScrollPane(tabla);
        add(scroll, BorderLayout.CENTER);

        // Botones
        JPanel panelBotones = new JPanel(new GridLayout(1, 3, 10, 0));
        panelBotones.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
        panelBotones.setBackground(Color.WHITE);

        JButton btnBuscar = crearBoton("Buscar");
        JButton btnActualizar = crearBoton("Actualizar");
        btnActualizar.setBackground(new Color(76, 175, 80));
        JButton btnVolver = crearBoton("Volver al Menú");

        btnBuscar.addActionListener(e -> buscarProveedor());
        btnActualizar.addActionListener(e -> cargarProveedores());
        btnVolver.addActionListener(e -> {
            dispose();
            new MenuVendedor(vendedor, inventario, gestor); // Corregido
        });

        panelBotones.add(btnBuscar);
        panelBotones.add(btnActualizar);
        panelBotones.add(btnVolver);

        add(panelBotones, BorderLayout.SOUTH);

        cargarProveedores();
        setVisible(true);
    }

    private void cargarProveedores() {
        modeloTabla.setRowCount(0);
        for (Proveedor p : gestor.getListaProveedores()) {
            modeloTabla.addRow(new Object[]{
                    p.getProveedorId(),
                    p.getNombre(),
                    p.getContacto(),
                    p.getTelefono(),
                    p.getEmail()
            });
        }
    }

    private void buscarProveedor() {
        String entrada = JOptionPane.showInputDialog(this, "Ingrese ID o nombre del proveedor:");
        if (entrada == null || entrada.trim().isEmpty()) return;

        modeloTabla.setRowCount(0);
        try {
            int id = Integer.parseInt(entrada);
            Proveedor proveedor = gestor.buscarProveedorPorId(id);
            if (proveedor != null) {
                modeloTabla.addRow(new Object[]{
                        proveedor.getProveedorId(),
                        proveedor.getNombre(),
                        proveedor.getContacto(),
                        proveedor.getTelefono(),
                        proveedor.getEmail()
                });
            } else {
                JOptionPane.showMessageDialog(this, "Proveedor no encontrado.");
            }
        } catch (NumberFormatException ex) {
            List<Proveedor> resultados = gestor.buscarProveedorPorNombre(entrada);
            if (!resultados.isEmpty()) {
                for (Proveedor p : resultados) {
                    modeloTabla.addRow(new Object[]{
                            p.getProveedorId(),
                            p.getNombre(),
                            p.getContacto(),
                            p.getTelefono(),
                            p.getEmail()
                    });
                }
            } else {
                JOptionPane.showMessageDialog(this, "No se encontraron proveedores con ese nombre.");
            }
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
