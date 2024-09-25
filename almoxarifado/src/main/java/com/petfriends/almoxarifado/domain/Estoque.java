package com.petfriends.almoxarifado.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Entity
@Table(name = "estoque", schema = "almoxarifado")
public class Estoque implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Column(name = "produto_id", nullable = false)
    private Long produtoId;

    @Setter
    @Embedded
    private Quantidade quantidade;

    public Estoque() {
    }

    public Estoque(Long produtoId, Quantidade quantidade) {
        this.produtoId = produtoId;
        this.quantidade = quantidade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Estoque estoque = (Estoque) o;
        return Objects.equals(id, estoque.id) && Objects.equals(produtoId, estoque.produtoId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, produtoId);
    }

    @Override
    public String toString() {
        return "Estoque{" +
                "id=" + id +
                ", produtoId=" + produtoId +
                ", quantidade=" + quantidade +
                '}';
    }
}