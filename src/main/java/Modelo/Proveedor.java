package Modelo;

public class Proveedor {


    private int proveedorId;
    private String nombre;
    private String contacto;
    private String telefono;
    private String email;

    public Proveedor(int proveedorId, String nombre, String contacto, String telefono, String email) {
        this.proveedorId = proveedorId;
        this.nombre = nombre;
        this.contacto = contacto;
        this.telefono = telefono;
        this.email = email;
    }

    public Proveedor(int proveedorId, String nombre, String contacto) {
        this(proveedorId, nombre, contacto, null, null);
    }

    // Getters
    public int getProveedorId() { return proveedorId; }
    public String getNombre() { return nombre; }
    public String getContacto() { return contacto; }
    public String getTelefono() { return telefono; }
    public String getEmail() { return email; }

    // Setters
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setContacto(String contacto) { this.contacto = contacto; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    public void setEmail(String email) { this.email = email; }
    public void setProveedorId(int proveedorId) { this.proveedorId = proveedorId; }

}


