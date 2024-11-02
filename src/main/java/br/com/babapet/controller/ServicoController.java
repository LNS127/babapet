package br.com.babapet.controller;

import br.com.babapet.models.servico.Servico;
import br.com.babapet.models.servico.ServicoRequest;
import br.com.babapet.models.servico.ServicoResponse;
import br.com.babapet.repositories.ServicoRepository;
import br.com.babapet.services.ServicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static java.lang.Long.valueOf;

@RestController
@RequestMapping("/servicos")
public class ServicoController {

    @Autowired
    private ServicoService servicoService;

    // Criar um novo serviço
    @PostMapping
    public ResponseEntity<ServicoResponse> criarServico(@RequestBody ServicoRequest servico) {
        Servico novoServico = servicoService.criarServico(servico.toServico());
        return ResponseEntity.ok(new ServicoResponse(novoServico));
    }
    // Listar todos os serviços
    @GetMapping
    public ResponseEntity<List<ServicoResponse>> listarServicos() {
        List<Servico> servicos = servicoService.listarServicos();
        List<ServicoResponse> servicosResponse = servicos.stream().map(ServicoResponse::new).toList();
        return ResponseEntity.ok(servicosResponse);
    }
    // Buscar um serviço por ID
    @GetMapping("/{id}")
    public ResponseEntity<ServicoResponse> buscarServico(@PathVariable Long id) {
        Optional<Servico> servico = servicoService.buscarServicoPorId(id);
        return servico.map(servicoResponse -> ResponseEntity.ok(new ServicoResponse(servicoResponse))).orElseGet(() -> ResponseEntity.notFound().build());
    }
    // Atualizar um serviço
    @PutMapping("/{id}")
    public Servico atualizarServico(Long id, Servico servicoAtualizado) {
       Optional<Servico> servico = servicoService.buscarServicoPorId(id);
        if (servico.isEmpty()) {
            throw new RuntimeException("Serviço com este ID nao encontrado.");
        }
        return servicoService.atualizarServico(id, servicoAtualizado);
    }


    // Deletar um serviço
    @DeleteMapping("/{id}")
    public ResponseEntity<ServicoResponse> deletarServico(@PathVariable Long id) {
        servicoService.deletarServico(id);
        return ResponseEntity.noContent().build();
    }
}
