package acciones;

import Usuarios.Usuario;

public class AccionUsuario {

    public void verProveedores(Usuario usuario) {
        System.out.println(usuario.getNombre() + " está viendo la lista de proveedores...");
    }

    public void gestionarFacturas(Usuario usuario) {
        System.out.println(usuario.getNombre() + " está gestionando facturas...");
    }

    public void verNotificaciones(Usuario usuario) {
        System.out.println("Notificaciones nuevas para " + usuario.getNombre());
    }

    public void verProductos(Usuario usuario) {
        System.out.println(usuario.getNombre() + " está visualizando productos del sistema...");
    }

    public void verResumenVentas(Usuario usuario) {
        System.out.println(usuario.getNombre() + " está revisando el resumen de ventas...");
    }
}

