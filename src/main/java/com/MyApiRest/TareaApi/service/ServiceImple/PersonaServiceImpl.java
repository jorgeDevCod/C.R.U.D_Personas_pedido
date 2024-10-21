package com.MyApiRest.TareaApi.service.ServiceImple;

import com.MyApiRest.TareaApi.constant.Constants;
import com.MyApiRest.TareaApi.entity.PersonaEntity;
import com.MyApiRest.TareaApi.repository.PersonaRepository;
import com.MyApiRest.TareaApi.service.PersonaService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class PersonaServiceImpl implements PersonaService {

    private final PersonaRepository personaRepository;

    public PersonaServiceImpl(PersonaRepository personaRepository) {
        this.personaRepository = personaRepository;
    }

    @Override
    public PersonaEntity crearPersona(PersonaEntity persona) {
        persona.setEstado(Constants.PersonaEstado.ACTIVO);
        return personaRepository.save(persona);
    }

    @Override
    public List<PersonaEntity> buscarTodos() {
        return personaRepository.findByEstado(Constants.PersonaEstado.ACTIVO);
    }

    @Override
    public PersonaEntity buscarPersonaPorNumeroDocumento(Long numeroDocumento) {
        return personaRepository.findByNumeroDocumento(numeroDocumento)
                .orElseThrow(() -> new NoSuchElementException("Persona no encontrada"));
    }

    @Override
    public PersonaEntity actualizarPersona(Long id, PersonaEntity personaActualizada) {
        PersonaEntity personaExistente = personaRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Persona no encontrada"));

        personaExistente.setNombres(personaActualizada.getNombres());
        personaExistente.setApellidos(personaActualizada.getApellidos());
        personaExistente.setNumeroDocumento(personaActualizada.getNumeroDocumento());
        personaExistente.setDireccion(personaActualizada.getDireccion());

        return personaRepository.save(personaExistente);
    }

    @Override
    public void eliminarPersona(Long id) {
        PersonaEntity persona = personaRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Persona no encontrada"));
        persona.setEstado(Constants.PersonaEstado.INACTIVO);
        personaRepository.save(persona);
    }
}