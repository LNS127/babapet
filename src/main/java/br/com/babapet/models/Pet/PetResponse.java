package br.com.babapet.models.Pet;

public record PetResponse(String nome, String raca) {
    public PetResponse(Pet pet) {
        this(pet.getRaca(), pet.getNome());
    }

}
