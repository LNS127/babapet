package br.com.babapet.controller;

import br.com.babapet.models.Pet.Pet;
import br.com.babapet.models.Pet.PetRequest;
import br.com.babapet.models.Pet.PetResponse;
import br.com.babapet.repositories.PetRepository;
import br.com.babapet.services.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pets")
public class PetController {

    @Autowired
    private PetService petService;

    // Criar um novo pet
    @PostMapping
    public ResponseEntity<PetResponse> criarPet(@RequestBody PetRequest pet) {
        Pet novoPet = petService.criarPet(pet.toPet());
        return ResponseEntity.ok(new PetResponse(novoPet));
    }
    // Listar todos os pets
    @GetMapping
    public ResponseEntity<List<PetResponse>> listarPets() {
        List<Pet> pets = petService.listarPets();
        List<PetResponse> petsResponse = pets.stream().map(PetResponse::new).toList();
        return ResponseEntity.ok(petsResponse);
    }

    // Buscar um pet por ID
    @GetMapping("/{id}")
    public ResponseEntity<PetResponse> buscarPet(@PathVariable Long id) {
        Optional<Pet> pet = petService.buscarPetPorId(id);
        return pet.map(value -> ResponseEntity.ok(new PetResponse(value))).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Atualizar um pet
    @PutMapping("/{id}")
    public ResponseEntity<PetResponse> atualizarPet(@PathVariable Long id, @RequestBody PetRequest petAtualizado) {
        if (!petService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        Pet petSalvo = petService.atualizarPet(id, petAtualizado.toPet());
        return ResponseEntity.ok(new PetResponse(petSalvo));
    }

    // Inativar um pet (definir raca como "indefinido")
    @PatchMapping("/{id}/inativar")
    public ResponseEntity<PetResponse> inativarPet(@PathVariable Long id) {
        Optional<Pet> petExistente = petService.buscarPetPorId(id);
        return petExistente.map(pet -> ResponseEntity.ok(new PetResponse(pet))).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
