package com.petfriends.almoxarifado.domain;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Objects;

@Setter
@Getter
@Embeddable
public class Quantidade {

    private BigDecimal valor;

    public Quantidade() {
    }

    public Quantidade(BigDecimal valor) {
        if (valor.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("A quantidade não pode ser negativa");
        }
        this.valor = valor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Quantidade that = (Quantidade) o;
        return Objects.equals(valor, that.valor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(valor);
    }

    @Override
    public String toString() {
        return "Quantidade{" +
                "valor=" + valor +
                '}';
    }

    public Quantidade adicionar(Quantidade outra) {
        return new Quantidade(this.valor.add(outra.valor));
    }

    public Quantidade subtrair(Quantidade outra) {
        return new Quantidade(this.valor.subtract(outra.valor));
    }
}