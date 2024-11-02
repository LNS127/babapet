package br.com.babapet.controller;

import br.com.babapet.models.Venda.Venda;
import br.com.babapet.models.Venda.VendaRequest;
import br.com.babapet.models.Venda.VendaResponse;
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
    public ResponseEntity<VendaResponse> criarVenda(@RequestBody VendaRequest venda) {
        Venda novaVenda = vendaService.criarVenda(venda.toVenda());
        return ResponseEntity.ok(new VendaResponse(novaVenda));
    }

    // Listar todas as vendas
    @GetMapping
    public ResponseEntity<List<VendaResponse>> listarVendas() {
        List<Venda> vendas = vendaService.listarVendas();
        List<VendaResponse> vendasResponse = vendas.stream().map(VendaResponse::new).toList();
        return ResponseEntity.ok(vendasResponse);
    }

    // Buscar uma venda por ID
    @GetMapping("/{id}")
    public ResponseEntity<VendaResponse> buscarVenda(@PathVariable Long id) {
        Optional<Venda> venda = vendaService.buscarVendaPorId(id);
        return venda.map(value -> ResponseEntity.ok(new VendaResponse(value))).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Deletar uma venda
    @DeleteMapping("/{id}")
    public ResponseEntity<VendaResponse> deletarVenda(@PathVariable Long id) {
        vendaService.deletarVenda(id);
        return ResponseEntity.noContent().build();
    }
}
