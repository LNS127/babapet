package br.com.babapet.repositories;

import br.com.babapet.models.Vendedor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendedorRepository extends JpaRepository<Vendedor, String> {

}
