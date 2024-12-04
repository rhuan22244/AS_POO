package AS_POO.controller;

import AS_POO.dtos.CarroClienteResponse;
import AS_POO.dtos.CarroRequest;
import AS_POO.dtos.CarroResponse;
import AS_POO.dtos.ClienteResponse;
import AS_POO.model.Carro;
import AS_POO.model.Cliente;
import AS_POO.service.CarroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carros")
public class CarroController {

    final CarroService service;
    private final CarroService carroService;

    @Autowired
    public CarroController(CarroService service, CarroService carroService) {
        this.service = service;
        this.carroService = carroService;
    }

    // Get: Retorna todos os carros
    @GetMapping
    public List<CarroResponse> listarTodos() {
        return service.listarTodos();
    }

    // Get: Retorna carro pelo id
    @GetMapping("/{id}")
    public ResponseEntity<CarroClienteResponse> listarPorId(@PathVariable Long id) {
        CarroClienteResponse carro = CarroClienteResponse.converteCarroClienteResponse(service.listarPorId(id));
        return ResponseEntity.ok(carro);
    }

    // Post: Adiciona um novo carro
    @PostMapping
    public ResponseEntity<Carro> adicionarCarro(@RequestBody CarroRequest carroRequest) {
        Carro carro = service.criarCarro(carroRequest);
        return ResponseEntity.ok(carro);
    }

    // Put: Atualiza carro por id
    @PutMapping("/{id}")
    public ResponseEntity<CarroClienteResponse> atualizarCarro(
            @PathVariable Long id,
            @RequestBody CarroRequest carroRequest) {
        // Atualiza o carro
        Carro carroAtualizado = service.atualizarCarro(id, carroRequest);

        // Converte para CarroClienteResponse
        CarroClienteResponse response = CarroClienteResponse.converteCarroClienteResponse(carroAtualizado);

        // Retorna o ResponseEntity com o status 200 e a resposta
        return ResponseEntity.ok(response);
    }



    // Delete: Deleta carro por id
    @DeleteMapping("/{id}")
    public void deletarCarro(@PathVariable Long id) {
        service.deletarCarro(id);
    }
}
