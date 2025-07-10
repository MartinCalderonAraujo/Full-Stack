package com.usuario.service.usuario_service.modelos;

public class Boleta {
    private int boletaId;
    private String detallePedido;
    private int total;
    private int idPedido;
    private String nombre;
    private int usuarioId;

    
    public Boleta() {
    }
    
    public int getBoletaId() {
        return boletaId;
    }
    public void setBoletaId(int boletaId) {
        this.boletaId = boletaId;
    }
    public String getDetallePedido() {
        return detallePedido;
    }
    public void setDetallePedido(String detallePedido) {
        this.detallePedido = detallePedido;
    }
    public int getTotal() {
        return total;
    }
    public void setTotal(int total) {
        this.total = total;
    }
    public int getIdPedido() {
        return idPedido;
    }
    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public int getUsuarioId() {
        return usuarioId;
    }
    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }
}
