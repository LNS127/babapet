package br.com.babapet.models.Cliente;

public record ClienteResponse(String nome, String email) {
    public ClienteResponse(Cliente cliente) {
        this(cliente.getNome(), cliente.getEmail());
    }
}
