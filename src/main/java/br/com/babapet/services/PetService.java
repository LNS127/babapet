package br.com.babapet.services;

import br.com.babapet.models.Pet.Pet;
import br.com.babapet.repositories.PetRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PetService {

    private final PetRepository petRepository;

    public PetService(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    // Listar Pets
    public List<Pet> listarPets() {
        List<Pet> pets = petRepository.findAll();
        if (pets.isEmpty()) {
            throw new RuntimeException("Nenhum pet encontrado.");
        }
        return pets;
    }

    // Buscar Pet por ID
    public Optional<Pet> buscarPetPorId(Long id) {
        Optional<Pet> pet = petRepository.findById(id);
        if (!pet.isPresent()) {
            throw new RuntimeException("Pet não encontrado.");
        }
        return pet;
    }

    // Criar Pet
    public Pet criarPet(Pet pet) {
        validarDadosPet(pet); // Validação adicional dos dados
        return petRepository.save(pet);
    }

    // Atualizar Pet
    public Pet atualizarPet(Long id, Pet petAtualizado) {
        Optional<Pet> petExistente = petRepository.findById(id);
        if (!petExistente.isPresent()) {
            throw new RuntimeException("Pet com este ID não encontrado.");
        }
        validarDadosPet(petAtualizado); // Validação adicional dos dados
        petAtualizado.setId(id); // Garantir que o ID do pet atualizado é o mesmo
        return petRepository.save(petAtualizado);
    }

    // Deletar Pet
    public void deletarPet(Long id) {
        Optional<Pet> petExistente = petRepository.findById(id);
        if (!petExistente.isPresent()) {
            throw new RuntimeException("Pet com este ID não existe.");
        }
        petRepository.deleteById(id);
    }

    // Método para validar dados do pet
    private void validarDadosPet(Pet pet) {
        if (pet.getNome() == null || pet.getNome().isEmpty()) {
            throw new RuntimeException("Nome do pet é obrigatório.");
        }
        if (pet.getRaca() == null || pet.getRaca().isEmpty()) {
            throw new RuntimeException("Raça do pet é obrigatória.");
        }
        if (pet.getSexo() == null || pet.getSexo().isEmpty()) {
            throw new RuntimeException("Sexo do pet é obrigatório.");
        }

    }

    public boolean existsById(Long id) {
        Optional<Pet> pet = petRepository.findById(id);
        if (!pet.isPresent()) {
            return false;
        }
        return true;}
}
