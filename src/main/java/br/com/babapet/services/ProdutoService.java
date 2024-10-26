package br.com.babapet.services;

import br.com.babapet.models.produto.Produto;
import br.com.babapet.repositories.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    // Injeção de dependência pelo construtor
    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    // Listar todos os produtos
    public List<Produto> listarProdutos() {
        List<Produto> produtos = produtoRepository.findAll();
        if (produtos.isEmpty()) {
            throw new IllegalStateException("Nenhum produto encontrado.");
        }
        return produtos;
    }

    // Buscar produto por código de barras
    public Produto buscarProdutoPorCodigo(String codigoDeBarras) {
        return produtoRepository.findById(codigoDeBarras)
                .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado."));
    }

    // Criar novo produto
    public Produto criarProduto(Produto produto) {
        if (produtoRepository.existsById(produto.getCodigoDeBarras())) {
            throw new IllegalStateException("Produto com este código de barras já está cadastrado.");
        }
        validarProduto(produto);
        return produtoRepository.save(produto);
    }

    // Atualizar produto
    public Produto atualizarProduto(String codigoDeBarras, Produto produtoAtualizado) {
        Produto produtoExistente = buscarProdutoPorCodigo(codigoDeBarras);

        produtoAtualizado.setCodigoDeBarras(produtoExistente.getCodigoDeBarras()); // Mantém o código de barras original
        validarProduto(produtoAtualizado);

        return produtoRepository.save(produtoAtualizado);
    }

    // Inativar produto (exclusão lógica)
    public Produto inativarProduto(String codigoDeBarras) {
        Produto produto = buscarProdutoPorCodigo(codigoDeBarras);
        produto.setStatus(false); // Define o status como inativo
        return produtoRepository.save(produto);
    }

    // Método para validar os dados do produto
    private void validarProduto(Produto produto) {
        if (produto.getNome() == null || produto.getNome().isEmpty()) {
            throw new IllegalArgumentException("O nome do produto é obrigatório.");
        }
        if (produto.getValor() <= 0) {
            throw new IllegalArgumentException("O valor do produto deve ser maior que zero.");
        }
        if (produto.getTipo() == null || produto.getTipo().isEmpty()) {
            throw new IllegalArgumentException("O tipo do produto é obrigatório.");
        }
    }

    public boolean existsById(String codigoDeBarras) {
        if (!produtoRepository.existsById(codigoDeBarras)) {
            return false;
        }
        return true;
    }
}
