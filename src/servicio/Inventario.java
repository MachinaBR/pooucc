package servicio;

import modelo.Producto;
import modelo.Proveedor;

import java.util.ArrayList;
import java.util.List;

public class Inventario {
    private List<Producto> productos;
    private List<Proveedor> proveedores;

    public Inventario() {
        this.productos = new ArrayList<>();
        this.proveedores = new ArrayList<>();
    }

    // ================== PRODUCTOS ==================
    public boolean agregarProducto(Producto producto) {
        if (buscarProducto(producto.getProducto_id()) == null) {
            productos.add(producto);
            return true;
        }
        return false;
    }

    public boolean eliminarProducto(int id) {
        Producto p = buscarProducto(id);
        if (p != null) {
            productos.remove(p);
            return true;
        }
        return false;
    }

    public Producto buscarProducto(int id) {
        for (Producto p : productos) {
            if (p.getProducto_id() == id) return p;
        }
        return null;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    // ================== PROVEEDORES ==================
    public boolean agregarProveedor(Proveedor proveedor) {
        if (buscarProveedor(proveedor.getProveedor_id()) == null) {
            proveedores.add(proveedor);
            return true;
        }
        return false;
    }

    public boolean eliminarProveedor(int id) {
        Proveedor p = buscarProveedor(id);
        if (p != null) {
            proveedores.remove(p);
            return true;
        }
        return false;
    }

    public Proveedor buscarProveedor(int id) {
        for (Proveedor p : proveedores) {
            if (p.getProveedor_id() == id) return p;
        }
        return null;
    }

    public List<Proveedor> getProveedores() {
        return proveedores;
    }
}
