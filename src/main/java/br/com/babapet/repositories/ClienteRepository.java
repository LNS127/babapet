package br.com.babapet.repositories;

import br.com.babapet.models.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, String> {

}
