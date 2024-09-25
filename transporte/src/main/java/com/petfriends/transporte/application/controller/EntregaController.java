package com.petfriends.transporte.application.controller;

import com.petfriends.transporte.domain.Entrega;
import com.petfriends.transporte.domain.StatusEntrega;
import com.petfriends.transporte.infra.service.EntregaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/entregas")
public class EntregaController {

    private final EntregaService entregaService;

    @Autowired
    public EntregaController(EntregaService entregaService) {
        this.entregaService = entregaService;
    }

    @PostMapping
    public Entrega criarEntrega(@RequestBody Entrega entrega) {
        return entregaService.criarEntrega(entrega);
    }

    @PatchMapping("/{pedidoId}/status")
    public Entrega atualizarStatus(@PathVariable Long pedidoId, @RequestParam StatusEntrega status) {
        return entregaService.atualizarStatus(pedidoId, status);
    }

    @GetMapping
    public List<Entrega> listarEntregas() {
        return entregaService.obterTodasEntregas();
    }
}