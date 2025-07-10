package com.boleta.service.boleta_service.entidades;

import java.util.List;

import com.boleta.service.boleta_service.modelos.Producto;
import com.boleta.service.boleta_service.modelos.Usuario;

public class UsuarioProducto {

    private Usuario usuario;
    private List<Producto> productos;

    public UsuarioProducto(Usuario usuario, List<Producto> productos) {
        this.usuario   = usuario;
        this.productos = productos;
    }
    

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }


    @Override
    public String toString() {
        return "UsuarioProducto [usuario=" + usuario + ", productos=" + productos + "]";
    }
}
