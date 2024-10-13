package br.com.babapet.controller;

import br.com.babapet.models.Cliente;
import br.com.babapet.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    // Criar um novo cliente
    @PostMapping
    public ResponseEntity<Cliente> criarCliente(@RequestBody Cliente cliente) {
        Cliente novoCliente = clienteRepository.save(cliente);
        return ResponseEntity.ok(novoCliente);
    }

    // Listar todos os clientes
    @GetMapping
    public ResponseEntity<List<Cliente>> listarClientes() {
        List<Cliente> clientes = clienteRepository.findAll();
        return ResponseEntity.ok(clientes);
    }

    // Buscar um cliente por CPF
    @GetMapping("/{cpf}")
    public ResponseEntity<Cliente> buscarCliente(@PathVariable String cpf) {
        Optional<Cliente> cliente = clienteRepository.findById(cpf);
        return cliente.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Atualizar um cliente
    @PutMapping("/{cpf}")
    public ResponseEntity<Cliente> atualizarCliente(@PathVariable String cpf, @RequestBody Cliente clienteAtualizado) {
        if (!clienteRepository.existsById(cpf)) {
            return ResponseEntity.notFound().build();
        }
        clienteAtualizado.setCpf(cpf);
        Cliente clienteSalvo = clienteRepository.save(clienteAtualizado);
        return ResponseEntity.ok(clienteSalvo);
    }


    @PatchMapping("/{cpf}/inativar")
    public ResponseEntity<Cliente> inativarCliente(@PathVariable String cpf) {
        Optional<Cliente> clienteExistente = clienteRepository.findById(cpf);
        if (!clienteExistente.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Cliente cliente = clienteExistente.get();
        cliente.setStatus(false);
        clienteRepository.save(cliente);
        return ResponseEntity.ok(cliente);
    }
}
