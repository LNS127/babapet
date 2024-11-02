package br.com.babapet.models;

import br.com.babapet.models.Cliente.Cliente;
import jakarta.persistence.*;

@Entity
public class Avaliacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Cliente cliente;

    private int estrelas;

    public Avaliacao() {}

    public Avaliacao(Cliente cliente, int estrelas) {
        this.cliente = cliente;
        this.estrelas = estrelas;
    }
    public Cliente getCliente() {
        return cliente;
    }
    public int getEstrelas() {
        return estrelas;
    }
}
