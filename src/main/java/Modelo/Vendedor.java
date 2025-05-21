package Modelo;

public class Vendedor extends Usuario{

        public Vendedor (int usuarioId, String nombre, String apellido1, String apellido2, String telefono, String email, String password, String rol) {
            super(usuarioId,nombre,apellido1,apellido2,password,telefono,email,rol);
        }

        public Vendedor(int usuarioID, String nombre, String apellido1, String Password, String rol){
            super(usuarioID, nombre, apellido1, Password, rol);
        }

    @Override
    public void verProducto() {
        System.out.println("Vendedor puede ver productos disponibles para venta.");

    }

    @Override
    public void verProveedores() {
        System.out.println("Vendedor no tiene acceso para ver proveedores..");

    }

    @Override
    public void consultarProducto() {
        System.out.println("Vendedor puede buscar productos por nombre o código.");

    }

    @Override
    public void verResumenVentas() {
        System.out.println("Vendedor puede ver su resumen de ventas personales");

    }

}
