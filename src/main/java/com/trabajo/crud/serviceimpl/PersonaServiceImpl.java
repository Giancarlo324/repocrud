package com.trabajo.crud.serviceimpl;

import com.trabajo.crud.dto.PersonaDetailBasicDto;
import com.trabajo.crud.dto.PersonaLoginDto;
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
    public List<PersonaDetailBasicDto> getPersonas() {
        return new ArrayList<>(personaRepository.findAllPersonas());
    }

    @Override
    public Persona insert(Persona persona) {
        return personaRepository.save(persona);
    }

    @Override
    public void updatePersona(int identificacion, Persona persona) {
        Persona personaFromDb = personaRepository.findByIdentificacion(identificacion);
        personaFromDb.setNombre(persona.getNombre());
        personaFromDb.setApellido(persona.getApellido());
        personaFromDb.setCodigoEstado(persona.getCodigoEstado());
        personaFromDb.setIdentificacion(persona.getIdentificacion());
        personaFromDb.setPassword(persona.getPassword());
        personaFromDb.setFechaNacimiento(persona.getFechaNacimiento());
        personaFromDb.setUsername(persona.getUsername());
        personaRepository.save(personaFromDb);
    }

    @Override
    public void delete(Persona persona) {
        personaRepository.delete(persona);
    }

    @Override
    public void deleteById(int codigo) {
        personaRepository.deleteById(codigo);
    }

    @Override
    public PersonaDetailBasicDto findPersonaDetail(int identificacion) {
        return personaRepository.findPersonaDetail(identificacion);
    }

    @Override
    public List<PersonaDetailBasicDto> findPersonasActivas() {
        return personaRepository.findPersonasActivas();
    }

    @Override
    public PersonaLoginDto findByUsernameAndPassword(String username, String password) {
        return personaRepository.findByUsernameAndPassword(username, password);
    }
}
