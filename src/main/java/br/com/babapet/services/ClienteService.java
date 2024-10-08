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
    public List<Cliente> listarCliente(){
        return clienteRepository.findAll();
    }
    public Optional<Cliente>buscarClientePorCpf(String cpf){
        return clienteRepository.findById(cpf);
    }
    public Cliente criarCliente(Cliente cliente){

        return clienteRepository.save(cliente);
    }
    public Cliente atualizarCliente(String cpf, Cliente clienteAtualizado){
        return clienteRepository.save(clienteAtualizado);
    }
    public void deletarCliente(String cpf){
        clienteRepository.deleteById(cpf);
    }
    private void validarCliente(Cliente cliente){
        if (cliente.getNome() == null || cliente.getNome().isEmpty()){
            throw new RuntimeException("É obrigatório preencher o nome.");
        }
        if (cliente.getEmail() == null || cliente.getEmail().isEmpty()){
            throw new RuntimeException("É obrigatório preencher o e-mail ");
        }
        if(clienteRepository.existsByEmail(cliente.getEmail())){
            throw new RuntimeException("É obrigatório preencher o e-mail");
        };
    }
}
