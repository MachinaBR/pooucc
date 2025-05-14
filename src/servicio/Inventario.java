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

    //Aumentar la cantidad del producto
    public boolean aumentarStock(int id, int cantidad) {
        Producto p = buscarProducto(id);
        if (p != null && cantidad > 0) {
            p.setStock(p.getStock() + cantidad);
            return true;
        }
        return false;
    }


    // Disminuir la cantidad del producto
    public boolean disminuirStock(int id, int cantidad) {
        Producto p = buscarProducto(id);
        if (p != null && cantidad > 0 && p.getStock() >= cantidad) {
            p.setStock(p.getStock() - cantidad);
            return true;
        }
        return false;
    }

    // Buscar varios productos por categoría
    public List<Producto> buscarProductosPorCategoria(String categoria) {
        List<Producto> resultado = new ArrayList<>();
        for (Producto p : productos) {
            if (p.getCategoria().equalsIgnoreCase(categoria)) {
                resultado.add(p);
            }
        }
        return resultado;
    }

    // Asociar proveedor a producto por ID
    public boolean asociarProveedor(int productoId, Proveedor proveedor) {
        Producto p = buscarProducto(productoId);
        if (p != null) {
            p.setProveedor(proveedor);
            return true;
        }
        return false;
    }

    // Editar producto por ID
    public boolean editarProducto(int id, Producto nuevo) {
        Producto actual = buscarProducto(id);
        if (actual != null) {
            actual.setNombre(nuevo.getNombre());
            actual.setPrecio(nuevo.getPrecio());
            actual.setStock(nuevo.getStock());
            actual.setCategoria(nuevo.getCategoria());
            actual.setProveedor(nuevo.getProveedor());
            return true;
        }
        return false;
    }
    //MOSTRAR TODOS LOS PRODUCTOS

    public void listarProductos() {
        if (productos.isEmpty()) {
            System.out.println("⚠No hay productos registrados.");
        } else {
            for (Producto p : productos) {
                System.out.println("ID: " + p.getProducto_id());
                System.out.println("Nombre: " + p.getNombre());
                System.out.println("Precio: " + p.getPrecio());
                System.out.println("Stock: " + p.getStock());
                System.out.println("Categoría: " + p.getCategoria());
                System.out.println("Proveedor: " + (p.getProveedor() != null ? p.getProveedor().getProveedor_name() : "Sin proveedor"));
                System.out.println("----------------------------");
            }
        }
    }


    public List<Producto> getProductos() {
        return productos;
    }

    // ================== PROVEEDORES ==================
    // Agregar proveedor
    public boolean agregarProveedor(Proveedor proveedor) {
        if (buscarProveedor(proveedor.getProveedor_id()) == null) {
            proveedores.add(proveedor);
            return true;
        }
        return false;
    }

    // Eliminar proveedor por ID
    public boolean eliminarProveedor(int id) {
        Proveedor p = buscarProveedor(id);
        if (p != null) {
            proveedores.remove(p);
            return true;
        }
        return false;
    }
    // Buscar proveedor por ID
    public Proveedor buscarProveedor(int id) {
        for (Proveedor p : proveedores) {
            if (p.getProveedor_id() == id) return p;
        }
        return null;
    }

    // Editar proveedor por ID
    public boolean editarProveedor(int id, Proveedor nuevo) {
        Proveedor actual = buscarProveedor(id);
        if (actual != null) {
            actual.setProveedor_name(nuevo.getProveedor_name());
            actual.setProveedor_email(nuevo.getProveedor_email());
            actual.setProveedor_telefono(nuevo.getProveedor_telefono());
            actual.setProveedor_direccion(nuevo.getProveedor_direccion());
            return true;
        }
        return false;
    }

    // Mostrar todos los proveedores (versión consola)
    public void listarProveedores() {
        if (proveedores.isEmpty()) {
            System.out.println("⚠ No hay proveedores registrados.");
        } else {
            for (Proveedor p : proveedores) {
                System.out.println("ID: " + p.getProveedor_id());
                System.out.println("Nombre: " + p.getProveedor_name());
                System.out.println("Email: " + p.getProveedor_email());
                System.out.println("Teléfono: " + p.getProveedor_telefono());
                System.out.println("Dirección: " + p.getProveedor_direccion());
                System.out.println("----------------------------");
            }
        }
    }


    public List<Proveedor> getProveedores() {
        return proveedores;
    }
}
