package Modelo;

public class ItemVenta {
    private int productoID;
    private String nombre;
    private double precioUnitario;
    private int cantidad;

    public ItemVenta(int productoID, String nombre, double precioUnitario, int cantidad) {
        this.productoID = productoID;
        this.nombre = nombre;
        this.precioUnitario = precioUnitario;
        this.cantidad = cantidad;
    }

    // ✅ GETTERS
    public int getProductoID() {
        return productoID;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public int getCantidad() {
        return cantidad;
    }

    public double getSubtotal() {
        return precioUnitario * cantidad;
    }

    // ✅ SETTERS (opcional)
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }
}