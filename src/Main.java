import interfaz.LoginInterfaz;
import Usuarios.*;
import interfaz.MenuPrincipal;
import servicio.Inventario;
import interfaz.MenuProductos;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        //ARRAY LIST USUARIOS
        List<Usuario> usuarios = new ArrayList<>();
        usuarios.add(new Dueno(1, "admin", "Admin", "Root", "1234", "3001111111"));
        usuarios.add(new Vendedor(2, "juan", "Luis", "Perez", "1234", "3434322"));

        LoginInterfaz login = new LoginInterfaz(usuarios);

        // SISTEMA DE LOGIN
        while (login.isVisible()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        Usuario usuarioActivo = login.getUsuarioLogueado();

        // MENU PRINCIPAL
        if (usuarioActivo != null) {
            Inventario inventario = new Inventario();
            new MenuPrincipal(inventario);
        }


    }
}