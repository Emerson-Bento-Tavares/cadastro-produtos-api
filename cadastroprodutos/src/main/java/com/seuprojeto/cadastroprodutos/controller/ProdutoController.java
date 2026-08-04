package com.seuprojeto.cadastroprodutos.controller;

import com.seuprojeto.cadastroprodutos.Produto;
import com.seuprojeto.cadastroprodutos.service.ProdutoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }


    @GetMapping
    public List<Produto> listarProdutos() {
        return produtoService.listarTodos();
    }


    @PostMapping
    public Produto cadastrarProduto(@RequestBody Produto produto) {
        return produtoService.salvar(produto);
    }


    @GetMapping("/{id}")
    public Produto buscarPorId(@PathVariable Long id) {
        return produtoService.buscarPorId(id);
    }


    @PutMapping("/{id}")
    public Produto atualizarProduto(
            @PathVariable Long id,
            @RequestBody Produto produto) {

        return produtoService.atualizar(id, produto);
    }


    @DeleteMapping("/{id}")
    public void excluirProduto(@PathVariable Long id) {
        produtoService.excluir(id);
    }
}
