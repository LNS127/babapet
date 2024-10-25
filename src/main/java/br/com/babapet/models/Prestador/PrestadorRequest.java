package br.com.babapet.models.Prestador;

import br.com.babapet.models.Endereco;

public record PrestadorRequest(String cpf,
                               String rg,
                               String orgaoEmissorRg,
                               String nome,
                               String email,
                               String telefone,
                               Endereco endereco,
                               String dataDeNascimento,
                               boolean status,
                               String imagemRg,
                               String comprovanteDeResidencia) {

    public Prestador toPrestador() {
        return new Prestador(this.cpf,
                this.rg,
                this.orgaoEmissorRg,
                this.nome,
                this.email,
                this.endereco,
                this.telefone,
                this.dataDeNascimento,
                true,
                this.imagemRg,
                this.comprovanteDeResidencia);
    }

}
