package br.com.babapet.models.Cliente;

import br.com.babapet.models.Endereco;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
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
    private String orgaoEmissorRg;
    private String nome;
    @Column(unique = true)
    private String email;
    @Embedded
    private Endereco endereco;
    @Column(unique = true)
    private List<String> telefone;
    private LocalDate dataDeNascimento;
    private boolean status;

}
