package Modelo;

import java.time.LocalDate;
import java.util.List;

public class Venta {

    private int ventaID;
    private int vendedorID;
    private List<ItemVenta> items;
    private LocalDate fecha;
    private double total;

    public Venta(int ventaID, int vendedorID, List<ItemVenta> items, double total, LocalDate fecha) {
        this.ventaID = ventaID;
        this.vendedorID = vendedorID;
        this.items = items;
        this.total = total;
        this.fecha = fecha;
    }

    // Setters
    public void setVentaID(int ventaID) {
        this.ventaID = ventaID;
    }

    // Getters
    public int getVentaID() {
        return ventaID;
    }

    public int getVendedorID() {
        return vendedorID;
    }

    public List<ItemVenta> getItems() {
        return items;
    }

    public double getTotal() {
        return total;
    }

    public LocalDate getFecha() {
        return fecha;
    }
}
