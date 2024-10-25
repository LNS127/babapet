package br.com.babapet.controller;

import br.com.babapet.models.Contrata.Contrata;
import br.com.babapet.repositories.ContrataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/contratos")
public class ContrataController {

    @Autowired
    private ContrataRepository contrataRepository;

    // Criar uma nova contratação
    @PostMapping
    public ResponseEntity<Contrata> criarContrata(@RequestBody Contrata contrata) {
        Contrata novaContrata = contrataRepository.save(contrata);
        return ResponseEntity.ok(novaContrata);
    }

    // Listar todas as contratações
    @GetMapping
    public ResponseEntity<List<Contrata>> listarContratas() {
        List<Contrata> contratas = contrataRepository.findAll();
        return ResponseEntity.ok(contratas);
    }

    // Buscar uma contratação por ID
    @GetMapping("/{id}")
    public ResponseEntity<Contrata> buscarContrata(@PathVariable Long id) {
        Optional<Contrata> contrata = contrataRepository.findById(id);
        return contrata.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
