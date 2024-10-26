package br.com.babapet.controller;

import br.com.babapet.models.produto.Produto;
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
    public ResponseEntity<Produto> criarProduto(@RequestBody Produto produto) {
        Produto novoProduto = produtoService.criarProduto(produto);
        return ResponseEntity.ok(novoProduto);
    }

    // Listar todos os produtos
    @GetMapping
    public ResponseEntity<List<Produto>> listarProdutos() {
        List<Produto> produtos = produtoService.listarProdutos();
        return ResponseEntity.ok(produtos);
    }

    // Buscar um produto por código de barras
    @GetMapping("/{codigoDeBarras}")
    public ResponseEntity<Produto> buscarProduto(@PathVariable String codigoDeBarras) {
        Optional<Produto> produto = Optional.ofNullable(produtoService.buscarProdutoPorCodigo(codigoDeBarras));
        return produto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Atualizar um produto
    @PutMapping("/{codigoDeBarras}")
    public ResponseEntity<Produto> atualizarProduto(@PathVariable String codigoDeBarras, @RequestBody Produto produtoAtualizado) {
        if (!produtoService.existsById(codigoDeBarras)) {
            return ResponseEntity.notFound().build();
        }
        produtoAtualizado.setCodigoDeBarras(codigoDeBarras);
        Produto produtoSalvo = produtoService.atualizarProduto(codigoDeBarras, produtoAtualizado);
        return ResponseEntity.ok(produtoSalvo);
    }

    // Inativar um produto (definir status como falso)
    @PatchMapping("/{codigoDeBarras}/inativar")
    public ResponseEntity<Produto> inativarProduto(@PathVariable String codigoDeBarras) {
        Optional<Produto> produtoExistente = Optional.ofNullable(produtoService.inativarProduto(codigoDeBarras));
        if (!produtoExistente.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Produto produto = produtoExistente.get();
        produto.setStatus(false);
        produtoService.inativarProduto(codigoDeBarras);
        return ResponseEntity.ok(produto);
    }
}
