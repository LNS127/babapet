package br.com.babapet.models.produto;

public record ProdutoResponse(String nome, double valor, String tipo, String descricao) {

    public ProdutoResponse(Produto produto) {
        this(produto.getNome(),produto.getValor(), produto.getTipo(), produto.getDescricao());
    }
}
