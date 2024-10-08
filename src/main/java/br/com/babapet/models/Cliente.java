package br.com.babapet.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

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
    @Column(unique = true)
    private String email;
    @Embedded
    private Endereco endereco;
    @Column(unique = true)
    private List<String> telefone;
    private String dataDeNascimento;

}
