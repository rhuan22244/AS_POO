package AS_POO.service;

import AS_POO.dtos.ClienteRequest;
import AS_POO.dtos.ClienteResponse;
import AS_POO.model.Cliente;
import AS_POO.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClienteService {

    final ClienteRepository repository;

    public ClienteService(ClienteRepository repository) {
        this.repository = repository;
    }

    public List<ClienteResponse> listarTodos() {
        return repository
                .findAll()
                .stream()
                .map(ClienteResponse::converteClienteResponse)
                .toList();
    }

    public List<ClienteResponse> listarPorNome(String nome) {
        return repository
                .findByNome(nome)
                .stream()
                .map(ClienteResponse::converteClienteResponse)
                .toList();
    }

    public Cliente save(ClienteRequest clienteRequest) {
        return repository.save(Cliente.converteParaCliente(clienteRequest));
    }

    public ClienteResponse atualizarCliente(Long id, ClienteRequest clienteRequest) {
        // Verifica se o cliente existe no banco
        Cliente clienteExistente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        // Atualiza os campos do cliente existente com os dados recebidos
        clienteExistente.setNome(clienteRequest.getNome());
        clienteExistente.setEmail(clienteRequest.getEmail());
        clienteExistente.setTelefone(clienteRequest.getTelefone());
        clienteExistente.setCpf(clienteRequest.getCpf());

        // Salva as alterações no banco
        Cliente clienteAtualizado = repository.save(clienteExistente);

        // Retorna a resposta convertida
        return ClienteResponse.converteClienteResponse(clienteAtualizado);
    }

    public void removerCliente(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Cliente não encontrado");
        }
        repository.deleteById(id);
    }

    public ClienteResponse buscarPorId(Long id) {
        Cliente cliente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        return ClienteResponse.converteClienteResponse(cliente);
    }

    public List<ClienteResponse> filtrarPorIdade(int idade) {
        return repository
                .findByIdade(idade)
                .stream()
                .map(ClienteResponse::converteClienteResponse)
                .toList();
    }
}
