package AS_POO.model;

import AS_POO.dtos.ClienteRequest;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "clientes")
@Getter
@Setter
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String password;
    public String nome;
    public int idade;
    public String cpf;
    public Long telefone;
    public String email;

    public static Cliente converteParaCliente(ClienteRequest clienteRequest) {
        Cliente cliente = new Cliente();
        cliente.setNome(clienteRequest.getNome());
        cliente.setIdade(clienteRequest.getIdade());
        cliente.setCpf(clienteRequest.getCpf());
        cliente.setTelefone(clienteRequest.getTelefone());
        cliente.setEmail(clienteRequest.getEmail());
        cliente.setPassword(cliente.getPassword());
        return cliente;
    }
}

