package br.com.babapet.services;

import br.com.babapet.models.Venda;
import br.com.babapet.repositories.ClienteRepository;
import br.com.babapet.repositories.ProdutoRepository;
import br.com.babapet.repositories.VendaRepository;
import br.com.babapet.repositories.VendedorRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class VendaService {

    private final VendaRepository vendaRepository;
    private final ProdutoRepository produtoRepository;
    private final ClienteRepository clienteRepository;
    private final VendedorRepository vendedorRepository;

    public VendaService(VendaRepository vendaRepository,
                        ProdutoRepository produtoRepository,
                        ClienteRepository clienteRepository,
                        VendedorRepository vendedorRepository) {
        this.vendaRepository = vendaRepository;
        this.produtoRepository = produtoRepository;
        this.clienteRepository = clienteRepository;
        this.vendedorRepository = vendedorRepository;
    }

    // Listar Vendas
    public List<Venda> listarVendas() {
        List<Venda> vendas = vendaRepository.findAll();
        if (vendas.isEmpty()) {
            throw new RuntimeException("Nenhuma venda encontrada.");
        }
        return vendas;
    }

    // Buscar Venda por ID
    public Optional<Venda> buscarVendaPorId(Long id) {
        Optional<Venda> venda = vendaRepository.findById(id);
        if (!venda.isPresent()) {
            throw new RuntimeException("Venda não encontrada.");
        }
        return venda;
    }

    // Criar Venda
    public Venda criarVenda(Venda venda) {
        validarDadosVenda(venda); // Validação adicional dos dados
        return vendaRepository.save(venda);
    }

    // Atualizar Venda
    public Venda atualizarVenda(Long id, Venda vendaAtualizada) {
        Optional<Venda> vendaExistente = vendaRepository.findById(id);
        if (!vendaExistente.isPresent()) {
            throw new RuntimeException("Venda com este ID não encontrada.");
        }
        validarDadosVenda(vendaAtualizada); // Validação adicional dos dados
        vendaAtualizada.setId(id); // Garantir que o ID da venda atualizada é o mesmo
        return vendaRepository.save(vendaAtualizada);
    }

    private void validarDadosVenda(Venda vendaAtualizada) {
    }

    // Deletar Venda
    public void deletarVenda(Long id) {
        Optional<Venda> vendaExistente = vendaRepository.findById(id);
        if (!vendaExistente.isPresent()) {
            throw new RuntimeException("Venda com este ID não existe.");
        }
        vendaRepository.deleteById(id);
    }

   
}
