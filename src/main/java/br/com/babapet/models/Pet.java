package br.com.babapet.models;

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
@Table(name = "Pets")
public class Pet {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)

    @Column(columnDefinition = "varchar(255) default 'indefinido'")
    private String raca;
    private Long id;
    private String nome;
    private String sexo;
    private String tipo;

}
