package br.com.babapet.models.Vendedor;

public record VendedorRessponse(String nome, String email) {
    public VendedorRessponse(Vendedor vendedor) {
        this(vendedor.getNome(), vendedor.getEmail());
    }
}
