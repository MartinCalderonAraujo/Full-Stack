package com.usuario.service.usuario_service.modelos;

import java.time.LocalDate;

public class Pedido {
    private int idPedido;
    private int idCuentaCliente; 
    private String estadoPedido;
    private Double totalPedido;
    private LocalDate fechaPedido;
    
    public Pedido() {
    }

    public int getIdPedido() {
        return idPedido;
    }
    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }
    public int getIdCuentaCliente() {
        return idCuentaCliente;
    }
    public void setIdCuentaCliente(int idCuentaCliente) {
        this.idCuentaCliente = idCuentaCliente;
    }
    public String getEstadoPedido() {
        return estadoPedido;
    }
    public void setEstadoPedido(String estadoPedido) {
        this.estadoPedido = estadoPedido;
    }
    public Double getTotalPedido() {
        return totalPedido;
    }
    public void setTotalPedido(Double totalPedido) {
        this.totalPedido = totalPedido;
    }
    public LocalDate getFechaPedido() {
        return fechaPedido;
    }
    public void setFechaPedido(LocalDate fechaPedido) {
        this.fechaPedido = fechaPedido;
    }


}
