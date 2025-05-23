package Modelo;

import Servicios.GestorProveedores;
import Servicios.GestorVentas;
import Servicios.Inventario;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public abstract class Vendedor extends Usuario {

    private final List<ItemVenta> carrito = new ArrayList<>();

    public Vendedor(int usuarioID, String nombre, String apellido1, String apellido2,
                    String telefono, String email, String password, String rol) {
        super(usuarioID, nombre, apellido1, apellido2, telefono, email, password, rol);
    }

    public Vendedor(int usuarioID, String nombre, String apellido1, String password, String rol) {
        super(usuarioID, nombre, apellido1, password, rol);
    }

    // ✅ PERMITIDOS
    @Override
    public List<Producto> verProducto(Inventario inventario) {
        return inventario.getListaProductos();
    }

    @Override
    public Producto buscarProductoPorID(Inventario inventario, int id) {
        return inventario.buscarProductoPorID(id);
    }

    @Override
    public List<Producto> buscarProductoPorNombre(Inventario inventario, String nombre) {
        return inventario.buscarProductosPorNombre(nombre);
    }

    @Override
    public List<Proveedor> verProveedores(GestorProveedores gestor) {
        return gestor.getListaProveedores();
    }

    @Override
    public Proveedor buscarProveedorPorId(GestorProveedores gestor, int id) {
        return gestor.buscarProveedorPorId(id);
    }

    @Override
    public List<Proveedor> buscarProveedorPorNombre(GestorProveedores gestor, String nombre) {
        return gestor.buscarProveedorPorNombre(nombre);
    }

    // ❌ RESTRINGIDOS
    @Override
    public void agregarProducto(Inventario inventario, Producto producto) {
        throw new UnsupportedOperationException("❌ El vendedor no tiene permiso para agregar productos.");
    }

    @Override
    public boolean editarProducto(Inventario inventario, int id, String nuevoNombre, double nuevoPrecio, int nuevoStock, String nuevaCategoria, String nuevoProveedor) {
        throw new UnsupportedOperationException("❌ El vendedor no puede editar productos.");
    }

    @Override
    public boolean eliminarProducto(Inventario inventario, int id) {
        throw new UnsupportedOperationException("❌ El vendedor no puede eliminar productos.");
    }

    @Override
    public void agregarProveedor(GestorProveedores gestor, Proveedor proveedor) {
        throw new UnsupportedOperationException("❌ El vendedor no tiene permiso para agregar proveedores.");
    }

    @Override
    public boolean eliminarProveedor(GestorProveedores gestor, int id) {
        throw new UnsupportedOperationException("❌ El vendedor no puede eliminar proveedores.");
    }

    @Override
    public boolean editarProveedor(GestorProveedores gestor, int id, String nombre, String contacto, String telefono, String email) {
        throw new UnsupportedOperationException("❌ El vendedor no puede editar proveedores.");
    }

    // ✅ VENTAS
    @Override
    public void agregarProductoAlCarrito(Producto producto, int cantidad) {
        carrito.add(new ItemVenta(
                producto.getProductoId(),
                producto.getNombre(),
                producto.getPrecio(),
                cantidad
        ));
    }

    @Override
    public void quitarProductoDelCarrito(int productoId) {
        carrito.removeIf(item -> item.getProductoID() == productoId);
    }

    @Override
    public double calcularTotalCarrito() {
        return carrito.stream()
                .mapToDouble(item -> item.getPrecioUnitario() * item.getCantidad())
                .sum();
    }

    @Override
    public void finalizarVenta(GestorVentas gestorVentas) {
        Venta nueva = new Venta(0, this.usuarioID, new ArrayList<>(carrito), calcularTotalCarrito(), LocalDate.now());
        gestorVentas.registrarVenta(nueva);
        carrito.clear();
    }

    @Override
    public List<Venta> verMisVentas(GestorVentas gestorVentas) {
        return gestorVentas.obtenerVentasPorVendedor(this.usuarioID);
    }

    @Override
    public List<Venta> verTodasLasVentas(GestorVentas gestorVentas) {
        throw new UnsupportedOperationException("❌ No puede ver todas las ventas.");
    }

    @Override
    public Producto buscarProductoParaVentaPorID(Inventario inventario, int id) {
        return inventario.buscarProductoPorID(id);
    }

    @Override
    public List<Producto> buscarProductoParaVentaPorNombre(Inventario inventario, String nombre) {
        return inventario.buscarProductosPorNombre(nombre);
    }
    @Override
    public List<ItemVenta> obtenerCarrito() {
        return carrito;
    }
    @Override
    public void quitarProductoDelCarritoPorNombre(String nombreProducto) {
        carrito.removeIf(item -> item.getNombre().equalsIgnoreCase(nombreProducto));
    }

}
