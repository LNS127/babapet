package br.com.babapet.controller;

import br.com.babapet.models.Cliente.Cliente;
import br.com.babapet.models.Prestador.Prestador;
import br.com.babapet.models.Prestador.PrestadorResponse;
import br.com.babapet.repositories.PrestadorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/prestadores")
public class PrestadorController {

    @Autowired
    private PrestadorRepository prestadorRepository;

    // Criar um novo prestador
    @PostMapping
    public ResponseEntity<Prestador> criarPrestador(@RequestBody Prestador prestador) {
        Prestador novoPrestador = prestadorRepository.save(prestador);
        return ResponseEntity.ok(novoPrestador);
    }

    // Listar todos os prestadores
    @GetMapping
    public ResponseEntity<List<Prestador>> listarPrestadores() {
        List<Prestador> prestadores = prestadorRepository.findAll();
        return ResponseEntity.ok(prestadores);
    }

    // Buscar um prestador por CPF
    @GetMapping("/{cpf}")
    public ResponseEntity<Prestador> buscarPrestador(@PathVariable String cpf) {
        Optional<Prestador> prestador = prestadorRepository.findById(cpf);
        return prestador.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Atualizar um prestador
    @PutMapping("/{cpf}")
    public ResponseEntity<Prestador> atualizarPrestador(@PathVariable String cpf, @RequestBody Prestador prestadorAtualizado) {
        if (!prestadorRepository.existsById(cpf)) {
            return ResponseEntity.notFound().build();
        }
        prestadorAtualizado.setCpf(cpf);
        Prestador prestadorSalvo = prestadorRepository.save(prestadorAtualizado);
        return ResponseEntity.ok(prestadorSalvo);
    }

    // Inativar um prestador
    @PatchMapping("/{cpf}/inativar")
    public ResponseEntity<PrestadorResponse> inativarPrestador(@PathVariable String cpf) {
        Optional<Prestador> prestadorExistente = prestadorRepository.findById(cpf);
        if (prestadorExistente.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Prestador prestadorInativado = prestadorExistente.get();
        return ResponseEntity.ok(new PrestadorResponse(prestadorInativado));
    }
}
