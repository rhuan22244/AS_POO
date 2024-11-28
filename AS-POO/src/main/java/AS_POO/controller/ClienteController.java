package AS_POO.controller;

import AS_POO.dtos.ClienteRequest;
import AS_POO.dtos.ClienteResponse;
import AS_POO.model.Cliente;
import AS_POO.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService service;

    @Autowired
    public ClienteController(ClienteService service) {
        this.service = service;
    }

    @GetMapping
    public List<ClienteResponse> listarTodos() {
        return service.listarTodos();
    }

    @GetMapping("/filtrar-nome")
    public List<ClienteResponse> listarPorNome(@RequestParam String nome) {
        return service.listarPorNome(nome);
    }

    @PostMapping
    public Cliente adicionarCliente(@RequestBody ClienteRequest cliente) {
        return service.save(cliente);
    }

    @PutMapping("/{id}")
    public ClienteResponse atualizarCliente(@PathVariable Long id, @RequestBody ClienteRequest cliente) {
        return service.atualizarCliente(id, cliente);
    }

    @DeleteMapping("/{id}")
    public void removerCliente(@PathVariable Long id) {
        service.removerCliente(id);
    }

    @GetMapping("/{id}")
    public ClienteResponse buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @GetMapping("/filtrar-idade")
    public List<ClienteResponse> filtrarPorIdade(@RequestParam int idade) {
        return service.filtrarPorIdade(idade);
    }
}

