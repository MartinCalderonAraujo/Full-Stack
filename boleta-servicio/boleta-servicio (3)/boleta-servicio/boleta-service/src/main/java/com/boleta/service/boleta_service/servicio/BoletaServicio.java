package com.boleta.service.boleta_service.servicio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import com.boleta.service.boleta_service.entidades.Comprobante;
import com.boleta.service.boleta_service.entidades.UsuarioProducto;
import com.boleta.service.boleta_service.modelos.Producto;
import com.boleta.service.boleta_service.modelos.Usuario;
import com.boleta.service.boleta_service.repositorio.BoletaRepository;

@Service
public class BoletaServicio {

    @Autowired
    private BoletaRepository boletaRepository;

    public List<Comprobante> getAll(){
        return boletaRepository.findAll();
    }
    public Comprobante getBoletaById(int comprobanteId){
        return boletaRepository.findById(comprobanteId).orElse(null);
    }
    public Comprobante save(Comprobante comprobante){
        Comprobante nuevoComprobante = boletaRepository.save(comprobante);
        return nuevoComprobante;
    }
    
    @Autowired
    private RestTemplate restTemplate;
    public List<Usuario> getUsuarios(int comprobanteId){
        List<Usuario>usuarios=restTemplate.getForObject("http://localhost:8001/usuario/comprobante/"+comprobanteId,List.class);
        return usuarios;
    }

    public List<Producto> getProductosPorUsuario(int usuarioId) {
        List<Producto>productos= restTemplate.getForObject(
        "http://localhost:8002/producto/usuario/" + usuarioId,
        List.class);
        return productos;
    }

    public List<Comprobante>byUsuarioId(int usuarioId){
        return boletaRepository.findByUsuarioId(usuarioId);
    }
    @Autowired
    private RestTemplate restTemplate2;
    public List<UsuarioProducto> getUsuariosConProductos(int comprobanteId) {
    List<Usuario> usuarios = getUsuarios(comprobanteId);
    if(usuarios==null){
        usuarios=new ArrayList<>();
    }
    List<UsuarioProducto> resultado = new ArrayList<>();
    for (Usuario usuario : usuarios) {
        List<Producto> productos;
        try {
            productos = getProductosPorUsuario(usuario.getUsuarioId());
        } catch (RestClientException e) {
            productos = Collections.emptyList();
        }
        resultado.add(new UsuarioProducto(usuario, productos));
    }
    return resultado;
    }

    public void deleteComprobanteById(int comprobanteId){
        boletaRepository.deleteById(comprobanteId);
    }
}
