package AS_POO.model;
import AS_POO.dtos.ClienteRequest;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "clientes")
@Getter
@Setter
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nome;
    private Integer idade;
    private String cpf;
    private Long telefone;
    private String email;
    private String password;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)  // Relacionamento OneToMany
    private List<Carro> carros = new ArrayList<>();  // Lista de carros associados ao cliente

    public static Cliente converteParaCliente(ClienteRequest clienteRequest) {
        Cliente cliente = new Cliente();
        cliente.setNome(clienteRequest.getNome());
        cliente.setIdade(clienteRequest.getIdade());
        cliente.setCpf(clienteRequest.getCpf());
        cliente.setTelefone(clienteRequest.getTelefone());
        cliente.setEmail(clienteRequest.getEmail());
        return cliente;
    }
}
