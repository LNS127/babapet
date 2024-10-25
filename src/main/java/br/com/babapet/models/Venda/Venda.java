package br.com.babapet.models.Venda;

import br.com.babapet.models.Cliente.Cliente;
import br.com.babapet.models.produto.Produto;
import br.com.babapet.models.Vendedor.Vendedor;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
@Table(name = "vendas")
public class Venda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Produto produto;
    @ManyToOne
    private Cliente cliente;
    @ManyToOne
    private Vendedor vendedor;
}
