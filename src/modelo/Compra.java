package modelo;

import Usuarios.Usuario;

import java.util.Date;

public class Compra {

    private int comprador_id;
    private Date fechas;
    private String estado;
    private double precio;
    private Usuario usuario; // quien realiza la compra
    private List<CompraProducto> productos;
}
