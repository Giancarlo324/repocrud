package com.trabajo.crud.service;


import com.trabajo.crud.dto.PersonaDetailBasicDto;
import com.trabajo.crud.dto.PersonaLoginDto;
import com.trabajo.crud.entity.Persona;

import java.util.List;

public interface PersonaService {

    List<PersonaDetailBasicDto> getPersonas();

    Persona insert(Persona persona);

    void updatePersona(int identificacion, Persona persona);

    void deletePersona(int identificacion);

    PersonaDetailBasicDto findPersonaDetail(int identificacion);

    List<PersonaDetailBasicDto> findPersonasActivas();

    PersonaLoginDto findByUsernameAndPassword(String username, String password);
}
