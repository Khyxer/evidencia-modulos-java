package modelo;

import java.time.LocalDateTime;

public class Venta {
    // Atributos - representan las columnas de la tabla ventas
    private int id;
    private int usuarioId;
    private LocalDateTime fecha;
    private double total;

    // Constructor vacío
    public Venta() {
    }

    // Constructor con parámetros
    public Venta(int usuarioId, double total) {
        this.usuarioId = usuarioId;
        this.total = total;
        this.fecha = LocalDateTime.now(); // Fecha actual
    }

    // Getters
    public int getId() {
        return id;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public double getTotal() {
        return total;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    // toString
    @Override
    public String toString() {
        return "Venta{" +
                "id=" + id +
                ", usuarioId=" + usuarioId +
                ", fecha=" + fecha +
                ", total=" + total +
                '}';
    }
}