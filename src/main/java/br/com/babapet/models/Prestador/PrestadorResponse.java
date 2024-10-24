package br.com.babapet.models.Prestador;

public record PrestadorResponse(String nome, String email) {
    public PrestadorResponse(Prestador prestador) {
        this(prestador.getNome(), prestador.getEmail());
    }
}
