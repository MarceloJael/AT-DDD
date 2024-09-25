package com.petfriends.transporte.infra.repository;

import com.petfriends.transporte.domain.Entrega;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntregaRepository extends JpaRepository<Entrega, Long> {

    Entrega findByPedidoId(Long pedidoId);
}