package modelo;

public class Proveedor {

    private int proveedor_id;
    private String proveedor_name;
    private String proveedor_email;
    private String proveedor_telefono;
    private String proveedor_direccion;

    public Proveedor() {
    }

    public Proveedor(int proveedor_id, String proveedor_name, String proveedor_email,
                     String proveedor_telefono, String proveedor_direccion) {

        this.proveedor_id = proveedor_id;
        this.proveedor_name = proveedor_name;
        this.proveedor_email = proveedor_email;
        this.proveedor_telefono = proveedor_telefono;
        this.proveedor_direccion = proveedor_direccion;
    }

    //Getters

    public int getProveedor_id() {return proveedor_id;}

    public String getProveedor_name() {return proveedor_name;}

    public String getProveedor_email() {return proveedor_email;}

    public String getProveedor_telefono() {return proveedor_telefono;}

    public String getProveedor_direccion() {return proveedor_direccion;}

    //Setters

    public void setProveedor_id(int proveedor_id) {this.proveedor_id = proveedor_id;}

    public void setProveedor_name(String proveedor_name) {this.proveedor_name = proveedor_name;}

    public void setProveedor_email(String proveedor_email) {proveedor_email = proveedor_email;}

    public void setProveedor_telefono(String proveedor_telefono) {proveedor_telefono = proveedor_telefono;}

    public void setProveedor_direccion(String proveedor_direccion) {proveedor_direccion = proveedor_direccion;}
}

