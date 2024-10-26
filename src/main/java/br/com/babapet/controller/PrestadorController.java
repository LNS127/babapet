package br.com.babapet.controller;

import br.com.babapet.models.Cliente.ClienteResponse;
import br.com.babapet.models.Prestador.Prestador;
import br.com.babapet.models.Prestador.PrestadorRequest;
import br.com.babapet.models.Prestador.PrestadorResponse;
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
    public ResponseEntity<PrestadorResponse> criarPrestador(@RequestBody PrestadorRequest prestador) {
        Prestador novoPrestador = prestadorService.criarPrestador(prestador.toPrestador());
        return ResponseEntity.ok(new PrestadorResponse(novoPrestador));
    }

    // Listar todos os prestadores
    @GetMapping
    public ResponseEntity<List<PrestadorResponse>> listarPrestadores() {
        List<Prestador> prestadores = prestadorService.listarPrestadores();
        return ResponseEntity.ok(prestadores.stream().map(PrestadorResponse::new).toList());
    }

    // Buscar um prestador por CPF
    @GetMapping("/{cpf}")
    public ResponseEntity<PrestadorResponse> buscarPrestador(@PathVariable String cpf) {
        Optional<Prestador> prestador = prestadorService.buscarPrestadorPorCpf(cpf);
        return ResponseEntity.ok(new PrestadorResponse(prestador.get()));
    }

    // Atualizar um prestador
    @PutMapping("/{cpf}")
    public ResponseEntity<PrestadorResponse> atualizarPrestador(@PathVariable String cpf, @RequestBody  Prestador prestadorAtualizado) {
        if (!prestadorService.existsById(cpf)) {
            return ResponseEntity.notFound().build();
        }
        prestadorAtualizado.setCpf(cpf);
        Prestador prestadorSalvo = prestadorService.atualizarPrestador(cpf, prestadorAtualizado);
        return ResponseEntity.ok(new PrestadorResponse(prestadorSalvo));
    }

    // Inativar um prestador (definir status como falso)
    @PatchMapping("/{cpf}/inativar")
    public ResponseEntity<PrestadorResponse> inativarPrestador(@PathVariable String cpf) {
        Optional<Prestador> prestadorExistente = prestadorService.buscarPrestadorPorCpf(cpf);
        if (!prestadorExistente.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Prestador prestador = prestadorExistente.get();
        prestador.setStatus(false);
        prestadorService.deletarPrestador(cpf);
        return ResponseEntity.ok(new PrestadorResponse (prestador));
    }
}
