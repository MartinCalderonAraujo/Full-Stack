package com.boleta.service.boleta_service.modelos;
import java.time.LocalDate;
public class Pedido {

    private int idPedido;
    private int idCuentacliente;
    private String estadoPedido;
    private double totalPedido;
    private LocalDate fechaPedido;


    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public int getIdCuentacliente() {
        return idCuentacliente;
    }

    public void setIdCuentacliente(int idCuentacliente) {
        this.idCuentacliente = idCuentacliente;
    }

    public String getEstadoPedido() {
        return estadoPedido;
    }

    public void setEstadoPedido(String estadoPedido) {
        this.estadoPedido = estadoPedido;
    }

    public double getTotalPedido() {
        return totalPedido;
    }

    public void setTotalPedido(double totalPedido) {
        this.totalPedido = totalPedido;
    }

    public LocalDate getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(LocalDate fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    public Pedido() {
    }


}
