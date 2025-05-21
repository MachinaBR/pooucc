package Modelo;

public class Producto {

    private int productoId;
    private String nombre;
    private double precio;
    private int stock;
    private String categoria;
    private String proveedor;

    public Producto(int productoId, String nombre, double precio, int stock, String categoria, String proveedor) {
        this.productoId = productoId;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
        this.categoria = categoria;
        this.proveedor = proveedor;
    }

    public Producto(int productoId, String nombre, double precio, int stock) {
        this(productoId, nombre, precio, stock, null, null);
    }

    public int getProductoId() { return productoId; }
    public String getNombre() { return nombre; }
    public double getPrecio() { return precio; }
    public int getStock() { return stock; }
    public String getCategoria() { return categoria; }
    public String getProveedor() { return proveedor; }

    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setPrecio(double precio) { this.precio = precio; }
    public void setStock(int stock) { this.stock = stock; }
    public void setCategoria(String categoria) { this.categoria = categoria; }
    public void setProveedor(String proveedor) { this.proveedor = proveedor; }
    public void setId(int id) {this.productoId = id;}

}
