package AS_POO.service;

import AS_POO.dtos.ClienteResponse;
import AS_POO.model.Carro;
import AS_POO.model.Cliente;
import AS_POO.repository.CarroRepository;
import AS_POO.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Arrays.stream;

@Service
public class ClienteService {

    final ClienteRepository clienteRepository;
    final CarroRepository carroRepository;

    @Autowired
    public ClienteService(ClienteRepository clienteRepository, CarroRepository carroRepository) {
        this.clienteRepository = clienteRepository;
        this.carroRepository = carroRepository;
    }

    public List<ClienteResponse> listarTodos() {
        List<ClienteResponse> clientes = clienteRepository.findAll().stream().map(item -> ClienteResponse.converteClienteResponse(item)).toList();
        return clientes;
    }

    public Cliente buscarPorId(Long id) {
        // Buscar o cliente pelo ID
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado com ID: " + id));

        // Mapear o cliente para o DTO de resposta (ClienteCarroResponse)
        return cliente;
    }

    public Cliente buscarPorEmailESenha(String email, String senha) {
        return clienteRepository.findByEmailAndPassword(email, senha)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado ou senha incorreta"));
    }

    public Cliente criarCliente(Cliente clienteRequest) {
        return clienteRepository.save(clienteRequest);
    }

    public Cliente atualizarCliente(Long id, Cliente clienteRequest) {
        Cliente cliente = buscarPorId(id); // Busca o cliente existente pelo ID

        // Atualiza apenas os atributos que não são nulos
        if (clienteRequest.getNome() != null) {
            cliente.setNome(clienteRequest.getNome());
        }
        if (clienteRequest.getCpf() != null) {
            cliente.setCpf(clienteRequest.getCpf());
        }
        if (clienteRequest.getIdade() != null) {
            cliente.setIdade(clienteRequest.getIdade());
        }
        if (clienteRequest.getEmail() != null) {
            cliente.setEmail(clienteRequest.getEmail());
        }
        if (clienteRequest.getTelefone() != null) {
            cliente.setTelefone(clienteRequest.getTelefone());
        }

        if (clienteRequest.getPassword() != null){
            cliente.setPassword(clienteRequest.getPassword());
        }

        // Salva e retorna o cliente atualizado
        return clienteRepository.save(cliente);
    }


    public void deletarCliente(Long id) {
        clienteRepository.deleteById(id);
    }

    public Cliente adicionarCarroAoCliente(Long clienteId, Long carroId) {
        // Buscar o cliente pelo ID
        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado com ID: " + clienteId));

        // Buscar o carro pelo ID
        Carro carro = carroRepository.findById(carroId)
                .orElseThrow(() -> new RuntimeException("Carro não encontrado com ID: " + carroId));

        // Associar o carro ao cliente
        carro.setCliente(cliente);
        cliente.getCarros().add(carro);

        // Salvar as alterações
        carroRepository.save(carro);

        return cliente;  // Retornar o carro atualizado
    }

}
