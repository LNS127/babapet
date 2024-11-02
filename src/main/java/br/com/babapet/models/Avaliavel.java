package br.com.babapet.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

public abstract class Avaliavel {
    @OneToMany(cascade = CascadeType.ALL)
    private List<Avaliacao> avaliacoes = new ArrayList<>();

    public void adicionaAvaliacao(Avaliacao avaliacao) {
        avaliacoes.add(avaliacao);
    }
    public double getAvaliacaoMedia() {
        return avaliacoes.isEmpty() ? 0.0:
                avaliacoes.stream().mapToInt(Avaliacao ::getEstrelas).average().orElse(0.0);
    }
    public List<Avaliacao> getAvaliacoes() {
        return avaliacoes;
    }
}
