package br.com.babapet.models.produto;

import br.com.babapet.models.Avaliavel;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "Produtos")
public class Produto extends Avaliavel {
    @Id
    private String codigoDeBarras;
    private String nome;
    private double valor;
    private String tipo;
    private String descricao;
    private boolean status;
}
