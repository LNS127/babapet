package br.com.babapet.controller;

import br.com.babapet.models.Servico;
import br.com.babapet.repositories.ServicoRepository;
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
    private ServicoRepository servicoRepository;

    // Criar um novo serviço
    @PostMapping
    public ResponseEntity<Servico> criarServico(@RequestBody Servico servico) {
        Servico novoServico = servicoRepository.save(servico);
        return ResponseEntity.ok(novoServico);
    }

    // Listar todos os serviços
    @GetMapping
    public ResponseEntity<List<Servico>> listarServicos() {
        List<Servico> servicos = servicoRepository.findAll();
        return ResponseEntity.ok(servicos);
    }

    // Buscar um serviço por ID
    @GetMapping("/{id}")
    public ResponseEntity<Servico> buscarServico(@PathVariable Long id) {
        Optional<Servico> servico = servicoRepository.findById(id);
        return servico.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Atualizar um serviço
    @PutMapping("/{id}")
    public Servico atualizarServico(Long id, Servico servicoAtualizado) {
        if (!servicoRepository.existsById(id)) {
            throw new RuntimeException("Serviço com este ID não encontrado.");
        }
        servicoAtualizado.setId(id); // Definindo o ID do serviço atualizado
        return servicoRepository.save(servicoAtualizado);
    }


    // Deletar um serviço
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarServico(@PathVariable Long id) {
        if (!servicoRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        servicoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
