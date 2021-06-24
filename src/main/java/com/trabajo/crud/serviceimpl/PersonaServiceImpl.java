package com.trabajo.crud.serviceimpl;

import com.trabajo.crud.entity.Persona;
import com.trabajo.crud.repository.PersonaRepository;
import com.trabajo.crud.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonaServiceImpl implements PersonaService {
    @Autowired
    PersonaRepository personaRepository;


    @Override
    public List<Persona> getPersonas() {
        List<Persona> personaList = new ArrayList<>();
        personaRepository.findAll().forEach(personaList::add);
        return personaList;
    }

    @Override
    public Persona insert(Persona persona) {
        return personaRepository.save(persona);
    }

    @Override
    public void updatePersona(int codigo, Persona persona) {
        Persona personaFromDb = personaRepository.findById(codigo).get();
        personaFromDb.setNombre(persona.getNombre());
        personaFromDb.setApellido(persona.getApellido());
        personaFromDb.setCodigoEstado(persona.getCodigoEstado());
        personaFromDb.setIdentificacion(persona.getIdentificacion());
        personaRepository.save(personaFromDb);
    }

    @Override
    public void deletePersona(int codigo) {
        personaRepository.deleteById(codigo);
    }
}
