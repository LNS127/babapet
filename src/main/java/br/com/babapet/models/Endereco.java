package br.com.babapet.models;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class Endereco {
    private String estado;
    private String bairro;
    private String rua;
    private String cep;
    private String cidade;
}
