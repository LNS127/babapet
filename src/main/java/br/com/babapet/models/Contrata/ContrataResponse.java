package br.com.babapet.models.Contrata;

import br.com.babapet.models.Cliente.Cliente;
import br.com.babapet.models.Prestador.Prestador;
import br.com.babapet.models.servico.Servico;

public record ContrataResponse(Cliente cliente, Prestador prestador, Servico servico) {
    public ContrataResponse(Contrata contrata) {
        this(contrata.getCliente(), contrata.getPrestador(), contrata.getServico());
    }
}
