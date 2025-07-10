package com.sucursal.service.sucursal_service.entidades;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Sucursal {
    private int idSucursal;
    private String nombreSucursal;
    private String direccionSucursal;
    private int usuarioId;
    private String usuarioNombre;
    private int comprobanteId;
    private int productoId;

    @Id

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    public int getIdSucursal() {
        return idSucursal;
    }
    public void setIdSucursal(int idSucursal) {
        this.idSucursal = idSucursal;
    }
    public String getNombreSucursal() {
        return nombreSucursal;
    }
    public void setNombreSucursal(String nombreSucursal) {
        this.nombreSucursal = nombreSucursal;
    }
    public String getDireccionSucursal() {
        return direccionSucursal;
    }
    public void setDireccionSucursal(String direccionSucursal) {
        this.direccionSucursal = direccionSucursal;
    }
    public int getUsuarioId() {
        return usuarioId;
    }
    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }
    public String getUsuarioNombre() {
        return usuarioNombre;
    }
    public void setUsuarioNombre(String usuarioNombre) {
        this.usuarioNombre = usuarioNombre;
    }
    public int getComprobanteId() {
        return comprobanteId;
    }
    public void setComprobanteId(int comprobanteId) {
        this.comprobanteId = comprobanteId;
    }
    public int getProductoId() {
        return productoId;
    }
    public void setProductoId(int idProducto) {
        this.productoId = idProducto;
    }

    public Sucursal() {
    super();
    }
    
    
}

