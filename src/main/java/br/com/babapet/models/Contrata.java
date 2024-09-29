package br.com.babapet.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
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
    @ManyToOne
    private Cliente cliente;
    @ManyToOne
    private Pet pet;
    @ManyToOne
    private Prestador prestador;
    @ManyToOne
    private Servico servico;
}
