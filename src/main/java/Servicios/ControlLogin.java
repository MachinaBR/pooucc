package Servicios;

import Modelo.Usuario;
import java.util.List;

public class ControlLogin {

    private List<Usuario> listaUsuarios;

    public ControlLogin(List<Usuario> usuarios) {
        this.listaUsuarios = usuarios;
    }

    public Usuario iniciarSesion(String nombre, String password) {
        for (Usuario u : listaUsuarios) {
            if (u.getNombre().equalsIgnoreCase(nombre) && u.getPassword().equals(password)) {
                return u;
            }
        }
        return null;
    }
}