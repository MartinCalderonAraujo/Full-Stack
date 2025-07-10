package com.logistica.logistica_servicio.servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.logistica.logistica_servicio.entidades.Logistica;
import com.logistica.logistica_servicio.modelo.Pedido;
import com.logistica.logistica_servicio.repositorio.LogisticaRepositorio;

@Service
public class LogisticaServicio {

    @Autowired
    public LogisticaRepositorio logisticaRepositorio;

    public List<Logistica> listarTodos() {
        return logisticaRepositorio.findAll();
    }

    public Logistica buscarPorId(int idEnvio) {
        return logisticaRepositorio.findById(idEnvio).orElse(null);
    }

    public Logistica guardar(Logistica logistica) {
        return logisticaRepositorio.save(logistica);
    }

    public Logistica actualizar(int idEnvio, Logistica logisticaActualizada) {
        return logisticaRepositorio.findById(idEnvio)
                .map(logistica -> {
                    logistica.setEstadoPedido(logisticaActualizada.getEstadoPedido());
                    logistica.setFechaDespacho(logisticaActualizada.getFechaDespacho());
                    logistica.setTransportista(logisticaActualizada.getTransportista());
                    logistica.setFechaEntregaEstimada(logisticaActualizada.getFechaEntregaEstimada());
                    return logisticaRepositorio.save(logistica);
                })
                .orElseThrow(() -> new RuntimeException("Logística con id " + idEnvio + " no encontrada"));
    }

    public List<Logistica> getAll(){
        return logisticaRepositorio.findAll();
    }

    public void eliminar(int idEnvio) {
        logisticaRepositorio.deleteById(idEnvio);
    }
    @Autowired
    private RestTemplate restTemplate;
    public List<Pedido> getProductos(int idPedido){
        List<Pedido>pedidos = restTemplate.getForObject("http://localhost:8005/pedido/logistica/"+idPedido, List.class);
        return pedidos;
    }


    public List<Logistica>byIdPedido(int idPedido){
        return logisticaRepositorio.findByIdPedido(idPedido);
    }    
}
