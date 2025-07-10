package com.usuario.service.usuario_service.modelos;

import java.time.LocalDate;

public class Comprobante {
    private int comprobanteId;
    private int idPedido;
    private int usuarioId;
    private LocalDate fechaPedido;
    private String nombreUsuario;
    private int idProducto;
    private String nombreProducto;
    private String marcaProducto;
    private int precioProducto;
    
    public int getComprobanteId() {
        return comprobanteId;
    }
    public void setComprobanteId(int comprobanteId) {
        this.comprobanteId = comprobanteId;
    }
    public int getIdPedido() {
        return idPedido;
    }
    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }
    public int getUsuarioId() {
        return usuarioId;
    }
    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }
    public LocalDate getFechaPedido() {
        return fechaPedido;
    }
    public void setFechaPedido(LocalDate fechaPedido) {
        this.fechaPedido = fechaPedido;
    }
    public String getNombreUsuario() {
        return nombreUsuario;
    }
    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }
    public int getIdProducto() {
        return idProducto;
    }
    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }
    public String getNombreProducto() {
        return nombreProducto;
    }
    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }
    public String getMarcaProducto() {
        return marcaProducto;
    }
    public void setMarcaProducto(String marcaProducto) {
        this.marcaProducto = marcaProducto;
    }
    public int getPrecioProducto() {
        return precioProducto;
    }
    public void setPrecioProducto(int precioProducto) {
        this.precioProducto = precioProducto;
    }
}
