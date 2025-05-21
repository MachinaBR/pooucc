package Modelo;

public class Owner extends  Usuario {

    public Owner(int usuarioID, String nombre, String apellido1, String apellido2,
                 String telefono,String email,String Password, String rol){
        super(usuarioID,nombre,apellido1,apellido2,telefono,email,Password,rol);
    }

    public Owner(int usuarioID, String nombre, String apellido1, String password, String rol) {
        super(usuarioID, nombre, apellido1, password, rol);
    }

    // Implementación de métodos abstractos

    @Override
    public void verProducto() {
        System.out.println("El dueño puede ver todos los productos del inventario.");
    }

    @Override
    public void verProveedores() {
        System.out.println("El dueño puede ver y gestionar todos los proveedores.");
    }

    @Override
    public void consultarProducto() {
        System.out.println("El dueño puede buscar cualquier producto del sistema.");
    }

    @Override
    public void verResumenVentas() {
        System.out.println("El dueño puede ver el resumen completo de todas las ventas.");
    }

}
