package br.com.babapet.controller;

import br.com.babapet.models.Cliente.Cliente;
import br.com.babapet.models.Cliente.ClienteResponse;
import br.com.babapet.models.Vendedor.Vendedor;
import br.com.babapet.models.Vendedor.VendedorRequest;
import br.com.babapet.models.Vendedor.VendedorResponse;
import br.com.babapet.repositories.VendedorRepository;
import br.com.babapet.services.VendedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/vendedores")
public class VendedorController {

    @Autowired
    private VendedorService vendedorService;

    // Criar um novo vendedor
    @PostMapping
    public ResponseEntity<VendedorResponse> criarVendedor(@RequestBody VendedorRequest vendedor) {
        Vendedor novoVendedor = vendedorService.criarVendedor(vendedor.toVendedor());
        return ResponseEntity.ok(new VendedorResponse(novoVendedor));
    }

    // Listar todos os vendedores
    @GetMapping
    public ResponseEntity<List<VendedorResponse>> listarVendedores() {
        List<Vendedor> vendedores = vendedorService.listarVendedores();
        List<VendedorResponse> vendedoresResponse = vendedores.stream().map(VendedorResponse::new).toList();
        return ResponseEntity.ok(vendedoresResponse);
    }
    // Buscar um vendedor por CPF
    @GetMapping("/{cpf}")
    public ResponseEntity<VendedorResponse> buscarVendedor(@PathVariable String cpf) {
        Optional<Vendedor> vendedor = vendedorService.buscarVendedorPorId(cpf);
        return vendedor.map(value -> ResponseEntity.ok(new VendedorResponse(value))).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Atualizar um vendedor
    @PutMapping("/{cpf}")
    public ResponseEntity<VendedorResponse> atualizarVendedor(@PathVariable String cpf, @RequestBody VendedorRequest vendedorAtualizado) {
        Vendedor vendedorSalvo = vendedorService.atualizarVendedor(cpf, vendedorAtualizado.toVendedor());
        return ResponseEntity.ok(new VendedorResponse(vendedorSalvo));
    }

    // Inativar um vendedor (definir status como falso)
    @PatchMapping("/{cpf}/inativar")
    public ResponseEntity<VendedorResponse> inativarVendedor(@PathVariable String cpf) {
        Vendedor VendedorInativado = vendedorService.inativarVendedor(cpf);
        return ResponseEntity.ok(new VendedorResponse(VendedorInativado));
    }
    }

