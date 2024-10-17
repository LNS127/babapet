package br.com.babapet.repositories;

import br.com.babapet.models.Pet.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetRepository extends JpaRepository<Pet, Long> {
}
