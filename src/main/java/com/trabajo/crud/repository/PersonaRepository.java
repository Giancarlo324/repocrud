package com.trabajo.crud.repository;

import com.trabajo.crud.dto.PersonaDetailBasicDto;
import com.trabajo.crud.dto.PersonaLoginDto;
import com.trabajo.crud.entity.Persona;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonaRepository extends CrudRepository<Persona, Integer> {
    boolean existsByUsername(String username);
    boolean existsByCodigo(int codigo);
    boolean existsByIdentificacion(int identificacion);


    Persona findByIdentificacion(int identificacion);

    PersonaLoginDto findByUsernameAndPassword(String username, String password);

    @Query(value = "SELECT p.nombre as nombre, p.apellido as apellido, " +
            "ti.nombre as tipoIdentificacion, p.identificacion as identificacion, " +
            "p.fecha_nacimiento as fechaNacimiento, e.nombre as estado " +
            "FROM persona p " +
            "INNER JOIN estado e ON p.codigo_estado = e.codigo " +
            "INNER JOIN tipo_identificacion ti ON p.codigo_tipo_identificacion = ti.codigo " +
            "WHERE p.identificacion = :identificacion", nativeQuery = true)
    PersonaDetailBasicDto findPersonaDetail(@Param("identificacion") int identificacion);

    @Query(value = "SELECT p.nombre as nombre, p.apellido as apellido, " +
            "ti.nombre as tipoIdentificacion, p.identificacion as identificacion, " +
            "p.fecha_nacimiento as fechaNacimiento, e.nombre as estado " +
            "FROM persona p " +
            "INNER JOIN estado e ON p.codigo_estado = e.codigo " +
            "INNER JOIN tipo_identificacion ti ON p.codigo_tipo_identificacion = ti.codigo " +
            "WHERE p.codigo_estado = 1", nativeQuery = true)
    List<PersonaDetailBasicDto> findPersonasActivas();

    @Query(value = "SELECT DISTINCT p.nombre as nombre, p.apellido as apellido, " +
            "ti.nombre as tipoIdentificacion, p.identificacion as identificacion, " +
            "p.fecha_nacimiento as fechaNacimiento, e.nombre as estado " +
            "FROM persona p " +
            "INNER JOIN estado e ON p.codigo_estado = e.codigo " +
            "INNER JOIN tipo_identificacion ti ON p.codigo_tipo_identificacion = ti.codigo ", nativeQuery = true)
    List<PersonaDetailBasicDto> findAllPersonas();
}
