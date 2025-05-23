package Modelo;

import Servicios.GestorProveedores;
import Servicios.Inventario;

import java.util.List;

public abstract class Administrador extends Usuario {

    public Administrador(int usuarioID, String nombre, String apellido1, String apellido2,
                         String telefono, String email, String Password, String rol){
        super(usuarioID,nombre,apellido1,apellido2,telefono,email,Password,rol);
    }

    public Administrador(int usuarioID, String nombre, String apellido1, String password, String rol) {
        super(usuarioID, nombre, apellido1, password, rol);
    }

    // Implementación de métodos abstractos

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
    public void generarVenta(){System.out.println("Administrador puede generar una venta.");}
    //------------------------------- PROVEEDOR
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


    //Metodos propios
    @Override
    public  void agregarProducto(Inventario invetario, Producto recibeProducto){
        invetario.agregarProducto(recibeProducto);
    }
    @Override
    public boolean editarProducto(Inventario inventario, int id, String nuevoNombre, double nuevoPrecio, int nuevoStock, String nuevaCategoria, String nuevoProveedor) {
        return inventario.editarProducto(id, nuevoNombre, nuevoPrecio, nuevoStock, nuevaCategoria, nuevoProveedor);
    }
    @Override
    public boolean eliminarProducto(Inventario inventario, int id) {
        return inventario.eliminarProducto(id);
    }
    //-------------------------------PROVEEDOR
    @Override
    public void agregarProveedor(GestorProveedores gestor, Proveedor proveedor) {
        gestor.agregarProveedor(proveedor);
    }
    @Override
    public boolean eliminarProveedor(GestorProveedores gestor, int id) {
        return gestor.eliminarProveedor(id);
    }
    @Override
    public boolean editarProveedor(GestorProveedores gestor, int id, String nombre, String contacto, String telefono, String email) {
        return gestor.editarProveedor(id, nombre, contacto, telefono, email);
    }
}
