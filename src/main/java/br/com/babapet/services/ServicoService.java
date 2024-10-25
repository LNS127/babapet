package br.com.babapet.services;

import br.com.babapet.models.servico.Servico;
import br.com.babapet.repositories.ServicoRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ServicoService {

    private final ServicoRepository servicoRepository;

    public ServicoService(ServicoRepository servicoRepository) {
        this.servicoRepository = servicoRepository;
    }

    // Listar Serviços
    public List<Servico> listarServicos() {
        List<Servico> servicos = servicoRepository.findAll();
        if (servicos.isEmpty()) {
            throw new RuntimeException("Nenhum serviço encontrado.");
        }
        return servicos;
    }

    // Buscar Serviço por ID
    public Optional<Servico> buscarServicoPorId(Long id) {
        Optional<Servico> servico = servicoRepository.findById(id);
        if (!servico.isPresent()) {
            throw new RuntimeException("Serviço não encontrado.");
        }
        return servico;
    }

    // Criar Serviço
    public Servico criarServico(Servico servico) {
        validarDadosServico(servico); // Validação adicional dos dados
        return servicoRepository.save(servico);
    }

    // Atualizar Serviço
    public Servico atualizarServico(Long id, Servico servicoAtualizado) {
        Optional<Servico> servicoExistente = servicoRepository.findById(id);
        if (servicoExistente.isEmpty()) {
            throw new RuntimeException("Serviço com este ID não encontrado.");
        }
        validarDadosServico(servicoAtualizado); // Validação adicional dos dados
        return servicoRepository.save(servicoAtualizado);
    }

    // Deletar Serviço
    public void deletarServico(Long id) {
        Optional<Servico> servicoExistente = servicoRepository.findById(id);
        if (!servicoExistente.isPresent()) {
            throw new RuntimeException("Serviço com este ID não existe.");
        }
        servicoRepository.deleteById(id);
    }

    // Método para validar dados do serviço
    private void validarDadosServico(Servico servico) {
        if (servico.getTipoDeServico() == null || servico.getTipoDeServico().isEmpty()) {
            throw new RuntimeException("Tipo do serviço é obrigatório.");
        }
        if (servico.getDescricao() == null || servico.getDescricao().isEmpty()) {
            throw new RuntimeException("Descrição do serviço é obrigatória.");
        }


    }
}

