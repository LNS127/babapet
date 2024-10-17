package br.com.babapet.models.Pet;

public record PetRequest (Long id, String raca, String nome, String sexo, String tipo) {
    public Pet toPet() {
        return new Pet(this.raca,null, this.nome, this.sexo, this.tipo);

    }
}
