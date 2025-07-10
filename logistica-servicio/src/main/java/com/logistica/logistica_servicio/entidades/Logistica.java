package com.logistica.logistica_servicio.entidades;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Logistica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   
    private int idEnvio;
    private int idPedido;
    private String estadoPedido;
    private LocalDate fechaDespacho;
    private String transportista;
    private LocalDate fechaEntregaEstimada;

    public Logistica() {
    }

    public Logistica(String estadoPedido, LocalDate fechaDespacho, String transportista, LocalDate fechaEntregaEstimada) {
        this.estadoPedido = estadoPedido;
        this.fechaDespacho = fechaDespacho;
        this.transportista = transportista;
        this.fechaEntregaEstimada = fechaEntregaEstimada;
    }

    public int getIdEnvio() {
        return idEnvio;
    }

    public void setIdEnvio(int idEnvio) {
        this.idEnvio = idEnvio;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }
    public String getEstadoPedido() {
        return estadoPedido;
    }

    public void setEstadoPedido(String estadoPedido) {
        this.estadoPedido = estadoPedido;
    }

    public LocalDate getFechaDespacho() {
        return fechaDespacho;
    }

    public void setFechaDespacho(LocalDate fechaDespacho) {
        this.fechaDespacho = fechaDespacho;
    }

    public String getTransportista() {
        return transportista;
    }

    public void setTransportista(String transportista) {
        this.transportista = transportista;
    }

    public LocalDate getFechaEntregaEstimada() {
        return fechaEntregaEstimada;
    }

    public void setFechaEntregaEstimada(LocalDate fechaEntregaEstimada) {
        this.fechaEntregaEstimada = fechaEntregaEstimada;
    }
}