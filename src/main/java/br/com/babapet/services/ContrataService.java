package br.com.babapet.services;

import br.com.babapet.models.Contrata.Contrata;
import br.com.babapet.repositories.*;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ContrataService {

    private final ContrataRepository contrataRepository;
    private final ClienteRepository clienteRepository;
    private final PetRepository petRepository;
    private final PrestadorRepository prestadorRepository;
    private final ServicoRepository servicoRepository;

    public ContrataService(ContrataRepository contrataRepository,
                           ClienteRepository clienteRepository,
                           PetRepository petRepository,
                           PrestadorRepository prestadorRepository,
                           ServicoRepository servicoRepository) {
        this.contrataRepository = contrataRepository;
        this.clienteRepository = clienteRepository;
        this.petRepository = petRepository;
        this.prestadorRepository = prestadorRepository;
        this.servicoRepository = servicoRepository;
    }

    // Listar Contratos
    public List<Contrata> listarContratos() {
        List<Contrata> contratos = contrataRepository.findAll();
        if (contratos.isEmpty()) {
            throw new RuntimeException("Nenhum contrato encontrado.");
        }
        return contratos;
    }

    // Buscar Contrato por ID
    public Optional<Contrata> buscarContratoPorId(Long id) {
        Optional<Contrata> contrato = contrataRepository.findById(id);
        if (!contrato.isPresent()) {
            throw new RuntimeException("Contrato não encontrado.");
        }
        return contrato;
    }

    // Criar Contrato
    public Contrata criarContrato(Contrata contrato) {
        validarDadosContrato(contrato); // Validação adicional dos dados
        return contrataRepository.save(contrato);
    }

    // Atualizar Contrato
    public Contrata atualizarContrato(Long id, Contrata contratoAtualizado) {
        Optional<Contrata> contratoExistente = contrataRepository.findById(id);
        if (!contratoExistente.isPresent()) {
            throw new RuntimeException("Contrato com este ID não encontrado.");
        }
        validarDadosContrato(contratoAtualizado); // Validação adicional dos dados
        contratoAtualizado.setId(id); // Garantir que o ID do contrato atualizado é o mesmo
        return contrataRepository.save(contratoAtualizado);
    }

    // Deletar Contrato
    public void deletarContrato(Long id) {
        Optional<Contrata> contratoExistente = contrataRepository.findById(id);
        if (contratoExistente.isEmpty()) {
            throw new RuntimeException("Contrato com este ID não existe.");
        }
        contrataRepository.deleteById(id);
    }

    // Método para validar dados do contrato
    private void validarDadosContrato(Contrata contrato) {
        if (contrato.getPet() == null || petRepository.findById(contrato.getPet().getId()).isEmpty()) {
            throw new RuntimeException("Pet é obrigatório ou não existe.");
        }

        if (contrato.getServico() == null || servicoRepository.findById(contrato.getServico().getId()).isEmpty()) {
            throw new RuntimeException("Serviço é obrigatório ou não existe.");
        }
    }
}
