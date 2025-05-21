package Servicios;

import Modelo.Proveedor;
import java.util.ArrayList;

public class ProveedorServicos {

    private ArrayList<Proveedor> listaProveedores;

    public ProveedorServicos() {
        listaProveedores = new ArrayList<>();
    }

    public void setListaProveedores(ArrayList<Proveedor> proveedores) {
        this.listaProveedores = proveedores;
    }

    public ArrayList<Proveedor> getListaProveedores() {
        return listaProveedores;
    }

    public void agregarProveedor(Proveedor proveedor) {
        proveedor.setProveedorId(generarNuevoId());
        for (Proveedor p : listaProveedores) {
            if (p.getProveedorId() == proveedor.getProveedorId()) {
                throw new IllegalArgumentException("Ya existe un proveedor con el ID " + proveedor.getProveedorId());
            }
        }
        listaProveedores.add(proveedor);
    }

    public void eliminarProveedor(int id) {
        Proveedor proveedorEliminar = null;
        for (Proveedor p : listaProveedores) {
            if (p.getProveedorId() == id) {
                proveedorEliminar = p;
                break;
            }
        }
        if (proveedorEliminar != null) {
            listaProveedores.remove(proveedorEliminar);
        }
    }

    public void editarProveedor(int id, String nuevoNombre, String nuevoContacto, String nuevoTelefono, String nuevoEmail) {
        for (Proveedor p : listaProveedores) {
            if (p.getProveedorId() == id) {
                p.setNombre(nuevoNombre);
                p.setContacto(nuevoContacto);
                p.setTelefono(nuevoTelefono);
                p.setEmail(nuevoEmail);
                return;
            }
        }
    }

    public Proveedor buscarPorId(int id) {
        for (Proveedor p : listaProveedores) {
            if (p.getProveedorId() == id) {
                return p;
            }
        }
        return null;
    }

    public Proveedor buscarPorNombre(String nombre) {
        for (Proveedor p : listaProveedores) {
            if (p.getNombre().equalsIgnoreCase(nombre)) {
                return p;
            }
        }
        return null;
    }

    private int generarNuevoId() {
        int maxId = 0;
        for (Proveedor p : listaProveedores) {
            if (p.getProveedorId() > maxId) {
                maxId = p.getProveedorId();
            }
        }
        return maxId + 1;
    }
}
