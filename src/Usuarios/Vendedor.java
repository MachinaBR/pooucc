package Usuarios;

public class Vendedor extends Usuario {

    public Vendedor(int usuario_id, String nombre, String apellido1, String apellido2, String contrasena,
                 String telefono) {
        super(usuario_id, nombre, apellido1, apellido2, contrasena, telefono);
        this.rol = "Vendedor";
    }

    //No es necesario agregar metodos  para evitar renundancias logicas...
}
