package com.petfriends.almoxarifado.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Getter
@Entity
@Table(name = "movimento_estoque", schema = "almoxarifado")
public class MovimentoEstoque implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @ManyToOne
    @JoinColumn(name = "estoque_id", nullable = false)
    private Estoque estoque;

    @Setter
    @Embedded
    private Quantidade quantidadeMovimentada;

    @Setter
    @Column(name = "data_movimento", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataMovimento;

    @Setter
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_movimento", nullable = false)
    private TipoMovimento tipoMovimento;

    public MovimentoEstoque() {
    }

    public MovimentoEstoque(Estoque estoque, Quantidade quantidadeMovimentada, TipoMovimento tipoMovimento) {
        this.estoque = estoque;
        this.quantidadeMovimentada = quantidadeMovimentada;
        this.tipoMovimento = tipoMovimento;
        this.dataMovimento = new Date();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MovimentoEstoque that = (MovimentoEstoque) o;
        return Objects.equals(id, that.id) && Objects.equals(estoque, that.estoque);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, estoque);
    }

    @Override
    public String toString() {
        return "MovimentoEstoque{" +
                "id=" + id +
                ", estoque=" + estoque +
                ", quantidadeMovimentada=" + quantidadeMovimentada +
                ", dataMovimento=" + dataMovimento +
                ", tipoMovimento=" + tipoMovimento +
                '}';
    }
}
