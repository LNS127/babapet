package br.com.babapet.controller;

import br.com.babapet.models.Prestador.Prestador;
import br.com.babapet.repositories.PrestadorRepository;
import br.com.babapet.services.PrestadorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/prestadores")
public class PrestadorController {

    @Autowired
    private PrestadorService prestadorService;

    // Criar um novo prestador
    @PostMapping
    public ResponseEntity<Prestador> criarPrestador(@RequestBody Prestador prestador) {
        Prestador novoPrestador = prestadorService.criarPrestador(prestador);
        return ResponseEntity.ok(novoPrestador);
    }

    // Listar todos os prestadores
    @GetMapping
    public ResponseEntity<List<Prestador>> listarPrestadores() {
        List<Prestador> prestadores = prestadorService.listarPrestadores();
        return ResponseEntity.ok(prestadores);
    }

    // Buscar um prestador por CPF
    @GetMapping("/{cpf}")
    public ResponseEntity<Prestador> buscarPrestador(@PathVariable String cpf) {
        Optional<Prestador> prestador = prestadorService.buscarPrestadorPorCpf(cpf);
        return prestador.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Atualizar um prestador
    @PutMapping("/{cpf}")
    public ResponseEntity<Prestador> atualizarPrestador(@PathVariable String cpf, @RequestBody  Prestador prestadorAtualizado) {
        if (!prestadorService.existsById(cpf)) {
            return ResponseEntity.notFound().build();
        }
        prestadorAtualizado.setCpf(cpf);
        Prestador prestadorSalvo = prestadorService.atualizarPrestador(cpf, prestadorAtualizado);
        return ResponseEntity.ok(prestadorSalvo);
    }

    // Inativar um prestador (definir status como falso)
    @PatchMapping("/{cpf}/inativar")
    public ResponseEntity<Prestador> inativarPrestador(@PathVariable String cpf) {
        Optional<Prestador> prestadorExistente = prestadorService.buscarPrestadorPorCpf(cpf);
        if (!prestadorExistente.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Prestador prestador = prestadorExistente.get();
        prestador.setStatus(false);
        prestadorService.deletarPrestador(cpf);
        return ResponseEntity.ok(prestador);
    }
}
