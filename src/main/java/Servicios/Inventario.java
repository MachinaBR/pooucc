package Servicios;

import Modelo.Producto;
import java.util.ArrayList;
import java.util.List;
import Modelo.Producto;

public class Inventario {

    private List<Producto> listaProductos;

    // Construtor
    public Inventario() {
        listaProductos = new ArrayList<>();
    }

    //Metodo para aceender a los datos de forma segura
    public List<Producto> getListaProductos() {
        return listaProductos;
    }

    public void agregarProducto(Producto recibeProducto) {
        listaProductos.add(recibeProducto);
    }

    public Producto buscarProductoPorID(int idbusqueda){
        for (Producto buscarproducto : listaProductos) {
            if(buscarproducto.getProductoId() == idbusqueda){
                return buscarproducto;
            }
        }
        return null;
    }

    public List<Producto> buscarProductosPorNombre(String buscarNombre){
        List<Producto> resultados = new ArrayList<>();

        for (Producto p : listaProductos) {
            if(p.getNombre().toLowerCase().contains(buscarNombre.toLowerCase())){
                resultados.add(p);
            }
        }
        return resultados;
    }

    public boolean eliminarProducto(int id){
        for (Producto p : listaProductos) {
            if(p.getProductoId() == id){
                listaProductos.remove(p);
                return true;
            }
        }
        return false;
    }

    public boolean editarProducto(int id, String nuevoNombre,double nuevaPrecio, int nuevaCantidad, String nuevaCategoria, String nuevoProveedor){
        for(Producto p : listaProductos){
            if(p.getProductoId() == id){
                p.setNombre(nuevoNombre);
                p.setPrecio(nuevaPrecio);
                p.setStock(nuevaCantidad);
                p.setCategoria(nuevaCategoria);
                p.setProveedor(nuevoProveedor);
                return true;
            }
        }return false;

    }

    public int generarIdAuto() {
        int maxId = 0;
        for (Producto p : listaProductos) {
            if (p.getProductoId() > maxId) {
                maxId = p.getProductoId();
            }
        }
        return maxId + 1;
    }

}
