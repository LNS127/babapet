package br.com.babapet.models.Vendedor;
import br.com.babapet.models.Endereco;
public record VendedorRequest(String cpf,
                              String cnpj,
                              String nome,
                              String email,
                              String telefone,
                              Endereco endereco,
                              String dataDeNascimento,
                              boolean status) {

    public Vendedor toVendedor() {
        return new Vendedor(this.cpf, this.cnpj, this.nome, this.email, this.telefone, this.endereco, this.dataDeNascimento, true);
    }

}
