package br.com.babapet.models;

import jakarta.persistence.Id;

public class Produto {
    @Id
    private String codigoDeBarras;
    private double valor;
    private String tipo;
    private String descricao;
    private boolean status;
}
