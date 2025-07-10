package soporte.service.soporte_service.Servicio;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import soporte.service.soporte_service.Entidades.Soporte;
import soporte.service.soporte_service.Repository.SoporteRepository;

@Service
public class SoporteServicio {

    @Autowired

    private SoporteRepository soporteRepository;

    public List<Soporte> getAll(){
        return soporteRepository.findAll();
    }

    public Soporte getSoporteById(int idSoporte){
        return soporteRepository.findById(idSoporte).orElse(null);
    }

    public Soporte save(Soporte soporte){
        Soporte nuevoSoporte = soporteRepository.save(soporte);
        return nuevoSoporte;
    }

    public void deleteSoporteById(int idSoporte){
        soporteRepository.deleteById(idSoporte);
    }

    @Autowired
    private RestTemplate restTemplate;
    public List<Soporte> getUsuarios(int idSoporte){
        List<Soporte>soportes=restTemplate.getForObject("http://localhost:8001/usuario/soporte/"+idSoporte,List.class);
        return soportes;
    }

    public List<Soporte>byUsuarioId(int usuarioId){
        return soporteRepository.findByUsuarioId(usuarioId);
    }
}
