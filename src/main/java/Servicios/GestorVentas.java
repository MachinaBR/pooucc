package Servicios;

import Modelo.Venta;

import java.util.ArrayList;
import java.util.List;

public class GestorVentas {

    private final List<Venta> listaVentas;
    private int contadorVentas;

    public GestorVentas() {
        this.listaVentas = new ArrayList<>();
        this.contadorVentas = 1;
    }

    public void registrarVenta(Venta venta) {
        venta.setVentaID(contadorVentas);
        contadorVentas++;
        listaVentas.add(venta);
    }

    public List<Venta> obtenerVentasPorVendedor(int vendedorID) {
        List<Venta> resultado = new ArrayList<>();
        for (Venta v : listaVentas) {
            if (v.getVendedorID() == vendedorID) {
                resultado.add(v);
            }
        }
        return resultado;
    }

    public List<Venta> obtenerTodasLasVentas() {
        return new ArrayList<>(listaVentas);
    }

    public Venta buscarVentaPorID(int id) {
        for (Venta v : listaVentas) {
            if (v.getVentaID() == id) {
                return v;
            }
        }
        return null;
    }

    public Venta obtenerUltimaVenta() {
        if (listaVentas.isEmpty()) return null;
        return listaVentas.get(listaVentas.size() - 1);
    }
}
