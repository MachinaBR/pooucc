package Usuarios;

public class Usuario {

    //Atributos del usuario
    protected int usuario_id;
    protected String nombre;
    protected String apellido1;
    protected String apellido2;
    protected String contrasena;
    protected String telefono;
    protected String rol;

    //Construtor vacio
    public Usuario() {}

    //contrutor: se usa para crear un ojbeto usuario
    public Usuario(int usuario_id, String nombre, String apellido1, String apellido2, String contrasena,
                    String telefono) {

        this.usuario_id = usuario_id;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.contrasena = contrasena;
        this.telefono = telefono;

    }

    //Getters
    public int getUsuario_id() {return usuario_id;}

    public String getNombre(){return nombre;}

    public String getApellido1(){return apellido1;}

    public String getApellido2(){return apellido2;}

    public String getContrasena(){return contrasena;}

    public String getTelefono(){return telefono;}

    public String getRol(){return rol;}

    //setters
    public void setUsuario_id(int usuario_id) {this.usuario_id = usuario_id;}

    public void setNombre(String nombre) {this.nombre = nombre;}

    public void setApellido1(String apellido1) {this.apellido1 = apellido1;}

    public void setApellido2(String apellido2) {this.apellido2 = apellido2;}

    public void setContrasena(String contrasena) {this.contrasena = contrasena;}

    public void setTelefono(String telefono) {this.telefono = telefono;}
}
