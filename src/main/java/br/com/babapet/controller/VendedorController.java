package br.com.babapet.controller;

import br.com.babapet.models.Vendedor.Vendedor;
import br.com.babapet.repositories.VendedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/vendedores")
public class VendedorController {

    @Autowired
    private VendedorRepository vendedorRepository;

    // Criar um novo vendedor
    @PostMapping
    public ResponseEntity<Vendedor> criarVendedor(@RequestBody Vendedor vendedor) {
        Vendedor novoVendedor = vendedorRepository.save(vendedor);
        return ResponseEntity.ok(novoVendedor);
    }

    // Listar todos os vendedores
    @GetMapping
    public ResponseEntity<List<Vendedor>> listarVendedores() {
        List<Vendedor> vendedores = vendedorRepository.findAll();
        return ResponseEntity.ok(vendedores);
    }

    // Buscar um vendedor por CPF
    @GetMapping("/{cpf}")
    public ResponseEntity<Vendedor> buscarVendedor(@PathVariable String cpf) {
        Optional<Vendedor> vendedor = vendedorRepository.findById(cpf);
        return vendedor.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Atualizar um vendedor
    @PutMapping("/{cpf}")
    public ResponseEntity<Vendedor> atualizarVendedor(@PathVariable String cpf, @RequestBody Vendedor vendedorAtualizado) {
        if (!vendedorRepository.existsById(cpf)) {
            return ResponseEntity.notFound().build();
        }
        vendedorAtualizado.setCpf(cpf);
        Vendedor vendedorSalvo = vendedorRepository.save(vendedorAtualizado);
        return ResponseEntity.ok(vendedorSalvo);
    }

    // Inativar um vendedor (definir status como falso)
    @PatchMapping("/{cpf}/inativar")
    public ResponseEntity<Vendedor> inativarVendedor(@PathVariable String cpf) {
        Optional<Vendedor> vendedorExistente = vendedorRepository.findById(cpf);
        if (!vendedorExistente.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Vendedor vendedor = vendedorExistente.get();
        vendedor.setStatus(false);
        vendedorRepository.save(vendedor);
        return ResponseEntity.ok(vendedor);
    }
}
