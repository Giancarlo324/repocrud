package com.trabajo.crud.repository;

import com.trabajo.crud.entity.Persona;
import org.springframework.data.repository.CrudRepository;

public interface PersonaRepository extends CrudRepository<Persona, Integer> {
    boolean existsByUsername(String username);
    boolean existsByCodigo(int codigo);
    boolean existsByIdentificacion(int identificacion);


}
