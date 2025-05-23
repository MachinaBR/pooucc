package Interfaz.Proveedores;

import Interfaz.MenuAdministrador;
import Modelo.Administrador;
import Modelo.Proveedor;
import Servicios.GestorProveedores;
import Servicios.Inventario;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ProveedorAdministrador extends JFrame {

    private final Administrador admin;
    private final GestorProveedores gestor;
    private final Inventario inventario;
    private DefaultTableModel modeloTabla;
    private JTable tabla;

    public ProveedorAdministrador(Administrador admin, Inventario inventario, GestorProveedores proveedores) {
        this.admin = admin;
        this.gestor = proveedores;
        this.inventario = inventario;

        setTitle("Gestión de Proveedores - Administrador");
        setSize(900, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.WHITE);

        // Título
        JLabel titulo = new JLabel("Lista de Proveedores", SwingConstants.CENTER);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titulo.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10));
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
        JPanel panelBotones = new JPanel(new GridLayout(1, 6, 10, 0));
        panelBotones.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
        panelBotones.setBackground(Color.WHITE);

        JButton btnAgregar = crearBoton("Agregar");
        JButton btnEditar = crearBoton("Editar");
        JButton btnEliminar = crearBoton("Eliminar");
        JButton btnBuscar = crearBoton("Buscar");
        JButton btnActualizar = crearBoton("Actualizar");
        btnActualizar.setBackground(new Color(76, 175, 80));
        JButton btnVolver = crearBoton("Volver al Menú");

        btnAgregar.addActionListener(e -> new AgregarProveedorForm(this, gestor));
        btnEditar.addActionListener(e -> new EditarProveedorForm(this, gestor)); // Corregido: era btnEliminar
        btnEliminar.addActionListener(e -> new EliminarProveedorForm(this, gestor)); // Agregado: parent JFrame
        btnBuscar.addActionListener(e -> new BuscarProveedorForm(gestor));
        btnActualizar.addActionListener(e -> cargarProveedores());
        btnVolver.addActionListener(e -> {
            dispose();
            new MenuAdministrador(admin, inventario, gestor);
        });
        panelBotones.add(btnAgregar);
        panelBotones.add(btnEditar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnBuscar);
        panelBotones.add(btnActualizar);
        panelBotones.add(btnVolver);

        add(panelBotones, BorderLayout.SOUTH);
        cargarProveedores();
        setVisible(true);
    }

    private void cargarProveedores() {
        modeloTabla.setRowCount(0);
        List<Proveedor> lista = gestor.getListaProveedores();
        for (Proveedor p : lista) {
            modeloTabla.addRow(new Object[]{
                    p.getProveedorId(),
                    p.getNombre(),
                    p.getContacto(),
                    p.getTelefono(),
                    p.getEmail()
            });
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
