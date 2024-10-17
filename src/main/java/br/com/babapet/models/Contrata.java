package br.com.babapet.models;

import br.com.babapet.models.Cliente.Cliente;
import br.com.babapet.models.Pet.Pet;
import br.com.babapet.models.Prestador.Prestador;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class Contrata {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Cliente cliente;
    @ManyToOne
    private Pet pet;
    @ManyToOne
    private Prestador prestador;
    @ManyToOne
    private Servico servico;
}
