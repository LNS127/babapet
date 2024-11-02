package br.com.babapet.controller;

import br.com.babapet.models.Contrata.Contrata;
import br.com.babapet.models.Contrata.ContrataRequest;
import br.com.babapet.models.Contrata.ContrataResponse;
import br.com.babapet.repositories.ContrataRepository;
import br.com.babapet.services.ContrataService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/contratos")
public class ContrataController {

    @Autowired
    private ContrataService contrataService;

    // Criar uma nova contratação
    @PostMapping
    public ResponseEntity<ContrataResponse> criarContratacao(@RequestBody ContrataRequest contrata) {
        Contrata novaContratacao = contrataService.criarContrato(contrata.toContrata());
        return ResponseEntity.ok(new ContrataResponse(novaContratacao));
    }

    // Listar todas as contratações
    @GetMapping
    public ResponseEntity<List<ContrataResponse>> listarContratacoes() {
        List<Contrata> contrata = contrataService.listarContratos();
        List<ContrataResponse> contrataResponse = contrata.stream().map(ContrataResponse::new).toList();
        return ResponseEntity.ok(contrataResponse);
    }
    // Buscar uma contratação por ID
    @GetMapping("/{id}")
    public ResponseEntity<ContrataResponse> buscarContrata(@PathVariable Long id) {
        Optional<Contrata> contrata = contrataService.buscarContratoPorId(id);
        return contrata.map(value -> ResponseEntity.ok(new ContrataResponse(value))).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Atualizar uma contratação
    @PutMapping("/{id}")
    public ResponseEntity<ContrataResponse> atualizarContrata(@PathVariable Long id, @RequestBody ContrataRequest contrata) {
        Contrata novaContrata = contrataService.atualizarContrato(id, contrata.toContrata());
        return ResponseEntity.ok(new ContrataResponse(novaContrata));
    }

    // Deletar uma contratação
    @DeleteMapping("/{id}")
    public ResponseEntity<ContrataResponse> deletarContrata(@PathVariable Long id) {
       contrataService.deletarContrato(id);
       return  ResponseEntity.noContent().build();
    }
}
