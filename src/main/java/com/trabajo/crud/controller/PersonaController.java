package com.trabajo.crud.controller;

import com.trabajo.crud.dto.PersonaDetailBasicDto;
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
				.commaSeparatedStringToAuthorityList("ROLE_USER");

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
	public List<PersonaDetailBasicDto> getAllPerson() {
		return personaService.getPersonas();
	}

	@GetMapping("/getpersonactiva")
	public PersonaDetailBasicDto personaDetailBasicDto() {
		return personaService.findPersonasActivas();
	}

	@PutMapping("/updatepersona/{codigo}")
	public ResponseEntity<?> updatePerson(@PathVariable("codigo") int codigo,
									   @RequestBody Persona persona) {
		boolean personaByCodigo = personaRepository.existsByCodigo(codigo);
		if(personaByCodigo) {
			personaService.updatePersona(codigo, persona);
			return new ResponseEntity<>("Actualizado!", HttpStatus.OK);
		}
		return new ResponseEntity<>("No se encontró al usuario", HttpStatus.BAD_REQUEST);
	}

}
