package br.com.babapet.services;

import br.com.babapet.models.Prestador;
import br.com.babapet.repositories.PrestadorRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PrestadorService {

    private final PrestadorRepository prestadorRepository;

    public PrestadorService(PrestadorRepository prestadorRepository) {
        this.prestadorRepository = prestadorRepository;
    }

    // Listar Prestadores
    public List<Prestador> listarPrestadores() {
        List<Prestador> prestadores = prestadorRepository.findAll();
        if (prestadores.isEmpty()) {
            throw new RuntimeException("Nenhum prestador encontrado.");
        }
        return prestadores;
    }

    // Buscar Prestador por CPF
    public Optional<Prestador> buscarPrestadorPorCpf(String cpf) {
        if (!validarCpf(cpf)) {
            throw new RuntimeException("CPF inválido.");
        }
        Optional<Prestador> prestador = prestadorRepository.findById(cpf);
        if (!prestador.isPresent()) {
            throw new RuntimeException("Prestador não encontrado.");
        }
        return prestador;
    }

    // Criar Prestador
    public Prestador criarPrestador(Prestador prestador) {
        if (!validarCpf(prestador.getCpf())) {
            throw new RuntimeException("CPF inválido.");
        }
        if (prestadorRepository.findById(prestador.getCpf()).isPresent()) {
            throw new RuntimeException("Prestador com este CPF já está cadastrado.");
        }
        validarDadosPrestador(prestador); // Validação adicional dos dados
        return prestadorRepository.save(prestador);
    }

    // Atualizar Prestador
    public Prestador atualizarPrestador(String cpf, Prestador prestadorAtualizado) {
        if (!validarCpf(cpf)) {
            throw new RuntimeException("CPF inválido.");
        }
        Optional<Prestador> prestadorExistente = prestadorRepository.findById(cpf);
        if (!prestadorExistente.isPresent()) {
            throw new RuntimeException("Prestador com este CPF não encontrado.");
        }
        validarDadosPrestador(prestadorAtualizado); // Validação adicional dos dados
        return prestadorRepository.save(prestadorAtualizado);
    }

    // Deletar Prestador
    public void deletarPrestador(String cpf) {
        if (!validarCpf(cpf)) {
            throw new RuntimeException("CPF inválido.");
        }
        Optional<Prestador> prestadorExistente = prestadorRepository.findById(cpf);
        if (!prestadorExistente.isPresent()) {
            throw new RuntimeException("Prestador com este CPF não existe.");
        }
        Prestador prestador = prestadorExistente.get();
        prestador.setStatus(false);
        prestadorRepository.save(prestador);

    }

    // Método para validar o formato do CPF
    private boolean validarCpf(String cpf) {
        return cpf != null && cpf.matches("\\d{11}");
    }

    // Método para validar dados do prestador
    private void validarDadosPrestador(Prestador prestador) {
        if (prestador.getNome() == null || prestador.getNome().isEmpty()) {
            throw new RuntimeException("Nome do prestador é obrigatório.");
        }
        if (!validarEmail(prestador.getEmail())) {
            throw new RuntimeException("E-mail inválido.");
        }
        if (!validarTelefone(prestador.getTelefone())) {
            throw new RuntimeException("Telefone inválido.");
        }
        // Outras validações como RG, endereço, data de nascimento e biometria podem ser adicionadas aqui
    }

    // Método para validar o formato do telefone
    private boolean validarTelefone(String telefone) {
        return telefone != null && telefone.matches("\\d{10,11}"); // Aceita 10 ou 11 dígitos
    }

    // Método para validar o formato do e-mail
    private boolean validarEmail(String email) {
        return email != null && email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
    }
}
