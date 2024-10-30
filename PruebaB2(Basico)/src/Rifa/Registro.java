package Rifa;

import java.util.List;

public class Registro {
    String nombre;
    String fecha;
    List<Compra> compras;

    public Registro(String nombre, String fecha, List<Compra> compras) {
        this.nombre = nombre;
        this.fecha = fecha;
        this.compras = compras;
    }
}