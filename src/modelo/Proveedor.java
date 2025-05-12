package modelo;

public class Proveedor {

    private int Proveedor_id;
    private String Proveedor_name;
    private String Proveedor_email;
    private String Proveedor_telefono;
    private String Proveedor_direccion;

    public Proveedor() {
    }

    public Proveedor(int proveedor_id, String proveedor_name, String proveedor_email,
                     String proveedor_telefono, String proveedor_direccion) {

        this.Proveedor_id = proveedor_id;
        this.Proveedor_name = proveedor_name;
        this.Proveedor_email = proveedor_email;
        this.Proveedor_telefono = proveedor_telefono;
        this.Proveedor_direccion = proveedor_direccion;
    }

    //Getters

    public int getProveedor_id() {return Proveedor_id;}

    public String getProveedor_name() {return Proveedor_name;}

    public String getProveedor_email() {return Proveedor_email;}

    public String getProveedor_telefono() {return Proveedor_telefono;}

    public String getProveedor_direccion() {return Proveedor_direccion;}

    //Setters

    public void setProveedor_id(int proveedor_id) {this.Proveedor_id = proveedor_id;}

    public void setProveedor_name(String proveedor_name) {this.Proveedor_name = proveedor_name;}

    public void setProveedor_email(String proveedor_email) {Proveedor_email = proveedor_email;}

    public void setProveedor_telefono(String proveedor_telefono) {Proveedor_telefono = proveedor_telefono;}

    public void setProveedor_direccion(String proveedor_direccion) {Proveedor_direccion = proveedor_direccion;}
}

