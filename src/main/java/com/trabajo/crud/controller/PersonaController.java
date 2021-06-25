package com.trabajo.crud.controller;

import com.trabajo.crud.dto.PersonaDetailBasicDto;
import com.trabajo.crud.entity.Persona;
import com.trabajo.crud.repository.PersonaRepository;
import com.trabajo.crud.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin
@RequestMapping("/persona")
public class PersonaController {

	@Autowired
	private PersonaService personaService;

	@Autowired
	private PersonaRepository personaRepository;

	@PostMapping("/register")
	public ResponseEntity<String> registerUser(@RequestBody Persona persona) {

		boolean personaByCodigo = personaRepository.existsByCodigo(persona.getCodigo());
		boolean personaByUsername = personaRepository.existsByUsername(persona.getUsername());
		boolean personaByIdentificacion = personaRepository.existsByIdentificacion(persona.getIdentificacion());

		if(personaByCodigo || personaByUsername || personaByIdentificacion) {
			return new ResponseEntity<>("Mal", HttpStatus.BAD_REQUEST);
		}

		personaService.insert(persona);

		return new ResponseEntity<>("Bien", HttpStatus.OK);
	}

	@GetMapping("/getpersoninfo/{id}")
	public PersonaDetailBasicDto personaDetailDto(@PathVariable("id") int identificacion) {

		boolean personaByIdentificacion = personaRepository.existsByIdentificacion(identificacion);
		if(personaByIdentificacion) {
			PersonaDetailBasicDto personaDetailBasicDto = personaService.findPersonaDetail(identificacion);
			return personaDetailBasicDto;
		}
		return null;
	}

	@GetMapping("/getPersons")
	public List<Persona> getAllPerson() {
		return personaService.getPersonas();
	}

	@PutMapping("/updateperson/{codigo}")
	public ResponseEntity updatePerson(@PathVariable("codigo") int codigo,
									   @RequestBody Persona persona) {
		boolean personaByCodigo = personaRepository.existsByCodigo(codigo);
		if(personaByCodigo) {
			personaService.updatePersona(codigo, persona);
			return new ResponseEntity<>("Actualizado!", HttpStatus.OK);
		}
		return new ResponseEntity<>("No se encontró al usuario", HttpStatus.BAD_REQUEST);
	}

}
