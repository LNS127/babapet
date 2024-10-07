package br.com.babapet.repositories;

import br.com.babapet.models.Prestador;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrestadorRepository extends JpaRepository<Prestador, String> {

}
