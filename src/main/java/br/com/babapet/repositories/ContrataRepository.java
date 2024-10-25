package br.com.babapet.repositories;

import br.com.babapet.models.Contrata.Contrata;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContrataRepository extends JpaRepository<Contrata, Long> {

}
