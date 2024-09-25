package com.petfriends.almoxarifado.application.controller;

import com.petfriends.almoxarifado.domain.Estoque;
import com.petfriends.almoxarifado.domain.Quantidade;
import com.petfriends.almoxarifado.domain.TipoMovimento;
import com.petfriends.almoxarifado.infra.service.EstoqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/estoques")
public class EstoqueController {

    private final EstoqueService estoqueService;

    @Autowired
    public EstoqueController(EstoqueService estoqueService) {
        this.estoqueService = estoqueService;
    }

    @PostMapping
    public Estoque criarEstoque(@RequestBody Estoque estoque) {
        return estoqueService.criarEstoque(estoque);
    }

    @PatchMapping("/{produtoId}/atualizar-quantidade")
    public Estoque atualizarQuantidade(
            @PathVariable Long produtoId,
            @RequestParam BigDecimal quantidade,
            @RequestParam TipoMovimento tipoMovimento) {
        return estoqueService.atualizarQuantidade(produtoId, new Quantidade(quantidade), tipoMovimento);
    }

    @GetMapping
    public List<Estoque> listarEstoques() {
        return estoqueService.obterTodosEstoques();
    }
}