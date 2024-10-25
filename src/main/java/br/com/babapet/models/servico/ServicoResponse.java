package br.com.babapet.models.servico;

public record ServicoResponse(String tipoDeServico, String descricao, double valor) {
    public ServicoResponse(Servico servico) {
        this(servico.getTipoDeServico(), servico.getDescricao(), servico.getValor());
    }
}
