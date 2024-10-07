package br.com.babapet.repositories;

import br.com.babapet.models.Servico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServicoRepository extends JpaRepository<Servico, String> {

}
