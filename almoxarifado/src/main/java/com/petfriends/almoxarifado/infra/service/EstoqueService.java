package com.petfriends.almoxarifado.infra.service;

import com.petfriends.almoxarifado.domain.Estoque;
import com.petfriends.almoxarifado.domain.MovimentoEstoque;
import com.petfriends.almoxarifado.domain.Quantidade;
import com.petfriends.almoxarifado.domain.TipoMovimento;
import com.petfriends.almoxarifado.infra.repository.EstoqueRepository;
import com.petfriends.almoxarifado.infra.repository.MovimentoEstoqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstoqueService {

    private final EstoqueRepository estoqueRepository;
    private final MovimentoEstoqueRepository movimentoEstoqueRepository;

    @Autowired
    public EstoqueService(EstoqueRepository estoqueRepository, MovimentoEstoqueRepository movimentoEstoqueRepository) {
        this.estoqueRepository = estoqueRepository;
        this.movimentoEstoqueRepository = movimentoEstoqueRepository;
    }

    public Estoque criarEstoque(Estoque estoque) {
        return estoqueRepository.save(estoque);
    }

    public Estoque atualizarQuantidade(Long produtoId, Quantidade quantidade, TipoMovimento tipoMovimento) {
        Estoque estoque = estoqueRepository.findByProdutoId(produtoId);
        if (estoque == null) {
            throw new IllegalArgumentException("Produto não encontrado no estoque");
        }

        Quantidade novaQuantidade;
        if (tipoMovimento == TipoMovimento.ENTRADA) {
            novaQuantidade = estoque.getQuantidade().adicionar(quantidade);
        } else {
            novaQuantidade = estoque.getQuantidade().subtrair(quantidade);
            if (novaQuantidade.getValor().compareTo(quantidade.getValor()) < 0) {
                throw new IllegalArgumentException("Estoque insuficiente");
            }
        }

        estoque.setQuantidade(novaQuantidade);
        estoqueRepository.save(estoque);

        MovimentoEstoque movimento = new MovimentoEstoque(estoque, quantidade, tipoMovimento);
        movimentoEstoqueRepository.save(movimento);

        return estoque;
    }

    public List<Estoque> obterTodosEstoques() {
        return estoqueRepository.findAll();
    }
}