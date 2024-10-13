package br.com.babapet.controller;

import br.com.babapet.models.Produto;
import br.com.babapet.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    // Criar um novo produto
    @PostMapping
    public ResponseEntity<Produto> criarProduto(@RequestBody Produto produto) {
        Produto novoProduto = produtoRepository.save(produto);
        return ResponseEntity.ok(novoProduto);
    }

    // Listar todos os produtos
    @GetMapping
    public ResponseEntity<List<Produto>> listarProdutos() {
        List<Produto> produtos = produtoRepository.findAll();
        return ResponseEntity.ok(produtos);
    }

    // Buscar um produto por código de barras
    @GetMapping("/{codigoDeBarras}")
    public ResponseEntity<Produto> buscarProduto(@PathVariable String codigoDeBarras) {
        Optional<Produto> produto = produtoRepository.findById(codigoDeBarras);
        return produto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Atualizar um produto
    @PutMapping("/{codigoDeBarras}")
    public ResponseEntity<Produto> atualizarProduto(@PathVariable String codigoDeBarras, @RequestBody Produto produtoAtualizado) {
        if (!produtoRepository.existsById(codigoDeBarras)) {
            return ResponseEntity.notFound().build();
        }
        produtoAtualizado.setCodigoDeBarras(codigoDeBarras);
        Produto produtoSalvo = produtoRepository.save(produtoAtualizado);
        return ResponseEntity.ok(produtoSalvo);
    }

    // Inativar um produto (definir status como falso)
    @PatchMapping("/{codigoDeBarras}/inativar")
    public ResponseEntity<Produto> inativarProduto(@PathVariable String codigoDeBarras) {
        Optional<Produto> produtoExistente = produtoRepository.findById(codigoDeBarras);
        if (!produtoExistente.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Produto produto = produtoExistente.get();
        produto.setStatus(false);
        produtoRepository.save(produto);
        return ResponseEntity.ok(produto);
    }
}
