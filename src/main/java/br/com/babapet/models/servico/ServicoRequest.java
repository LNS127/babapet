package br.com.babapet.models.servico;

public record ServicoRequest(Long id, String tipoDeServico, String descricao, double valor) {

    public Servico toServico() {
        return new Servico(null, this.tipoDeServico, this.descricao, this.valor);
    }

}
