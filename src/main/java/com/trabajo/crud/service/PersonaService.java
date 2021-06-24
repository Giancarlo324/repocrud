package com.trabajo.crud.service;


import com.trabajo.crud.entity.Persona;

import java.util.List;

public interface PersonaService {

    List<Persona> getPersonas();

    Persona insert(Persona persona);

    void updatePersona(int codigo, Persona persona);

    void deletePersona(int codigo);
}
