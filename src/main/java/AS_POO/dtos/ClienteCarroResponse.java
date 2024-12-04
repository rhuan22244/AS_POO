package AS_POO.dtos;

import AS_POO.model.Carro;
import AS_POO.model.Cliente;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class ClienteCarroResponse {
    private Long clienteId;
    private String nome;
    private Integer idade;
    private String cpf;
    private Long telefone;
    private String email;
    private String password;
    private List<CarroClienteResponse> carros;  // Alterado para List<CarroClienteResponse>


    public static ClienteCarroResponse converteClienteCarroResponse(Cliente cliente) {
        ClienteCarroResponse clienteCarroResponse = new ClienteCarroResponse();
        clienteCarroResponse.setClienteId(cliente.getId());
        clienteCarroResponse.setNome(cliente.getNome());
        clienteCarroResponse.setIdade(cliente.getIdade());
        clienteCarroResponse.setCpf(cliente.getCpf());
        clienteCarroResponse.setTelefone(cliente.getTelefone());
        clienteCarroResponse.setEmail(cliente.getEmail());
        clienteCarroResponse.setPassword(cliente.getPassword());

        // Converter a lista de carros do cliente para a lista de CarroClienteResponse
        List<CarroClienteResponse> carrosResponse = cliente.getCarros().stream()
                .map(CarroClienteResponse::converteCarroClienteResponse)
                .collect(Collectors.toList());

        clienteCarroResponse.setCarros(carrosResponse);

        return clienteCarroResponse;
    }
}
