package AS_POO.dtos;

import AS_POO.model.Cliente;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteResponse {
    private String nome;
    private int idade;
    private String cpf;
    private Long telefone;
    private String email;

    public static ClienteResponse converteClienteResponse(Cliente cliente){
        ClienteResponse clienteResponse = new ClienteResponse();
        clienteResponse.setNome(cliente.getNome());
        clienteResponse.setIdade(cliente.getIdade());
        clienteResponse.setCpf(cliente.getCpf());
        clienteResponse.setTelefone(cliente.getTelefone());
        clienteResponse.setEmail(cliente.getEmail());

        return clienteResponse;
    }
}
