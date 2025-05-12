package servicio;

import Usuarios.Usuario;

public class ControlLogin {

    //LOGICA PARA EL INICIO DE SESION AL SISTEMA, SIN SEGRUIDAD DE ENCRIPTACION..
    public static boolean iniciarSesion(Usuario usuario, String nombre,
                                        String contrasena) {
        if (usuario.getNombre().equals(nombre) && usuario.getContrasena().equals(contrasena)) {
            System.out.println("Sesion Iniciado Correctamente");
            return true;
        } else {
            System.out.println("Credenciales Incorrectamente");
            return false;
        }
    }

    //LOGICA PARA CAMBIAR CONTRASENA DEL USUARIO
    public void cambiarContrasena(Usuario usuario, String nuevaContrasena) {
        usuario.setContrasena(nuevaContrasena);
        System.out.println("Contrasena Actualizado Correctamente");
    }
    //LOGICA PARA ACTUALIZAR EL NOMBRE
    public void cambiarNombre(Usuario usuario, String nuevonombre) {
        usuario.setNombre(nuevonombre);
        System.out.printf("Nombre Actualizado Correctamente");
    }
    //LOGICA PARA CAMBIAR  EL TELEFONO
    public void actualizarTelefono(Usuario usuario, String telefono) {
        usuario.setTelefono(telefono);
        System.out.printf("Telefono Actualizado Correctamente");
    }

}



