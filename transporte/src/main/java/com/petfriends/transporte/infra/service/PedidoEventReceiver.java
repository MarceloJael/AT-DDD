package com.petfriends.transporte.infra.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.petfriends.transporte.domain.Endereco;
import com.petfriends.transporte.domain.Entrega;
import com.petfriends.transporte.infra.repository.EntregaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class PedidoEventReceiver {

    private final EntregaService entregaService;
    private final ObjectMapper objectMapper;

    @Autowired
    public PedidoEventReceiver(EntregaService entregaService, ObjectMapper objectMapper) {
        this.entregaService = entregaService;
        this.objectMapper = objectMapper;
    }

    public void receberEventoPedido(String message) {
        try {
            Map<String, Object> evento = objectMapper.readValue(message, Map.class);

            Long pedidoId = Long.valueOf(evento.get("pedidoId").toString());
            String rua = evento.get("rua").toString();
            String numero = evento.get("numero").toString();
            String cidade = evento.get("cidade").toString();
            String estado = evento.get("estado").toString();
            String cep = evento.get("cep").toString();

            Endereco enderecoEntrega = new Endereco(rua, numero, cidade, estado, cep);
            Entrega entrega = new Entrega(pedidoId, enderecoEntrega);

            entregaService.criarEntrega(entrega);

            System.out.println("Evento de pedido processado com sucesso para transporte: " + evento);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Erro ao processar evento de pedido: " + e.getMessage());
        }
    }
}