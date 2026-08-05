package com.seuprojeto.cadastroprodutos.service;

import com.seuprojeto.cadastroprodutos.Produto;
import com.seuprojeto.cadastroprodutos.Strategy.Desconto10Strategy;
import com.seuprojeto.cadastroprodutos.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProdutoService {
    @Autowired
    private Desconto10Strategy descontoStrategy;

    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public List<Produto> listarTodos() {
        return produtoRepository.findAll();
    }

    public Produto buscarPorId(Long id) {
        return produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
    }

    public Produto salvar(Produto produto) {

        validarProduto(produto);

        return produtoRepository.save(produto);
    }


    public Produto atualizar(Long id, Produto produtoAtualizado) {

        Produto produto = buscarPorId(id);

        validarProduto(produtoAtualizado);

        produto.setNome(produtoAtualizado.getNome());
        produto.setPreco(produtoAtualizado.getPreco());

        return produtoRepository.save(produto);
    }


    public void excluir(Long id) {

        Produto produto = buscarPorId(id);

        produtoRepository.delete(produto);
    }


    private void validarProduto(Produto produto) {

        if (produto.getNome() == null || produto.getNome().isBlank()) {
            throw new RuntimeException("Nome obrigatório");
        }


        if (produto.getPreco() == null || produto.getPreco() <= 0) {
            throw new RuntimeException("Preço deve ser maior que zero");
        }
    }
    public Double calcularPrecoComDesconto(Long id) {

        Produto produto = produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        return descontoStrategy.calcularDesconto(produto.getPreco());

    }
}