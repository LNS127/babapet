package br.com.babapet.models;

import jakarta.persistence.ManyToOne;

public class Venda {
    @ManyToOne
    private Produto produto;
    @ManyToOne
    private Cliente cliente;
    @ManyToOne
    private Vendedor Vendedor;
}
