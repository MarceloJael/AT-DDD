package com.petfriends.almoxarifado.infra.repository;

import com.petfriends.almoxarifado.domain.Estoque;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstoqueRepository extends JpaRepository<Estoque, Long> {

    Estoque findByProdutoId(Long produtoId);
}