package br.com.babapet.controller;

import br.com.babapet.models.Venda.Venda;
import br.com.babapet.repositories.VendaRepository;
import br.com.babapet.services.VendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/vendas")
public class VendaController {

    @Autowired
    private VendaService vendaService;

    // Criar uma nova venda
    @PostMapping
    public ResponseEntity<Venda> criarVenda(@RequestBody Venda venda) {
        Venda novaVenda = vendaService.criarVenda(venda);
        return ResponseEntity.ok(novaVenda);
    }

    // Listar todas as vendas
    @GetMapping
    public ResponseEntity<List<Venda>> listarVendas() {
        List<Venda> vendas = vendaService.listarVendas();
        return ResponseEntity.ok(vendas);
    }

    // Buscar uma venda por ID
    @GetMapping("/{id}")
    public ResponseEntity<Venda> buscarVenda(@PathVariable Long id) {
        Optional<Venda> venda = vendaService.buscarVendaPorId(id);
        return venda.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Deletar uma venda
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarVenda(@PathVariable Long id) {
        if (!vendaService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        vendaService.deletarVenda(id);
        return ResponseEntity.noContent().build();
    }
}
