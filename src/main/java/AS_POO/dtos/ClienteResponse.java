package AS_POO.dtos;

import AS_POO.model.Cliente;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteResponse {
    private String nome;
    private int idade;
    private Long telefone;

    // Método de conversão de Cliente para ClienteResponse
    public static ClienteResponse converteClienteResponse(Cliente cliente) {
        ClienteResponse clienteResponse = new ClienteResponse();
        clienteResponse.setNome(cliente.getNome());
        clienteResponse.setIdade(cliente.getIdade());
        clienteResponse.setTelefone(cliente.getTelefone());
        return clienteResponse;
    }
}
