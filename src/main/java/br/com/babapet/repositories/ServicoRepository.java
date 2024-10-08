package br.com.babapet.repositories;

import br.com.babapet.models.Servico;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ServicoRepository extends JpaRepository<Servico, String> {

    Optional<Servico> findById(Long id);
}
