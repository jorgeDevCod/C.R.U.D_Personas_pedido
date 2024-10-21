package com.MyApiRest.TareaApi.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "persona")
@Getter
@Setter
public class PersonaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombres;
    private String apellidos;
    private Long numeroDocumento;
    private String estado;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "direccion_id")
    private DireccionEntity direccion;

    @OneToMany(mappedBy = "persona")
    @JsonManagedReference
    private List<PedidoEntity> pedidos = new ArrayList<>();

    // Ajuste en el método auxiliar
    public void addPedido(PedidoEntity pedido) {
        if (!pedidos.contains(pedido)) { // Evita agregar múltiples veces
            pedidos.add(pedido);
            pedido.setPersona(this);
        }
    }
}
