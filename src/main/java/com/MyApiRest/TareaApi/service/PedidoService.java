package com.MyApiRest.TareaApi.service;

import com.MyApiRest.TareaApi.entity.PedidoEntity;

import java.util.List;

public interface PedidoService {
    PedidoEntity crearPedido(Long personaId, PedidoEntity pedido);

    List<PedidoEntity> buscarTodos(String estado);

    PedidoEntity buscarPedidoPorId(Long id);

    List<PedidoEntity> buscarPedidoPorEstado(String estado);

    PedidoEntity actualizarPedido(Long id, Long personaId, PedidoEntity pedido);

    void eliminarPedido(Long id);
}
