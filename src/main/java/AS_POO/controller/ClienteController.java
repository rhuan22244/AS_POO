package AS_POO.controller;

import AS_POO.dtos.CarroRequest;
import AS_POO.dtos.ClienteCarroResponse;
import AS_POO.dtos.ClienteResponse;
import AS_POO.model.Carro;
import AS_POO.model.Cliente;
import AS_POO.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    final ClienteService clienteService;

    @Autowired
    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    // Get: Retorna todos os clientes
    @GetMapping
    public List<ClienteResponse> listarTodos() {
        return clienteService.listarTodos();
    }

    // Get: Retorna cliente por id com os carros associados
    @GetMapping("/{id}")
    public ResponseEntity<ClienteCarroResponse> buscarClientId(@PathVariable Long id) {
        ClienteCarroResponse cliente = ClienteCarroResponse.converteClienteCarroResponse(clienteService.buscarPorId(id));
        return ResponseEntity.ok(cliente);
    }

    @GetMapping("/buscarCliente")
    public Cliente buscarClienteUsuario(@RequestParam String email, @RequestParam String password) {
        return clienteService.buscarPorEmailESenha(email, password);
    }

    // Post: Adiciona um novo cliente
    @PostMapping
    public ResponseEntity<Cliente> adicionarCliente(@RequestBody Cliente clienteRequest) {
        Cliente cliente = clienteService.criarCliente(clienteRequest);
        return ResponseEntity.ok(cliente);
    }

    // Put: Atualiza cliente por id
    @PutMapping("/{id}")
    public ResponseEntity<ClienteCarroResponse> atualizarCliente(@PathVariable Long id, @RequestBody Cliente clienteRequest) {
        Cliente clienteAtualizado = clienteService.atualizarCliente(id, clienteRequest);

        ClienteCarroResponse response = ClienteCarroResponse.converteClienteCarroResponse(clienteAtualizado);

        return ResponseEntity.ok(response);
    }


    // Delete: Remove cliente por id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCliente(@PathVariable Long id) {
        clienteService.deletarCliente(id);
        return ResponseEntity.noContent().build();
    }

    // Post: Associa um carro a um cliente
    @PostMapping("/associar")
    public ResponseEntity<?> associarCarroAoCliente(
            @RequestParam("clienteId") Long clienteId,
            @RequestParam("carroId") Long carroId) {
        try {
            // Adiciona o carro ao cliente e retorna o cliente atualizado com os carros convertidos
            ClienteCarroResponse clienteResponse = ClienteCarroResponse.converteClienteCarroResponse(clienteService.adicionarCarroAoCliente(clienteId, carroId));
            return ResponseEntity.ok(clienteResponse); // Retorna o ClienteCarroResponse com status 200
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage()); // Retorna erro com status 400
        }
    }
}
