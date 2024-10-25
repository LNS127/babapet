package br.com.babapet.models.Venda;

import br.com.babapet.models.Cliente.Cliente;
import br.com.babapet.models.produto.Produto;
import br.com.babapet.models.Vendedor.Vendedor;

    public record VendaResponse(Long id, Produto produto, Cliente cliente, Vendedor vendedor) {
        public VendaResponse(Venda venda) {
            this(venda.getId(), venda.getProduto(), venda.getCliente(), venda.getVendedor());}
}
