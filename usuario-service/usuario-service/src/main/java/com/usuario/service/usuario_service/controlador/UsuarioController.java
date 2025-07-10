package com.usuario.service.usuario_service.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.usuario.service.usuario_service.entidades.Usuario;
import com.usuario.service.usuario_service.modelos.Producto;
import com.usuario.service.usuario_service.repositorio.UsuarioRepository;
import com.usuario.service.usuario_service.servicio.UsuarioService;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @SuppressWarnings("unused")
    private final UsuarioRepository usuarioRepository;
    
    @Autowired
    private UsuarioService usuarioService;

    UsuarioController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    /*---------------------------------------------------------------------------------------------*/

    /* OBTENER USUARIO */
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obtenerUsuario(@PathVariable("id")int id) {
        Usuario usuario = usuarioService.getUsuarioById(id);
        
        if(usuario==null){
            return ResponseEntity.notFound().build();
        }else{
        EntityModel<Usuario>recurso= EntityModel.of(usuario);

        recurso.add(linkTo(methodOn(UsuarioController.class).obtenerUsuario(id)).withSelfRel());
        recurso.add(linkTo(methodOn(UsuarioController.class).listarUsuario()).withRel("todos-los-usuarios"));

        return ResponseEntity.ok(usuario);
        }
    }

    /* ELIMINAR USUARIO */
    @DeleteMapping("/{id}")
    public ResponseEntity<Usuario> eliminarUsuario(@PathVariable("id")int id) {
        Usuario usuario = usuarioService.getUsuarioById(id);
    
        if(usuario==null){
            return ResponseEntity.notFound().build();
        }
        usuarioService.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }

    /* LISTAR USUARIO */
    @GetMapping
    public ResponseEntity<List<Usuario>> listarUsuario(){

        List<Usuario> usuarios = usuarioService.getAll();
        
        if (usuarios.isEmpty()){
            return ResponseEntity.noContent().build();
        }else{
        CollectionModel<Usuario>recurso= CollectionModel.of(usuarios);
        
        recurso.add(linkTo(methodOn(UsuarioController.class).listarUsuario()).withSelfRel());

        return ResponseEntity.ok(usuarios);
        }
    }

    /* GUARDAR USUARIO */
    @PostMapping
    public ResponseEntity<Usuario> guardarUsuario(@RequestBody Usuario usuario){
        
        Usuario nuevoUsuario = usuarioService.save(usuario);
        return ResponseEntity.ok(nuevoUsuario);
    }


    /*---------------------------------------------------------------------------------------------*/

    /* LISTAR PRODUCTO */
    @GetMapping("/productos/{usuarioId}")
    public ResponseEntity<List<Producto>> listarProducto(@PathVariable("usuarioId")int id){
        Usuario usuario = usuarioService.getUsuarioById(id);

        if(usuario == null){
            return ResponseEntity.notFound().build();
        }else{
        List<Producto> productos = usuarioService.getProductos(id);

        CollectionModel<Producto>recurso = CollectionModel.of(productos);
        
        recurso.add(linkTo(methodOn(UsuarioController.class).listarProducto(id)).withSelfRel());
        recurso.add(linkTo(methodOn(UsuarioController.class).listarUsuario()).withRel("todos-los-usuarios"));

        return ResponseEntity.ok(productos);
        }
    }


    /*---------------------------------------------------------------------------------------------*/

    /* LISTAR COMPROBANTE */
    
    @GetMapping("/comprobante/{comprobanteId}")
    public ResponseEntity<List<Usuario>> getByComprobanteId(@PathVariable ("comprobanteId")int comprobanteId) {
    List<Usuario> usuarios = usuarioService.findByComprobanteId(comprobanteId);
    if (usuarios.isEmpty()) {
      return ResponseEntity.noContent().build();

    }
    return ResponseEntity.ok(usuarios);
    }
}
