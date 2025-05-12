package modelo;

public class Producto {
    //Atributos del Producto
    private int producto_id;
    private String nombre;
    private double precio;
    private int stock;
    private String categoria;
    private Proveedor proveedor;

    //CONTRUSTOR VACIO
    public Producto() {}

    //contrutor: se usa para crear un ojbeto usuario
    public Producto(int producto_id, String nombre, double precio, int stock, String categoria, Proveedor proveedor) {
        this.producto_id = producto_id;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
        this.categoria = categoria;
        this.proveedor = proveedor;
    }

    //GETTERS
    public int getProducto_id() {return producto_id;}

    public String getNombre() {return nombre;}

    public double getPrecio() {return precio;}

    public int getStock() {return stock;}

    public String getCategoria() {return categoria;}

    public Proveedor getProveedor() {return proveedor;}

    //SETTERS

    public void setProducto_id(int producto_id) {this.producto_id = producto_id;}

    public void setNombre(String nombre) {this.nombre = nombre;}

    public void setPrecio(double precio) {this.precio = precio;}

    public void setStock(int stock) {this.stock = stock;}

    public void setCategoria(String categoria) {this.categoria = categoria;}

    public void setProveedor(Proveedor proveedor) {this.proveedor = proveedor;}

    @Override
    public String toString() {
        return "ID: " + producto_id +
                " | Nombre: " + nombre +
                " | Precio: $" + precio +
                " | Stock: " + stock +
                " | Categoría: " + categoria +
                " | Proveedor: " + (proveedor != null ? proveedor.getProveedor_name() : "N/A");
    }


}
