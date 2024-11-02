package br.com.babapet.models.Prestador;

import br.com.babapet.models.Avaliavel;
import br.com.babapet.models.Endereco;
import jakarta.persistence.Embedded;
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
@Table(name = "prestadores")
public class Prestador extends Avaliavel {
    @Id
    private String cpf;
    private String rg;
    private String orgaoEmissorRg;
    private String nome;
    private String email;
    @Embedded
    private Endereco endereco;
    private String telefone;
    private String dataDeNascimento;
    private Boolean Status = true;
    private String imagemRg;
    private String comprovanteDeResidencia;

}

