package br.com.babapet.models.Cliente;

public record ClienteResponse(String Nome, String Email) {
       public ClienteResponse(Cliente cliente) {
           this(cliente.getNome(), cliente.getEmail());
       }
}

