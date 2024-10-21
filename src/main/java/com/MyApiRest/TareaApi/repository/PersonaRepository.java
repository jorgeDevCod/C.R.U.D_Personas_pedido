package com.MyApiRest.TareaApi.repository;

import com.MyApiRest.TareaApi.entity.PersonaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PersonaRepository extends JpaRepository<PersonaEntity, Long> {
    List<PersonaEntity> findByEstado(String estado);
    Optional<PersonaEntity> findByNumeroDocumento(Long numeroDocumento);
}
