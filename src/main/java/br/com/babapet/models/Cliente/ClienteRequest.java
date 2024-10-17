package br.com.babapet.models.Cliente;

import br.com.babapet.models.Endereco;

import java.util.List;

public record ClienteRequest(String cpf,
                             String rg,
                             String orgaoEmissorRg,
                             String nome,
                             String email,
                             Endereco endereco,
                             List<String> telefone,
                             String dataDeNascimento,
                             Boolean status) {
    public Cliente tocliente() {
        return new Cliente(this.cpf, this.rg, this.orgaoEmissorRg, this.nome, this.email,this.endereco, this.telefone, this.dataDeNascimento, true);
    }
}
