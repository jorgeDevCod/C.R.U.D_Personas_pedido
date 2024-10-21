package com.MyApiRest.TareaApi.repository;

import com.MyApiRest.TareaApi.entity.PedidoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PedidoRepository extends JpaRepository<PedidoEntity, Long> {
    List<PedidoEntity> findByEstado(String estado);
}
