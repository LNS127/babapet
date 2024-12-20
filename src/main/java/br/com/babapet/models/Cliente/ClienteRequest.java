package br.com.babapet.models.Cliente;

import br.com.babapet.models.Endereco;

import java.time.LocalDate;
import java.util.List;

public record ClienteRequest(String cpf,
                             String rg,
                             String orgaoEmissorRg,
                             String nome,
                             String email,
                             Endereco endereco,
                             List<String> telefone,
                             LocalDate DataNascimento,
                             Boolean status) {
    public Cliente toCliente() {
        return new Cliente(this.cpf, this.rg, this.orgaoEmissorRg, this.nome, this.email, this.endereco, this.telefone, this.DataNascimento, true);
    }
}
