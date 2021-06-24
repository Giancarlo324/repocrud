package com.trabajo.crud.controller;

import com.trabajo.crud.entity.Persona;
import com.trabajo.crud.repository.PersonaRepository;
import com.trabajo.crud.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
@RequestMapping("/auth")
public class PersonaController {

	@Autowired
	private PersonaService personaService;

	@Autowired
	private PersonaRepository personaRepository;

	@PostMapping("/register")
	public ResponseEntity<String> registerUser(@RequestBody Persona persona) {

		Boolean personaByCodigo = personaRepository.existsByCodigo(persona.getCodigo());
		Boolean personaByUsername = personaRepository.existsByUsername(persona.getUsername());
		Boolean personaByIdentificacion = personaRepository.existsByIdentificacion(persona.getIdentificacion());

		if(personaByCodigo || personaByUsername || personaByIdentificacion) {
			return new ResponseEntity<>("Mal", HttpStatus.BAD_REQUEST);
		}

		personaService.insert(persona);

		return new ResponseEntity<>("Bien", HttpStatus.OK);
	}

	/*@GetMapping("/getuserinfo")
	public*/

}
