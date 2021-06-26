package com.trabajo.crud.controller;

import com.trabajo.crud.dto.EstadoDto;
import com.trabajo.crud.dto.TipoIdentificacionDto;
import com.trabajo.crud.entity.Estado;
import com.trabajo.crud.entity.TipoIdentificacion;
import com.trabajo.crud.repository.EstadoRepository;
import com.trabajo.crud.repository.TipoIdentificacionRepository;
import com.trabajo.crud.response.Response;
import com.trabajo.crud.service.EstadoService;
import com.trabajo.crud.service.TipoIdentificacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;

@RestController
@CrossOrigin
@RequestMapping("/estado")
public class EstadoController {

    @Autowired
    private EstadoRepository estadoRepository;

    @Autowired
    private EstadoService estadoService;

    @PostMapping("/register")
    public ResponseEntity<Object> registerEstado(@RequestBody EstadoDto estadoDto) {
        org.springframework.security.core.Authentication auth
                = SecurityContextHolder.getContext().getAuthentication();

        Response response = new Response("Ya existe un estado con el código ingresado",
                HttpStatus.BAD_REQUEST.value());

        if(estadoRepository.existsById(estadoDto.getCodigo())) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Estado estado = new Estado();
        estado.setCodigo(estadoDto.getCodigo());
        estado.setNombre(estadoDto.getNombre());
        estado.setUsuarioCreacion(auth.getPrincipal().toString());
        estado.setUsuarioModificacion(auth.getPrincipal().toString());
        estado.setFechaCreacion(timestamp);
        estado.setFechaModificacion(timestamp);

        estadoRepository.save(estado);

        response.setMensaje("Registrado correctamente");
        response.setStatus(HttpStatus.OK.value());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/update/{codigo}")
    public ResponseEntity<Object> registerEstado(@PathVariable("codigo") int codigo,
                                                             @RequestHeader String name ) {
        Response response = new Response("Actualizado correctamente",
                HttpStatus.OK.value());

        Estado estado = estadoRepository.findById(codigo)
                .orElse(null);
        if(estado == null){
            response.setMensaje("No se encontró el estado con código ingresado");
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        estado.setNombre(name);
        estadoService.updateEstado(codigo, estado);


        return new ResponseEntity<>(response, HttpStatus.OK);

    }
}
