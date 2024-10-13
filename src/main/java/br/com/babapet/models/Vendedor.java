package br.com.babapet.models;

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
    private String descricaoDaLoja;
    private Boolean Status;

}
