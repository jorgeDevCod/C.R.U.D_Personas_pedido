package com.MyApiRest.TareaApi.controller;

import com.MyApiRest.TareaApi.entity.PersonaEntity;
import com.MyApiRest.TareaApi.service.PersonaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/personas/v1")
public class PersonaController {

    private final PersonaService personaService;

    public PersonaController(PersonaService personaService) {
        this.personaService = personaService;
    }

    @PostMapping("/crear")
    public ResponseEntity<PersonaEntity> crearPersona(@RequestBody PersonaEntity persona) {
        return new ResponseEntity<>(personaService.crearPersona(persona), HttpStatus.CREATED);
    }

    @GetMapping("/buscarTodos")
    public ResponseEntity<List<PersonaEntity>> buscarTodos() {
        return new ResponseEntity<>(personaService.buscarTodos(), HttpStatus.OK);
    }

    @GetMapping("/buscarPersonaXnumDocumento/{numeroDocumento}")
    public ResponseEntity<PersonaEntity> buscarPersonaPorNumeroDocumento(
            @PathVariable Long numeroDocumento) {
        return new ResponseEntity<>(
                personaService.buscarPersonaPorNumeroDocumento(numeroDocumento),
                HttpStatus.OK
        );
    }

    @PutMapping("/actualizarPersona/{id}")
    public ResponseEntity<PersonaEntity> actualizarPersona(
            @PathVariable Long id,
            @RequestBody PersonaEntity persona) {
        return new ResponseEntity<>(
                personaService.actualizarPersona(id, persona),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/eliminarPersona/{id}")
    public ResponseEntity<Void> eliminarPersona(@PathVariable Long id) {
        personaService.eliminarPersona(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
