package com.trabajo.crud.controller;

import com.trabajo.crud.dto.TipoIdentificacionDto;
import com.trabajo.crud.entity.TipoIdentificacion;
import com.trabajo.crud.service.TipoIdentificacionService;
import org.springframework.security.core.context.SecurityContextHolder;
import com.trabajo.crud.repository.TipoIdentificacionRepository;
import com.trabajo.crud.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.sql.Timestamp;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/tipoidentificacion")
public class TipoIdentificacionController {

    @Autowired
    private TipoIdentificacionRepository tipoIdentificacionRepository;

    @Autowired
    private TipoIdentificacionService tipoIdentificacionService;

    @PostMapping("/register")
    public ResponseEntity<Object> registerTipoIdentificacion(@RequestBody TipoIdentificacionDto tipoIdentificacionDto) {
        org.springframework.security.core.Authentication auth
                = SecurityContextHolder.getContext().getAuthentication();

        Response response = new Response("Ya existe un tipo identificación con el código ingresado",
                HttpStatus.BAD_REQUEST.value());

        if(tipoIdentificacionRepository.existsById(tipoIdentificacionDto.getCodigo())) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        TipoIdentificacion tipoIdentificacion = new TipoIdentificacion();
        tipoIdentificacion.setCodigo(tipoIdentificacionDto.getCodigo());
        tipoIdentificacion.setNombre(tipoIdentificacionDto.getNombre());
        tipoIdentificacion.setUsuarioCreacion(auth.getPrincipal().toString());
        tipoIdentificacion.setUsuarioModificacion(auth.getPrincipal().toString());
        tipoIdentificacion.setFechaCreacion(timestamp);
        tipoIdentificacion.setFechaModificacion(timestamp);

        tipoIdentificacionRepository.save(tipoIdentificacion);


        response.setMensaje("Registrado correctamente");
        response.setStatus(HttpStatus.OK.value());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/update/{codigo}")
    public ResponseEntity<Object> registerTipoIdentificacion(@PathVariable("codigo") int codigo,
                                                             @RequestHeader String name ) {
        Response response = new Response("Actualizado correctamente",
                HttpStatus.OK.value());

        TipoIdentificacion tipoIdentificacion = tipoIdentificacionRepository.findById(codigo)
                .orElse(null);
        if(tipoIdentificacion == null){
            response.setMensaje("No se encontró el tipo identificación con código ingresado");
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        tipoIdentificacion.setNombre(name);
        tipoIdentificacionService.updateTipoIdentificacion(codigo, tipoIdentificacion);


        return new ResponseEntity<>(response, HttpStatus.OK);

    }
}
