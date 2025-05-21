package Servicios;
import Modelo.Usuario;
import java.util.ArrayList;

public class ControlLogin {

    // Lista de  los empleados
    private ArrayList<Usuario> empleados;

    //Construtor
    public ControlLogin(ArrayList<Usuario> empleados) {this.empleados = empleados;}

    //Validar nombre y contraseña
    public Usuario iniciarSesion(String nombre, String password) {
        for (Usuario u : empleados) {
            if (u.getNombre().equalsIgnoreCase(nombre) && u.getPassword().equals(password)) {
                return u;
            }
        }

        return null;
    }

}
