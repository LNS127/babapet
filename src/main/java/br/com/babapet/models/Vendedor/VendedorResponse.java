package br.com.babapet.models.Vendedor;

public record VendedorResponse(String nome, String email) {
    public VendedorResponse(Vendedor vendedor) {
        this(vendedor.getNome(), vendedor.getEmail());
    }
}
