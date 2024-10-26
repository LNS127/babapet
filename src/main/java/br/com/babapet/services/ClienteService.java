package br.com.babapet.services;

import br.com.babapet.models.Cliente.Cliente;
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
            throw new IllegalStateException("Nenhum cliente encontrado.");
        }
        return clientes;
    }

    // Buscar Cliente por CPF
    public Cliente buscarClientePorCpf(String cpf) {
        validarCpf(cpf);
        return clienteRepository.findById(cpf)
                .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado."));
    }

    // Criar Cliente
    public Cliente criarCliente(Cliente cliente) {
        validarCpf(cliente.getCpf());

        if (clienteRepository.existsById(cliente.getCpf())) {
            throw new IllegalStateException("Cliente com este CPF já está cadastrado.");
        }

        validarDadosCliente(cliente);
        return clienteRepository.save(cliente);
    }

    // Atualizar Cliente
    public Cliente atualizarCliente(Cliente clienteAtualizado) {
        validarCpf(clienteAtualizado.getCpf());

        Cliente clienteExistente = clienteRepository.findById(clienteAtualizado.getCpf())
                .orElseThrow(() -> new IllegalArgumentException("Cliente com este CPF não encontrado."));

        validarDadosCliente(clienteAtualizado);
        clienteAtualizado.setCpf(clienteExistente.getCpf()); // Mantém o CPF consistente
        return clienteRepository.save(clienteAtualizado);
    }

    // Inativar Cliente (Exclusão Lógica)
    public Cliente inativarCliente(String cpf) {
        validarCpf(cpf);
        Cliente cliente = clienteRepository.findById(cpf)
                .orElseThrow(() -> new IllegalArgumentException("Cliente com este CPF não existe."));
        cliente.setStatus(false); // Definindo o status como inativo
        return clienteRepository.save(cliente);
    }

    // Verificar se Cliente existe por CPF
    public boolean existsByCpf(String cpf) {
        validarCpf(cpf);
        return clienteRepository.existsById(cpf);
    }

    // Método para validar o formato do CPF
    private void validarCpf(String cpf) {
        if (cpf == null || !cpf.matches("\\d{11}")) {
            throw new IllegalArgumentException("CPF inválido.");
        }
    }

    // Método para validar dados do cliente
    private void validarDadosCliente(Cliente cliente) {
        if (cliente.getNome() == null || cliente.getNome().isEmpty()) {
            throw new IllegalArgumentException("Nome do cliente é obrigatório.");
        }
    }
}
