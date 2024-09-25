package com.petfriends.transporte.infra.service;

import com.petfriends.transporte.domain.Entrega;
import com.petfriends.transporte.domain.StatusEntrega;
import com.petfriends.transporte.infra.repository.EntregaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EntregaService {

    private final EntregaRepository entregaRepository;

    @Autowired
    public EntregaService(EntregaRepository entregaRepository) {
        this.entregaRepository = entregaRepository;
    }

    public Entrega criarEntrega(Entrega entrega) {
        return entregaRepository.save(entrega);
    }

    public Entrega atualizarStatus(Long pedidoId, StatusEntrega status) {
        Entrega entrega = entregaRepository.findByPedidoId(pedidoId);
        if (entrega == null) {
            throw new IllegalArgumentException("Entrega não encontrada para o pedido: " + pedidoId);
        }
        entrega.setStatus(status);
        return entregaRepository.save(entrega);
    }

    public List<Entrega> obterTodasEntregas() {
        return entregaRepository.findAll();
    }
}
