package Servicios;

import Modelo.Proveedor;
import java.util.ArrayList;
import java.util.List;

public class GestorProveedores {

    private List<Proveedor> listaProveedores;

    public GestorProveedores() {
        listaProveedores = new ArrayList<>();
    }

    public List<Proveedor> getListaProveedores() {
        return listaProveedores;
    }

    public void agregarProveedor(Proveedor proveedor) {
        listaProveedores.add(proveedor);
    }

    public Proveedor buscarProveedorPorId(int id) {
        for (Proveedor p : listaProveedores) {
            if (p.getProveedorId() == id) {
                return p;
            }
        }
        return null;
    }

    public List<Proveedor> buscarProveedorPorNombre(String nombre) {
        List<Proveedor> resultados = new ArrayList<>();
        for (Proveedor p : listaProveedores) {
            if (p.getNombre().toLowerCase().contains(nombre.toLowerCase())) {
                resultados.add(p);
            }
        }
        return resultados;
    }

    public boolean editarProveedor(int id, String nombre, String contacto, String telefono, String email) {
        Proveedor p = buscarProveedorPorId(id);
        if (p != null) {
            p.setNombre(nombre);
            p.setContacto(contacto);
            p.setTelefono(telefono);
            p.setEmail(email);
            return true;
        }
        return false;
    }

    public boolean eliminarProveedor(int id) {
        Proveedor p = buscarProveedorPorId(id);
        if (p != null) {
            listaProveedores.remove(p);
            return true;
        }
        return false;
    }

    public int generarNuevoId() {
        int maxId = 0;
        for (Proveedor p : listaProveedores) {
            if (p.getProveedorId() > maxId) {
                maxId = p.getProveedorId();
            }
        }
        return maxId + 1;
    }

}
