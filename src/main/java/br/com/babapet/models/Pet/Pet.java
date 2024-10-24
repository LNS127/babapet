package br.com.babapet.models.Pet;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "pets") // Tabela em minúsculas, plural
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Definindo id como chave primária

    @Column(columnDefinition = "varchar(255) default 'indefinido'")
    private String raca; // Raca não é mais a chave primária

    private String nome;
    private String sexo;
    private String tipo;
}
