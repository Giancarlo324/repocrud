package com.trabajo.crud.controller;

import com.trabajo.crud.dto.PersonaLoginDto;
import com.trabajo.crud.entity.Persona;
import com.trabajo.crud.repository.PersonaRepository;
import com.trabajo.crud.response.Response;
import com.trabajo.crud.service.PersonaService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@CrossOrigin
@RequestMapping("/persona")
public class PersonaController {

	@Autowired
	private PersonaService personaService;

	@Autowired
	private PersonaRepository personaRepository;

	@PostMapping("/register")
	public ResponseEntity<Response> registerUser(@RequestBody Persona persona) {

		boolean personaByCodigo = personaRepository.existsByCodigo(persona.getCodigo());
		boolean personaByUsername = personaRepository.existsByUsername(persona.getUsername());
		boolean personaByIdentificacion = personaRepository.existsByIdentificacion(persona.getIdentificacion());

		Response response = new Response("Falló al registrar usuario", HttpStatus.BAD_REQUEST.value());

		if(personaByCodigo || personaByUsername || personaByIdentificacion) {
			return new ResponseEntity<>(response, HttpStatus.OK);
		}

		personaService.insert(persona);
		response.setStatus(HttpStatus.OK.value());
		response.setMensaje("Registro exitoso");

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping("/login")
	public ResponseEntity<Response> loginPerson(@RequestParam String username, @RequestParam String password) {
		PersonaLoginDto personaLoginDto = personaService.findByUsernameAndPassword(username, password);
		Response response = new Response("Inicio de sesión fallido", HttpStatus.BAD_REQUEST.value());
		if(personaLoginDto != null){
			String token = getJWTToken(username);
			response.setMensaje(token);
			response.setStatus(HttpStatus.OK.value());
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	private String getJWTToken(String username) {
		String secretKey = "mySecretKey";
		List<GrantedAuthority> grantedAuthorities = AuthorityUtils
				.commaSeparatedStringToAuthorityList("admin");

		String token = Jwts
				.builder()
				.setId("softtekJWT")
				.setSubject(username)
				.claim("authorities",
						grantedAuthorities.stream()
								.map(GrantedAuthority::getAuthority)
								.collect(Collectors.toList()))
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 600000))
				.signWith(SignatureAlgorithm.HS512,
						secretKey.getBytes()).compact();

		return "Bearer " + token;
	}

	@GetMapping("/getpersonainfo/{id}")
	public ResponseEntity<Object> personaDetailDto(@PathVariable("id") int identificacion) {

		boolean personaByIdentificacion = personaRepository.existsByIdentificacion(identificacion);
		if(personaByIdentificacion){
			return new ResponseEntity<>(personaService.findPersonaDetail(identificacion), HttpStatus.OK);
		}
		Response response = new Response("No se encontró a la persona", HttpStatus.BAD_REQUEST.value());
		return new ResponseEntity<>(response, HttpStatus.OK);

	}

	@GetMapping("/getpersonas")
	public ResponseEntity<Object> getAllPerson() {
		if(!personaService.getPersonas().isEmpty()){
			return new ResponseEntity<>(personaService.getPersonas(), HttpStatus.OK);
		}
		Response response = new Response("No hay personas registradas", HttpStatus.BAD_REQUEST.value());
		return new ResponseEntity<>(response, HttpStatus.OK);

	}

	@GetMapping("/getpersonasactivas")
	public ResponseEntity<Object> personaDetailBasicDto() {
		if(!personaService.findPersonasActivas().isEmpty()) {
			return new ResponseEntity<>(personaService.findPersonasActivas(), HttpStatus.OK);
		}
		Response response = new Response("No hay personas activas", HttpStatus.BAD_REQUEST.value());
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PutMapping("/updatepersona/{identificacion}")
	public ResponseEntity<Object> updatePersona(@PathVariable("identificacion") int identificacion,
									   @RequestBody Persona persona) {
		boolean personaByIdentificacion = personaRepository.existsByIdentificacion(identificacion);
		Response response = new Response("Persona actualizada", HttpStatus.OK.value());
		if(personaByIdentificacion) {
			personaService.updatePersona(identificacion, persona);
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		response.setMensaje("No se encontró a la persona con identificación ingresada");
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

	@DeleteMapping("/deletepersona/{codigo}")
	public ResponseEntity<Object> deletePersona(@PathVariable("codigo") int codigo) {
		boolean personaByCodigo = personaRepository.existsByCodigo(codigo);
		Response response = new Response("Persona eliminada", HttpStatus.OK.value());
		if(personaByCodigo){
			personaService.deleteById(codigo);
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		response.setMensaje("No se encontró a la persona con código ingresado");
		response.setStatus(HttpStatus.BAD_REQUEST.value());
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
