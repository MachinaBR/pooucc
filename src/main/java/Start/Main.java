package Start;

import Interfaz.LoginInterfaz;
import Interfaz.MenuAdministrador;
import Modelo.*;
import Servicios.GestorProveedores;
import Servicios.GestorVentas;
import Servicios.Inventario;

import java.util.ArrayList;

public class Main {
    public static ArrayList<Usuario> empleados = new ArrayList<>();
    public static GestorVentas ventas = new GestorVentas();
    public static GestorProveedores proveedores;

    public static void main(String[] args) {
        Inventario inventario = new Inventario();
        GestorProveedores proveedores = new GestorProveedores();

        //Cargar usuarios de prueba
        Administrador admin = new Administrador(1, "Carlos", "Gómez", "López", "3015551234", "admin@tienda.com", "admin123", "ADMIN") {};
        Vendedor vendedor = new Vendedor(2, "Ana", "Martínez", "Ramírez", "3014445678", "vendedor@tienda.com", "venta123", "VENDEDOR") {};

        empleados.add(admin);
        empleados.add(vendedor);

       //Cargar productos de prueba
        inventario.agregarProducto(new Producto(1, "Arroz Diana", 2800.0, 100, "Alimentos", "Distribuciones Bogotá"));
        inventario.agregarProducto(new Producto(2, "Arroz Roa", 3000.0, 80, "Alimentos", "Roa S.A."));
        inventario.agregarProducto(new Producto(3, "Arroz con Leche", 3500.0, 40, "Postres", "Dulces Bogotá"));
        inventario.agregarProducto(new Producto(4, "Jabón Rey", 1500.0, 50, "Aseo", "Colombiana de Limpieza"));
        inventario.agregarProducto(new Producto(5, "Jabón Protex", 2700.0, 30, "Aseo", "Protex Corp."));
        inventario.agregarProducto(new Producto(6, "Gaseosa Coca-Cola", 3500.0, 70, "Bebidas", "CocaCola FEMSA"));
        inventario.agregarProducto(new Producto(7, "Gaseosa Postobón", 3200.0, 65, "Bebidas", "Postobón S.A."));
        inventario.agregarProducto(new Producto(8, "Gaseosa Pepsi", 3400.0, 45, "Bebidas", "PepsiCo"));
        inventario.agregarProducto(new Producto(9, "Empanada", 1800.0, 200, "Comida rápida", "TiendaCoco S.A."));
        inventario.agregarProducto(new Producto(10, "Empanada de Pollo", 2000.0, 180, "Comida rápida", "TiendaCoco S.A."));
        //Cargar proveedores de prueba
        proveedores.agregarProveedor(new Proveedor(1, "Distribuciones Bogotá", "Luis Gómez", "3105558888", "contacto@distribucionesbogota.com"));
        proveedores.agregarProveedor(new Proveedor(2, "Roa S.A.", "Sandra Roa", "3104447777", "s.roa@roasa.com"));
        proveedores.agregarProveedor(new Proveedor(3, "Dulces Bogotá", "Marcela Dulce", "3001112222", "info@dulcesbogota.com"));
        proveedores.agregarProveedor(new Proveedor(4, "Colombiana de Limpieza", "Diana Torres", "3018889999", "ventas@colimpieza.com"));
        proveedores.agregarProveedor(new Proveedor(5, "Protex Corp.", "Carlos Ramírez", "3135556677", "contacto@protex.com"));
        proveedores.agregarProveedor(new Proveedor(6, "Postobón S.A.", "Laura Coca", "3102223344", "ventas@cocacola.com"));
        proveedores.agregarProveedor(new Proveedor(7, "Postobón S.A.", "Pedro Postobón", "3129991122", "pp@postobon.com"));
        proveedores.agregarProveedor(new Proveedor(8, "PepsiCo", "Andrea Pepsi", "3141234567", "contact@pepsico.com"));
        proveedores.agregarProveedor(new Proveedor(9, "TiendaCoco S.A.", "Juan Pérez", "3110001122", "admin@tiendacoco.com"));
        proveedores.agregarProveedor(new Proveedor(10, "TiendaCoco S.A.", "Natalia Gómez", "3110001133", "natalia@tiendacoco.com")); // nombre repetido


        // Iniciar sistema
        new LoginInterfaz(empleados, inventario, proveedores);        // O si quieres ir directamente al panel del admin (opcional para pruebas)
        // new MenuAdministrador(admin, inventario, proveedores);
    }
}