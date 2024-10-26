package br.com.babapet.controller;

import br.com.babapet.models.Contrata.Contrata;
import br.com.babapet.repositories.ContrataRepository;
import br.com.babapet.services.ContrataService;
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
    public ResponseEntity<Contrata> criarContratacao(@RequestBody Contrata contrata) {
        Contrata novaContratacao = contrataService.criarContrato(contrata);
        return ResponseEntity.ok(novaContratacao);
    }

    // Listar todas as contratações
    @GetMapping
    public ResponseEntity<List<Contrata>> listarContratas() {
        List<Contrata> contratas = contrataService.listarContratos();
        return ResponseEntity.ok(contratas);
    }

    // Buscar uma contratação por ID
    @GetMapping("/{id}")
    public ResponseEntity<Contrata> buscarContrata(@PathVariable Long id) {
        Optional<Contrata> contrata = contrataService.buscarContratoPorId(id);
        return contrata.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
