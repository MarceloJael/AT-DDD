package com.petfriends.transporte.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Objects;

@Getter
@Entity
@Table(name = "entregas", schema = "transporte")
public class Entrega {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Column(name = "pedido_id", nullable = false)
    private Long pedidoId;

    @Setter
    @Column(name = "data_envio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataEnvio;

    @Setter
    @Embedded
    private Endereco enderecoEntrega;

    @Setter
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private StatusEntrega status;

    public Entrega() {
    }

    public Entrega(Long pedidoId, Endereco enderecoEntrega) {
        this.pedidoId = pedidoId;
        this.enderecoEntrega = enderecoEntrega;
        this.status = StatusEntrega.PENDENTE;
        this.dataEnvio = new Date();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entrega entrega = (Entrega) o;
        return Objects.equals(id, entrega.id) && Objects.equals(pedidoId, entrega.pedidoId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, pedidoId);
    }

    @Override
    public String toString() {
        return "Entrega{" +
                "id=" + id +
                ", pedidoId=" + pedidoId +
                ", dataEnvio=" + dataEnvio +
                ", enderecoEntrega=" + enderecoEntrega +
                ", status=" + status +
                '}';
    }
}
