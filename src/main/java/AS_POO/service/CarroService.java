package AS_POO.service;

import AS_POO.dtos.CarroRequest;
import AS_POO.dtos.CarroResponse;
import AS_POO.dtos.ClienteResponse;
import AS_POO.model.Carro;
import AS_POO.model.Cliente;
import AS_POO.repository.CarroRepository;
import AS_POO.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarroService {

    final CarroRepository carroRepository;
    final ClienteRepository clienteRepository;

    public CarroService(CarroRepository carroRepository, ClienteRepository clienteRepository) {
        this.carroRepository = carroRepository;
        this.clienteRepository = clienteRepository;
    }

    // Listar todos os carros
    public List<CarroResponse> listarTodos() {
        List<CarroResponse> carros = carroRepository.findAll().stream().map(item -> CarroResponse.converteCarroResponse(item)).toList();
        return carros;
    }

    // Listar carro por ID
    public Carro listarPorId(Long id) {
        return carroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Carro não encontrado"));
    }

    // Salvar um novo carro
    public Carro criarCarro(CarroRequest carroRequest) {
        // Cria o carro sem o cliente
        Carro carro = Carro.converteParaCarro(carroRequest);
        return carroRepository.save(carro);
    }

    // Atualizar um carro por ID - Atualização parcial dos campos
    public Carro atualizarCarro(Long id, CarroRequest carroRequest) {
        Carro carro = carroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Carro não encontrado com ID: " + id));

        // Atualiza apenas os campos passados no corpo da requisição
        if (carroRequest.getModelo() != null) {
            carro.setModelo(carroRequest.getModelo());
        }
        if (carroRequest.getPlaca() != null) {
            carro.setPlaca(carroRequest.getPlaca());
        }
        if (carroRequest.getCor() != null) {
            carro.setCor(carroRequest.getCor());
        }
        if (carroRequest.getMultas() != null) {
            carro.setMultas(carroRequest.getMultas());
        }


        // Salva as alterações no carros
        return carroRepository.save(carro);
    }

    // Deletar carro por ID
    public void deletarCarro(Long id) {
        if (!carroRepository.existsById(id)) {
            throw new RuntimeException("Carro não encontrado com ID: " + id);
        }
        carroRepository.deleteById(id);
    }
}
