package com.MyApiRest.TareaApi.service;

import com.MyApiRest.TareaApi.entity.PersonaEntity;

import java.util.List;

public interface PersonaService {

    PersonaEntity crearPersona(PersonaEntity persona);

    List<PersonaEntity> buscarTodos();

    PersonaEntity buscarPersonaPorNumeroDocumento(Long numeroDocumento);

    PersonaEntity actualizarPersona(Long id, PersonaEntity persona);

    void eliminarPersona(Long id);
}
