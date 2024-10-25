package br.com.babapet.services;

import br.com.babapet.models.Vendedor.Vendedor;
import br.com.babapet.repositories.VendedorRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class VendedorService {

    private final VendedorRepository vendedorRepository;

    public VendedorService(VendedorRepository vendedorRepository) {
        this.vendedorRepository = vendedorRepository;
    }

    // Listar Vendedores
    public List<Vendedor> listarVendedores() {
        List<Vendedor> vendedores = vendedorRepository.findAll();
        if (vendedores.isEmpty()) {
            throw new RuntimeException("Nenhum vendedor encontrado.");
        }
        return vendedores;
    }

    // Buscar Vendedor por CPF (ID)
    public Optional<Vendedor> buscarVendedorPorId(String cpf) {
        Optional<Vendedor> vendedor = vendedorRepository.findById(cpf);
        if (!vendedor.isPresent()) {
            throw new RuntimeException("Vendedor não encontrado.");
        }
        return vendedor;
    }

    // Criar Vendedor
    public Vendedor criarVendedor(Vendedor vendedor) {
        validarDadosVendedor(vendedor); // Validação adicional dos dados
        return vendedorRepository.save(vendedor);
    }

    // Atualizar Vendedor
    public Vendedor atualizarVendedor(String cpf, Vendedor vendedorAtualizado) {
        Optional<Vendedor> vendedorExistente = vendedorRepository.findById(cpf);
        if (!vendedorExistente.isPresent()) {
            throw new RuntimeException("Vendedor com este CPF não encontrado.");
        }
        validarDadosVendedor(vendedorAtualizado); // Validação adicional dos dados
        vendedorAtualizado.setCpf(cpf); // Garantir que o CPF não é alterado
        return vendedorRepository.save(vendedorAtualizado);
    }

    // Deletar Vendedor
    public void deletarVendedor(String cpf) {
        Optional<Vendedor> vendedorExistente = vendedorRepository.findById(cpf);
        if (!vendedorExistente.isPresent()) {
            throw new RuntimeException("Vendedor com este CPF não existe.");
        }
        vendedorRepository.deleteById(cpf);
    }

    // Método para validar dados do vendedor
    private void validarDadosVendedor(Vendedor vendedor) {
        if (vendedor.getCpf() == null || vendedor.getCpf().isEmpty()) {
            throw new RuntimeException("CPF do vendedor é obrigatório.");
        }
        if (vendedor.getCnpj() == null || vendedor.getCnpj().isEmpty()) {
            throw new RuntimeException("CNPJ do vendedor é obrigatório.");
        }
        if (vendedor.getNome() == null || vendedor.getNome().isEmpty()) {
            throw new RuntimeException("Nome do vendedor é obrigatório.");
        }
        if (vendedor.getEmail() == null || vendedor.getEmail().isEmpty()) {
            throw new RuntimeException("E-mail do vendedor é obrigatório.");
        }
        if (vendedor.getTelefone() == null || vendedor.getTelefone().isEmpty()) {
            throw new RuntimeException("Telefone do vendedor é obrigatório.");
        }
        if (vendedor.getDescricaoDaLoja() == null || vendedor.getDescricaoDaLoja().isEmpty()) {
            throw new RuntimeException("Descrição da loja é obrigatória.");
        }
    }
}