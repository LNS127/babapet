package br.com.babapet.controller;

import br.com.babapet.models.Cliente.Cliente;
import br.com.babapet.models.Cliente.ClienteRequest;
import br.com.babapet.models.Cliente.ClienteResponse;
import br.com.babapet.services.ClienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    // Injeção de dependência pelo construtor
    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    // Criar um novo cliente
    @PostMapping
    public ResponseEntity<ClienteResponse> criarCliente(@RequestBody ClienteRequest clienteRequest) {
        Cliente novoCliente = clienteService.criarCliente(clienteRequest.toCliente());
        return ResponseEntity.ok(new ClienteResponse(novoCliente));
    }

    // Listar todos os clientes
    @GetMapping
    public ResponseEntity<List<ClienteResponse>> listarClientes() {
        List<Cliente> clientes = clienteService.listarCliente();
        List<ClienteResponse> clienteResponses = clientes.stream()
                .map(ClienteResponse::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(clienteResponses);
    }

    // Buscar um cliente por CPF
    @GetMapping("/{cpf}")
    public ResponseEntity<ClienteResponse> buscarCliente(@PathVariable String cpf) {
        Cliente cliente = clienteService.buscarClientePorCpf(cpf);
        return ResponseEntity.ok(new ClienteResponse(cliente));
    }

    // Atualizar um cliente
    @PutMapping("/{cpf}")
    public ResponseEntity<ClienteResponse> atualizarCliente(
            @PathVariable String cpf,
            @RequestBody ClienteRequest clienteRequest) {

        if (!clienteService.existsByCpf(cpf)) {
            return ResponseEntity.notFound().build();
        }
        Cliente clienteAtualizado = clienteRequest.toCliente();
        clienteAtualizado.setCpf(cpf);
        Cliente clienteSalvo = clienteService.atualizarCliente(clienteAtualizado);

        return ResponseEntity.ok(new ClienteResponse(clienteSalvo));
    }

    // Inativar um cliente
    @PatchMapping("/{cpf}/inativar")
    public ResponseEntity<ClienteResponse> inativarCliente(@PathVariable String cpf) {
        Cliente clienteInativado = clienteService.inativarCliente(cpf);
        return ResponseEntity.ok(new ClienteResponse(clienteInativado));
    }
}
