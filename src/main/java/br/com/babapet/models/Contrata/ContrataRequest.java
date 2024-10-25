package br.com.babapet.models.Contrata;

import br.com.babapet.models.Cliente.Cliente;
import br.com.babapet.models.Pet.Pet;
import br.com.babapet.models.Prestador.Prestador;
import br.com.babapet.models.servico.Servico;

public record ContrataRequest(Long id, Cliente cliente, Pet pet, Prestador prestador, Servico servico) {

    public Contrata toContrata() {
        return new Contrata(null, this.cliente, this.pet, this.prestador, this.servico);
    }
}
