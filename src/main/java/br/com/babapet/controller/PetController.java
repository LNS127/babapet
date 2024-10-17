package br.com.babapet.controller;

import br.com.babapet.models.Pet.Pet;
import br.com.babapet.repositories.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pets")
public class PetController {

    @Autowired
    private PetRepository petRepository;

    // Criar um novo pet
    @PostMapping
    public ResponseEntity<Pet> criarPet(@RequestBody Pet pet) {
        Pet novoPet = petRepository.save(pet);
        return ResponseEntity.ok(novoPet);
    }

    // Listar todos os pets
    @GetMapping
    public ResponseEntity<List<Pet>> listarPets() {
        List<Pet> pets = petRepository.findAll();
        return ResponseEntity.ok(pets);
    }

    // Buscar um pet por ID
    @GetMapping("/{id}")
    public ResponseEntity<Pet> buscarPet(@PathVariable Long id) {
        Optional<Pet> pet = petRepository.findById(id);
        return pet.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Atualizar um pet
    @PutMapping("/{id}")
    public ResponseEntity<Pet> atualizarPet(@PathVariable Long id, @RequestBody Pet petAtualizado) {
        if (!petRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        petAtualizado.setId(id); // Garante que o ID do pet atualizado é o mesmo
        Pet petSalvo = petRepository.save(petAtualizado);
        return ResponseEntity.ok(petSalvo);
    }

    // Inativar um pet (definir raca como "indefinido")
    @PatchMapping("/{id}/inativar")
    public ResponseEntity<Pet> inativarPet(@PathVariable Long id) {
        Optional<Pet> petExistente = petRepository.findById(id);
        if (!petExistente.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Pet pet = petExistente.get();
        pet.setRaca("indefinido"); //
        petRepository.save(pet);
        return ResponseEntity.ok(pet);
    }
}
