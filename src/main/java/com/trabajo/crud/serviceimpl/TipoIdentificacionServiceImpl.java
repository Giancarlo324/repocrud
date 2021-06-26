package com.trabajo.crud.serviceimpl;

import com.trabajo.crud.entity.Persona;
import com.trabajo.crud.entity.TipoIdentificacion;
import com.trabajo.crud.repository.TipoIdentificacionRepository;
import com.trabajo.crud.service.TipoIdentificacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.sql.Timestamp;


@Service
public class TipoIdentificacionServiceImpl implements TipoIdentificacionService {

    @Autowired
    private TipoIdentificacionRepository tipoIdentificacionRepository;

    @Override
    public void updateTipoIdentificacion(int codigo, TipoIdentificacion tipoIdentificacionNuevo) {
        org.springframework.security.core.Authentication auth
                = SecurityContextHolder.getContext().getAuthentication();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        TipoIdentificacion tipoIdentificacionFromDb = tipoIdentificacionRepository.findById(codigo)
                .orElse(null);
        if(tipoIdentificacionFromDb != null) {
            tipoIdentificacionFromDb.setNombre(tipoIdentificacionNuevo.getNombre());
            tipoIdentificacionFromDb.setUsuarioModificacion(auth.getPrincipal().toString());
            tipoIdentificacionFromDb.setFechaModificacion(timestamp);

            tipoIdentificacionRepository.save(tipoIdentificacionFromDb);
        }
    }
}
