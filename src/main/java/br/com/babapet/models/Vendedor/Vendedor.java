package br.com.babapet.models.Vendedor;

import br.com.babapet.models.Endereco;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "Vendedores")
public class Vendedor {
    @Id
    private String cpf;
    private String cnpj;
    private String nome;
    private String email;
    private String telefone;
    private Endereco endereco;
    private String descricaoDaLoja;
    private boolean Status;

}
