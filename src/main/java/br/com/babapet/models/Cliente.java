package br.com.babapet.models;

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
@Getter @Setter
@Table(name = "Clientes")
public class Cliente {
    @Id
    private String cpf;
    private String rg;
    private String nome;
    private String email;
    @Embedded
    private Endereco endereco;
    private String telefone;
    private String dataDeNascimento;

}