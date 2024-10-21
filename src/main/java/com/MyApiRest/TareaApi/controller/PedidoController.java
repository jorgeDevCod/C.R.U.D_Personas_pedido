package com.MyApiRest.TareaApi.controller;

import com.MyApiRest.TareaApi.entity.PedidoEntity;
import com.MyApiRest.TareaApi.service.PedidoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos/v1")
public class PedidoController {

    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @PostMapping("/crear/{personaId}")
    public ResponseEntity<PedidoEntity> crearPedido(
            @PathVariable Long personaId,
            @RequestBody PedidoEntity pedido) {
        return new ResponseEntity<>(
                pedidoService.crearPedido(personaId, pedido),
                HttpStatus.CREATED
        );
    }

    @GetMapping("/buscarTodos")
    public ResponseEntity<List<PedidoEntity>> buscarTodos(@RequestParam String estado) {
        return new ResponseEntity<>(pedidoService.buscarTodos(estado), HttpStatus.OK);
    }

    @GetMapping("/buscarPedidoPorParametro")
    public ResponseEntity<?> buscarPedidoPorParametro(
            @RequestParam(required = false) Long id,
            @RequestParam(required = false) String estado) {
        if (id != null) {
            return new ResponseEntity<>(pedidoService.buscarPedidoPorId(id), HttpStatus.OK);
        } else if (estado != null) {
            return new ResponseEntity<>(pedidoService.buscarPedidoPorEstado(estado), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/actualizarPedido/{id}/persona/{personaId}")
    public ResponseEntity<PedidoEntity> actualizarPedido(
            @PathVariable Long id,
            @PathVariable Long personaId,
            @RequestBody PedidoEntity pedido) {
        return new ResponseEntity<>(
                pedidoService.actualizarPedido(id, personaId, pedido),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/eliminarPedido/{id}")
    public ResponseEntity<Void> eliminarPedido(@PathVariable Long id) {
        pedidoService.eliminarPedido(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}