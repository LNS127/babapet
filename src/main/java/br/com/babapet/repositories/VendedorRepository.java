package br.com.babapet.repositories;

import br.com.babapet.models.Vendedor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VendedorRepository extends JpaRepository<Vendedor, String> {

    Optional<Vendedor> findById(Long id);
}
