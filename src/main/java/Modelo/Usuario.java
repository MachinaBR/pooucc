package Modelo;
import Servicios.GestorProveedores;
import Servicios.Inventario;

import java.util.List;

public abstract class Usuario {

    //Atributos protegidos de la superclase
    protected final int usuarioID;
    protected String nombre;
    protected String apellido1;
    protected String apellido2;
    protected String telefono;
    protected String email;
    protected String password;
    protected String rol;

    //Construtor con todos los datos
    public Usuario(int usuarioID, String nombre, String apellido1, String apellido2,
                   String telefono, String email, String password, String rol) {

        this.usuarioID = usuarioID;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.telefono = telefono;
        this.email = email;
        this.password = password;
        this.rol = rol;
    }

    //Segundos Construtor encadenamiento de construtores
    public Usuario(int usuarioID, String nombre, String apellido1, String password,
                   String rol) {
        this(usuarioID, nombre, apellido1, null, null, null, password, rol);

    }

    //GETTERS
    public int getUsuarioId(){ return usuarioID; }
    public String getNombre(){ return nombre; }
    public String getApellido1(){ return apellido1; }
    public String getApellido2(){ return apellido2; }
    public String getPassword(){ return password; }
    public String getTelefono(){ return telefono; }
    public String getEmail(){ return email; }
    public String getRol(){ return rol; }


    public abstract List<Producto> verProducto(Inventario inventario);
    public abstract Producto buscarProductoPorID(Inventario inventario, int id);
    public abstract List<Producto> buscarProductoPorNombre(Inventario inventario, String nombre);
    public abstract void generarVenta();

    //Interfaz Proveedores
    public abstract  List<Proveedor> verProveedores(GestorProveedores gestor);
    public abstract Proveedor buscarProveedorPorId(GestorProveedores gestor, int id);
    public abstract  List<Proveedor>  buscarProveedorPorNombre(GestorProveedores gestor, String nombre);
    //-------------------------------------------------------------------------------------
    //Metodos Registringidos (para Administrador)
    public abstract void agregarProducto(Inventario inventario, Producto producto);
    public abstract boolean eliminarProducto(Inventario inventario, int id);
    public abstract boolean editarProducto(Inventario inventario, int id, String nuevoNombre, double nuevoPrecio, int nuevoStock, String nuevaCategoria, String nuevoProveedor);    //Interfaz Proveedores
    public abstract void agregarProveedor(GestorProveedores gestor, Proveedor proveedor);
    public abstract boolean eliminarProveedor(GestorProveedores gestor, int id);
    public abstract boolean editarProveedor(GestorProveedores gestor, int id, String nombre, String contacto, String telefono, String email);


}
