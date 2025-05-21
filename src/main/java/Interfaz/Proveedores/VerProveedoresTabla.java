package Interfaz.Proveedores;

import Modelo.Proveedor;
import Servicios.ProveedorServicos;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class VerProveedoresTabla extends JDialog {

    private DefaultTableModel modelo;
    private ProveedorServicos proveedorServicos;

    public VerProveedoresTabla(JFrame parent, ProveedorServicos proveedorServicos) {
        super(parent, "Lista de Proveedores", true);
        this.proveedorServicos = proveedorServicos;

        setSize(800, 500);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());

        // 🧭 Título
        JLabel titulo = new JLabel("Lista de Proveedores", SwingConstants.CENTER);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 26));
        titulo.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10));
        add(titulo, BorderLayout.NORTH);

        // 🧾 Tabla de proveedores
        String[] columnas = {"ID", "Nombre", "Contacto", "Teléfono", "Email"};
        modelo = new DefaultTableModel(columnas, 0);
        JTable tabla = new JTable(modelo);
        JScrollPane scroll = new JScrollPane(tabla);
        add(scroll, BorderLayout.CENTER);

        // 🔃 Botón actualizar
        JButton btnActualizar = crearBoton("Actualizar");
        btnActualizar.setBackground(new Color(76, 175, 80)); // Verde
        btnActualizar.addActionListener(e -> actualizarTabla());

        // 🔘 Botones inferiores
        JPanel panelBotones = new JPanel(new GridLayout(1, 6, 10, 0));
        panelBotones.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));

        JButton btnAgregar = crearBoton("Agregar");
        JButton btnEditar = crearBoton("Editar");
        JButton btnEliminar = crearBoton("Eliminar");
        JButton btnBuscar = crearBoton("Buscar");
        JButton btnCerrar = crearBoton("Cerrar");

        btnAgregar.addActionListener(e -> new AgregarProveedorForm(this, proveedorServicos));
        btnEditar.addActionListener(e -> new EditarProveedorForm(this, proveedorServicos));
        btnEliminar.addActionListener(e -> new EliminarProveedorForm(this, proveedorServicos));
        btnBuscar.addActionListener(e -> new BuscarProveedorForm(this, proveedorServicos));
        btnCerrar.addActionListener(e -> dispose());

        // Agregamos todos los botones
        panelBotones.add(btnAgregar);
        panelBotones.add(btnEditar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnBuscar);
        panelBotones.add(btnActualizar); // << Aquí va el nuevo
        panelBotones.add(btnCerrar);

        add(panelBotones, BorderLayout.SOUTH);

        // Cargar la tabla inicialmente
        actualizarTabla();

        setVisible(true);
    }

    private void actualizarTabla() {
        modelo.setRowCount(0); // Limpia la tabla
        ArrayList<Proveedor> lista = proveedorServicos.getListaProveedores();
        for (Proveedor p : lista) {
            Object[] fila = {
                    p.getProveedorId(),
                    p.getNombre(),
                    p.getContacto(),
                    p.getTelefono(),
                    p.getEmail()
            };
            modelo.addRow(fila);
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
