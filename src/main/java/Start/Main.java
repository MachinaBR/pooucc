package Start;
import Interfaz.LoginInterfaz;
import Modelo.*;
import Servicios.Inventario;
import Servicios.ProveedorServicos;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        ArrayList<Usuario> empleados = new ArrayList<>();
        empleados.add(new Owner(1, "Jhonny","Romero", "Barreto", "3205525486", "jhonnybarreto102@gmail.com", "admin123", "admin" ));
        empleados.add(new Owner(2, "Juan", "Gomes","juan123","admin"));
        empleados.add(new Vendedor(3, "Felipe", "Garcia","vendedor123","Vendedor"));
        empleados.add(new Vendedor(4, "sofia","barrero", "gomes", "3205525486", "jhonnybarreto102@gmail.com", "admin12", "Vendedor"));

        ProveedorServicos proveedorServicos = new ProveedorServicos();
        ArrayList<Proveedor> proveedores = new ArrayList<>();
        proveedorServicos.setListaProveedores(proveedores);
        proveedores.add(new Proveedor(1, "Distribuidora CampoSur", "Laura Mendoza", "3104567890", "ventas@camposur.com"));
        proveedores.add(new Proveedor(2, "Frutas y Verduras Los Andes", "Carlos Ríos", "3117896543", "andinafruver@losandes.com"));
        proveedores.add(new Proveedor(3, "Lácteos Colanta", "Andrea Salazar", "3001234567", "contacto@colanta.com"));
        proveedores.add(new Proveedor(4, "Carnes el Llanero", "Oscar Tovar", "3125556677", "el.lanero@carnes.com"));
        proveedores.add(new Proveedor(5, "Granos y Cereales La Abundancia", "Marcela Díaz"));

        Inventario inventario = new Inventario();
        ArrayList<Producto> productos = new ArrayList<>();
        productos.add(new Producto(1, "Arroz Diana", 3200, 100, "Granos", "Distribuidora CampoSur"));
        productos.add(new Producto(2, "Frijol Rojo", 4200, 80, "Granos", "Granos y Cereales La Abundancia"));
        productos.add(new Producto(3, "Leche Entera", 2800, 120, "Lácteos", "Lácteos Colanta"));
        productos.add(new Producto(4, "Tomate Chonto", 1800, 60, "Verduras", "Frutas y Verduras Los Andes"));
        productos.add(new Producto(5, "Carne de Res x kg", 14500, 30, "Carnes", "Carnes el Llanero"));
        inventario.setListaProductos(productos);

        //Menu Login
        new LoginInterfaz(empleados, inventario, proveedorServicos);    }
}