package com.MyApiRest.TareaApi.service.ServiceImple;

import com.MyApiRest.TareaApi.constant.Constants;
import com.MyApiRest.TareaApi.entity.PedidoEntity;
import com.MyApiRest.TareaApi.entity.PersonaEntity;
import com.MyApiRest.TareaApi.repository.PedidoRepository;
import com.MyApiRest.TareaApi.repository.PersonaRepository;
import com.MyApiRest.TareaApi.service.PedidoService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class PedidoServiceImpl implements PedidoService {

    private final PedidoRepository pedidoRepository;
    private final PersonaRepository personaRepository;

    public PedidoServiceImpl(PedidoRepository pedidoRepository, PersonaRepository personaRepository) {
        this.pedidoRepository = pedidoRepository;
        this.personaRepository = personaRepository;
    }

    @Override
    @Transactional
    public PedidoEntity crearPedido(Long personaId, PedidoEntity pedido) {
        PersonaEntity persona = personaRepository.findById(personaId)
                .orElseThrow(() -> new NoSuchElementException("Persona no encontrada"));

        // Evitar un ciclo infinito de guardado de pedidos
        if (pedido.getPersona() == null) {
            pedido.setPersona(persona);
        }

        // Guardamos solo el pedido sin guardar innecesariamente la persona
        return pedidoRepository.save(pedido);
    }

    @Override
    public List<PedidoEntity> buscarTodos(String estado) {
        return pedidoRepository.findByEstado(estado);
    }

    @Override
    public PedidoEntity buscarPedidoPorId(Long id) {
        return pedidoRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Pedido no encontrado"));
    }

    @Override
    public List<PedidoEntity> buscarPedidoPorEstado(String estado) {
        return pedidoRepository.findByEstado(estado);
    }

    @Override
    public PedidoEntity actualizarPedido(Long id, Long personaId, PedidoEntity pedidoActualizado) {
        PedidoEntity pedidoExistente = pedidoRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Pedido no encontrado"));

        PersonaEntity nuevaPersona = personaRepository.findById(personaId)
                .orElseThrow(() -> new NoSuchElementException("Persona no encontrada"));

        pedidoExistente.setDescripcion(pedidoActualizado.getDescripcion());
        pedidoExistente.setCantidad(pedidoActualizado.getCantidad());
        pedidoExistente.setEstado(pedidoActualizado.getEstado());
        pedidoExistente.setPersona(nuevaPersona);

        return pedidoRepository.save(pedidoExistente);
    }

    @Override
    public void eliminarPedido(Long id) {
        PedidoEntity pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Pedido no encontrado"));
        pedido.setEstado(Constants.PedidoEstado.ELIMINADO);
        pedidoRepository.save(pedido);
    }
}