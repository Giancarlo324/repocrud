package com.trabajo.crud.serviceimpl;

import com.trabajo.crud.entity.Estado;
import com.trabajo.crud.repository.EstadoRepository;
import com.trabajo.crud.service.EstadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class EstadoServiceImpl implements EstadoService {

    @Autowired
    private EstadoRepository estadoRepository;

    @Override
    public void updateEstado(int codigo, Estado estadoNuevo) {
        org.springframework.security.core.Authentication auth
                = SecurityContextHolder.getContext().getAuthentication();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        Estado estadoFromDb = estadoRepository.findById(codigo)
                .orElse(null);
        if(estadoFromDb != null) {
            estadoFromDb.setNombre(estadoNuevo.getNombre());
            estadoFromDb.setUsuarioModificacion(auth.getPrincipal().toString());
            estadoFromDb.setFechaModificacion(timestamp);

            estadoRepository.save(estadoFromDb);
        }
    }
}
