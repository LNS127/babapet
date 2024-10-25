package br.com.babapet.models.Venda;

import br.com.babapet.models.Cliente.Cliente;
import br.com.babapet.models.produto.Produto;
import br.com.babapet.models.Vendedor.Vendedor;

public record VendaRequest(Long id, Produto produto, Cliente cliente, Vendedor vendedor) {
    public Venda toVenda() {
        return new Venda(this.id, this.produto, this.cliente, this.vendedor);
    }
}
