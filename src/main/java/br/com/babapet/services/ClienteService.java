package br.com.babapet.services;

import br.com.babapet.models.Cliente;
import br.com.babapet.repositories.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {
    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    // Listar Clientes
    public List<Cliente> listarCliente() {
        List<Cliente> clientes = clienteRepository.findAll();
        if (clientes.isEmpty()) {
            throw new RuntimeException("Nenhum cliente encontrado.");
        }
        return clientes;
    }

    // Buscar Cliente por CPF
    public Optional<Cliente> buscarClientePorCpf(String cpf) {
        if (!validarCpf(cpf)) {
            throw new RuntimeException("CPF inválido.");
        }
        Optional<Cliente> cliente = clienteRepository.findById(cpf);
        if (!cliente.isPresent()) {
            throw new RuntimeException("Cliente não encontrado.");
        }
        return cliente;
    }

    // Criar Cliente
    public Cliente criarCliente(Cliente cliente) {
        if (!validarCpf(cliente.getCpf())) {
            throw new RuntimeException("CPF inválido.");
        }
        if (clienteRepository.findById(cliente.getCpf()).isPresent()) {
            throw new RuntimeException("Cliente com este CPF já está cadastrado.");
        }
        validarDadosCliente(cliente); // Validação adicional dos dados
        return clienteRepository.save(cliente);
    }

    // Atualizar Cliente
    public Cliente atualizarCliente(String cpf, Cliente clienteAtualizado) {
        if (!validarCpf(cpf)) {
            throw new RuntimeException("CPF inválido.");
        }
        Optional<Cliente> clienteExistente = clienteRepository.findById(cpf);
        if (!clienteExistente.isPresent()) {
            throw new RuntimeException("Cliente com este CPF não encontrado.");
        }
        validarDadosCliente(clienteAtualizado); // Validação adicional dos dados
        return clienteRepository.save(clienteAtualizado);
    }

    // Deletar Cliente
    public void deletarCliente(String cpf) {
        if (!validarCpf(cpf)) {
            throw new RuntimeException("CPF inválido.");
        }
        Optional<Cliente> clienteExistente = clienteRepository.findById(cpf);
        if (!clienteExistente.isPresent()) {
            throw new RuntimeException("Cliente com este CPF não existe.");
        }
        clienteRepository.deleteById(cpf);
    }

    // Método para validar o formato do CPF
    private boolean validarCpf(String cpf) {
        return cpf != null && cpf.matches("\\d{11}");
    }

    // Método para validar dados do cliente
    private void validarDadosCliente(Cliente cliente) {
        if (cliente.getNome() == null || cliente.getNome().isEmpty()) {
            throw new RuntimeException("Nome do cliente é obrigatório.");
        }

    }

}

