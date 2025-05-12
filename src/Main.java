import Usuarios.Dueno;
import Usuarios.Usuario;
import Usuarios.Vendedor;
import modelo.Producto;
import modelo.Proveedor;
import servicio.ControlLogin;
import servicio.Inventario;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        // Lista de Usuarios con su rol..
        ArrayList<Usuario> usuarios = new ArrayList<>();
        usuarios.add(new Dueno(1, "admin", "Admin", "Root", "1234", "3100000"));
        usuarios.add(new Vendedor(2, "juan", "Luis", "Martínez", "Perez", "1234"));

        Scanner sc = new Scanner(System.in);
        Usuario usuarioEncontrado = null;
        boolean opcion = false;


        while (usuarios == null || !opcion) {
            System.out.printf("ingrese su nombre de usuario: ");
            String nombreIngresado = sc.nextLine();
            System.out.printf("ingrese su contrasena de usuario: ");
            String ContrasenaIngresada = sc.nextLine();

            for (Usuario u : usuarios) {
                if (u.getContrasena().equals(ContrasenaIngresada) && u.getNombre().equals(nombreIngresado)) {
                    usuarioEncontrado = u;
                    break;
                }
            }

            if (usuarioEncontrado == null) {
                System.out.println("Usuario no encontrado");
            } else {
                opcion = ControlLogin.iniciarSesion(usuarioEncontrado, nombreIngresado, ContrasenaIngresada);
            }

        }

        System.out.println(" Bienvenido " + usuarioEncontrado.getNombre() + " (Rol: " + usuarioEncontrado.getRol() + ")");

        Boolean salir = false;
        Inventario inventario = new Inventario();
        if (usuarioEncontrado instanceof Dueno dueno) {
            menuDueno(dueno, inventario, sc);
        }else if (usuarioEncontrado instanceof Vendedor vendedor) {
            menuVendedor(vendedor);
        }
    }

    //prueba de logica....
    public static void menuDueno(Dueno dueno, Inventario inventario, Scanner sc) {
        boolean salir = false;
        while (!salir) {
            System.out.println("\n--- MENÚ DUEÑO ---");
            System.out.println("1. Gestionar productos");
            System.out.println("2. Gestionar ventas");
            System.out.println("3. Gestionar proveedores");
            System.out.println("4. Ver notificaciones");
            System.out.println("5. Ver resumen de ventas");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            int opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1 -> gestionarProductos(inventario, sc);
                case 0 -> {
                    salir = true;
                    System.out.println("Cerrando sesión...");
                }
                default -> System.out.println("Opción no válida.");
            }
        }
    }

    public static void gestionarProductos(Inventario inventario, Scanner sc) {
        boolean volver = false;
        while (!volver) {
            System.out.println("\n--- GESTIÓN DE PRODUCTOS ---");
            System.out.println("1. Agregar producto");
            System.out.println("2. Eliminar producto");
            System.out.println("3. Ver productos");
            System.out.println("0. Volver");
            System.out.print("Opción: ");
            int op = sc.nextInt();
            sc.nextLine();

            switch (op) {
                case 1 -> {
                    System.out.print("ID: ");
                    int id = sc.nextInt(); sc.nextLine();
                    System.out.print("Nombre: ");
                    String nombre = sc.nextLine();
                    System.out.print("Precio: ");
                    double precio = sc.nextDouble();
                    System.out.print("Stock: ");
                    int stock = sc.nextInt(); sc.nextLine();
                    System.out.print("Categoría: ");
                    String categoria = sc.nextLine();

                    Proveedor prov = new Proveedor(0, "Genérico", "correo@ejemplo.com", "000", "Dirección");
                    Producto nuevo = new Producto(id, nombre, precio, stock, categoria, prov);

                    if (inventario.agregarProducto(nuevo)) {
                        System.out.println("Producto agregado.");
                    } else {
                        System.out.println("Ya existe un producto con ese ID.");
                    }
                }
                case 2 -> {
                    System.out.print("ID del producto a eliminar: ");
                    int id = sc.nextInt();
                    if (inventario.eliminarProducto(id)) {
                        System.out.println("🗑Producto eliminado.");
                    } else {
                        System.out.println(" Producto no encontrado.");
                    }
                }
                case 3 -> {
                    System.out.println("\n📦 Productos:");
                    for (Producto p : inventario.getProductos()) {
                        System.out.println(p);
                    }
                }
                case 0 -> volver = true;
                default -> System.out.println("Opción inválida");
            }
        }
    }

    public static void menuVendedor(Vendedor vendedor) {
        boolean salir = false;
        while (!salir) {
            System.out.println("\n--- MENÚ VENDEDOR ---");
            System.out.println("1. Ver productos");
            System.out.println("2. Registrar venta");
            System.out.println("3. Ver notificaciones");
            System.out.println("0. Salir");
            // Aquí podrías agregar lógica específica para cada opción
            salir = true; // solo para salir del bucle por ahora
        }
    }
}