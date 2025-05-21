package Servicios;
import Modelo.Producto;
import java.util.ArrayList;
public class Inventario {

    private ArrayList<Producto> productos;

    public Inventario() {
        productos = new ArrayList<>();
    }

    public void agregarProducto(Producto producto) {
        producto.setId(generarNuevoId());
        for (Producto p : productos) {
            if(p.getProductoId() == producto.getProductoId()){
                throw new IllegalArgumentException("El producto ya existe");
            }
        }
        productos.add(producto);
    }

    public void eliminarProducto(int id) {
        Producto productoEliminar = null;
        for (Producto p : productos) {
            if (p.getProductoId() == id) {
                productoEliminar = p;
                break;
            }
        }
        if (productoEliminar != null) {
            productos.remove(productoEliminar);
        }
    }

    public void editarProducto(int id, String nuevoNombre, double nuevoPrecio, int nuevoStock,
                               String nuevaCategoria, String nuevoProveedor) {
        for (Producto p : productos) {
            if (p.getProductoId() == id) {
                p.setNombre(nuevoNombre);
                p.setPrecio(nuevoPrecio);
                p.setStock(nuevoStock);
                p.setCategoria(nuevaCategoria);
                p.setProveedor(nuevoProveedor);
                return;
            }
        }
    }

    public Producto buscarProducto(String nombre) {
        for (Producto p : productos) {
            if (p.getNombre().equals(nombre)) {
                return p;
            }
        }
        return null;
    }

    public Producto buscarProducto(int id) {
        for (Producto p : productos) {
            if (p.getProductoId() == id) {
                return p;
            }
        }
        return null;
    }

    public ArrayList<Producto> listaProductoss() {
        return productos;
    }

    private int generarNuevoId() {
        int maxId = 0;
        for (Producto p : productos) {
            if (p.getProductoId() > maxId) {
                maxId = p.getProductoId();
            }
        }
        return maxId + 1;
    }
    public void setListaProductos(ArrayList<Producto> listaProductos) {
        productos = listaProductos;
    }

}
