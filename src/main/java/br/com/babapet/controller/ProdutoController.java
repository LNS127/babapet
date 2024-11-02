package br.com.babapet.controller;

import br.com.babapet.models.produto.Produto;
import br.com.babapet.models.produto.ProdutoRequest;
import br.com.babapet.models.produto.ProdutoResponse;
import br.com.babapet.repositories.ProdutoRepository;
import br.com.babapet.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    // Criar um novo produto
    @PostMapping
    public ResponseEntity<ProdutoResponse> criarProduto(@RequestBody ProdutoRequest produto) {
        Produto novoProduto = produtoService.criarProduto(produto.toProduto());
        return ResponseEntity.ok(new ProdutoResponse(novoProduto));
    }

    // Listar todos os produtos
    @GetMapping
    public ResponseEntity<List<ProdutoResponse>> listarProdutos() {
        List<Produto> produtos = produtoService.listarProdutos();
        List<ProdutoResponse> produtosResponse = produtos.stream().map(ProdutoResponse::new).toList();
        return ResponseEntity.ok(produtosResponse);
    }
    // Buscar um produto por código de barras
    @GetMapping("/{codigoDeBarras}")
    public ResponseEntity<ProdutoResponse> buscarProduto(@PathVariable String codigoDeBarras) {
        Optional<Produto> produto = Optional.ofNullable(produtoService.buscarProdutoPorCodigo(codigoDeBarras));
        return ResponseEntity.of(produto.map(ProdutoResponse::new));
    }

    // Atualizar um produto
    @PutMapping("/{codigoDeBarras}")
    public ResponseEntity<ProdutoResponse> atualizarProduto(@PathVariable String codigoDeBarras, @RequestBody ProdutoRequest produtoAtualizado) {
        Produto produtoSalvo = produtoService.atualizarProduto(codigoDeBarras, produtoAtualizado.toProduto());
        return ResponseEntity.ok(new ProdutoResponse(produtoSalvo));
    }

    // Inativar um produto (definir status como falso)
    @PatchMapping("/{codigoDeBarras}/inativar")
    public ResponseEntity<ProdutoResponse> inativarProduto(@PathVariable String codigoDeBarras) {
        Optional<Produto> produtoExistente = Optional.ofNullable(produtoService.inativarProduto(codigoDeBarras));
        return produtoExistente.map(produto -> ResponseEntity.ok(new ProdutoResponse(produto))).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
