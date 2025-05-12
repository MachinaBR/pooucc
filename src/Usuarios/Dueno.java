package Usuarios;
import servicio.Inventario;

public class Dueno  extends Usuario {

        //ATRIBUTOS HEREDADOS POR LA CLASE PADRE
        public Dueno(int usuario_id, String nombre, String apellido1, String apellido2, String contrasena,
                     String telefono) {
            super(usuario_id, nombre, apellido1, apellido2, contrasena, telefono);
            this.rol = "dueno";
        }

        //GESTION DE PRODUCTOS
        public void gestionarProductos(Inventario inventario) {
            System.out.println("Gestionando productos del inventario...");
        }
        //GESTION DE VENTAS
        public void gestionarVentas() {
            System.out.println("fRevisando reporte de ventas...");
        }
        //GESTION DE PROVEEDORES
        public void gestionarProveedores() {
            System.out.println("Gestionando proveedores...");
        }
        //NOTIFICACIONE
        public void notificacion() {
            System.out.println("Enviando notificación al dueño...");
        }

}

