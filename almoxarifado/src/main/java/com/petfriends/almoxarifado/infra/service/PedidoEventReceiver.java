package com.petfriends.almoxarifado.infra.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.petfriends.almoxarifado.domain.Quantidade;
import com.petfriends.almoxarifado.domain.TipoMovimento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;

@Service
public class PedidoEventReceiver {

    private final EstoqueService estoqueService;
    private final ObjectMapper objectMapper;

    @Autowired
    public PedidoEventReceiver(EstoqueService estoqueService, ObjectMapper objectMapper) {
        this.estoqueService = estoqueService;
        this.objectMapper = objectMapper;
    }

    public void receberEventoPedido(String message) {
        try {
            Map<String, Object> evento = objectMapper.readValue(message, Map.class);

            Long produtoId = Long.valueOf(evento.get("produtoId").toString());
            Double quantidade = Double.valueOf(evento.get("quantidade").toString());
            BigDecimal quantidadeBigDecimal = BigDecimal.valueOf(quantidade);
            estoqueService.atualizarQuantidade(produtoId, new Quantidade(quantidadeBigDecimal), TipoMovimento.SAIDA);

            System.out.println("Evento de pedido processado com sucesso: " + evento);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Erro ao processar evento de pedido: " + e.getMessage());
        }
    }
}