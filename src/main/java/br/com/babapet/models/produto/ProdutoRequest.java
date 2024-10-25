package br.com.babapet.models.produto;

public record ProdutoRequest(String codigoDeBarras, String nome,  double valor, String tipo, String descricao, boolean status) {
    public Produto toProduto() {
        return new Produto(codigoDeBarras, nome, valor, tipo, descricao, status);
    }

}
