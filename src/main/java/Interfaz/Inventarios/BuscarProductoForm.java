package Interfaz.Inventarios;

import Modelo.Producto;
import Servicios.Inventario;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class BuscarProductoForm extends JFrame {

    private final Inventario inventario;
    private JTextField txtId;
    private JTextField txtNombre;
    private JTable tablaResultados;
    private DefaultTableModel modeloTabla;

    public BuscarProductoForm(JFrame parent, Inventario inventario) {
        this.inventario = inventario;

        setTitle("Buscar Producto");
        setSize(650, 450);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.WHITE);

        // Título
        JLabel titulo = new JLabel("Buscar Producto", SwingConstants.CENTER);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 22));
        titulo.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10));
        add(titulo, BorderLayout.NORTH);

        // Panel de campos
        JPanel panelCampos = new JPanel(new GridLayout(2, 3, 10, 10));
        panelCampos.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        panelCampos.setBackground(Color.WHITE);

        txtId = new JTextField();
        txtNombre = new JTextField();

        JButton btnBuscarId = crearBoton("Buscar por ID");
        JButton btnBuscarNombre = crearBoton("Buscar por Nombre");
        JButton btnCerrar = crearBoton("Cerrar");

        panelCampos.add(new JLabel("ID del producto:"));
        panelCampos.add(txtId);
        panelCampos.add(btnBuscarId);
        panelCampos.add(new JLabel("Nombre del producto:"));
        panelCampos.add(txtNombre);
        panelCampos.add(btnBuscarNombre);

        add(panelCampos, BorderLayout.NORTH);

        // Tabla resultados
        String[] columnas = {"ID", "Nombre", "Precio", "Stock", "Categoría", "Proveedor"};
        modeloTabla = new DefaultTableModel(columnas, 0);
        tablaResultados = new JTable(modeloTabla);
        tablaResultados.setRowHeight(24);
        tablaResultados.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        JScrollPane scroll = new JScrollPane(tablaResultados);
        add(scroll, BorderLayout.CENTER);

        // Panel botón cerrar
        JPanel panelCerrar = new JPanel();
        panelCerrar.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));
        panelCerrar.setBackground(Color.WHITE);
        panelCerrar.add(btnCerrar);
        add(panelCerrar, BorderLayout.SOUTH);

        // Acciones
        btnBuscarId.addActionListener(e -> buscarPorId());
        btnBuscarNombre.addActionListener(e -> buscarPorNombre());
        btnCerrar.addActionListener(e -> dispose());

        setVisible(true);
    }

    private void buscarPorId() {
        modeloTabla.setRowCount(0);
        try {
            int id = Integer.parseInt(txtId.getText().trim());
            Producto p = inventario.buscarProductoPorID(id);
            if (p != null) {
                modeloTabla.addRow(new Object[]{
                        p.getProductoId(), p.getNombre(), p.getPrecio(), p.getStock(), p.getCategoria(), p.getProveedor()
                });
            } else {
                JOptionPane.showMessageDialog(this, "Producto no encontrado.");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "ID inválido.");
        }
    }

    private void buscarPorNombre() {
        modeloTabla.setRowCount(0);
        String nombre = txtNombre.getText().trim();
        if (nombre.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingresa un nombre para buscar.");
            return;
        }
        List<Producto> resultados = inventario.buscarProductosPorNombre(nombre);
        if (resultados.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No se encontraron productos con ese nombre.");
        } else {
            for (Producto p : resultados) {
                modeloTabla.addRow(new Object[]{
                        p.getProductoId(), p.getNombre(), p.getPrecio(), p.getStock(), p.getCategoria(), p.getProveedor()
                });
            }
        }
    }

    private JButton crearBoton(String texto) {
        JButton btn = new JButton(texto);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btn.setBackground(new Color(33, 150, 243));
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return btn;
    }
}
