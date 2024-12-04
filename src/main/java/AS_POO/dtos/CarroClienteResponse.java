package AS_POO.dtos;

import AS_POO.model.Carro;
import AS_POO.model.Cliente;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CarroClienteResponse {
    private Long id;
    private String modelo;
    private String placa;
    private String cor;
    private Integer multas;
    private String nomeCliente;


    public static CarroClienteResponse converteCarroClienteResponse(Carro carro) {
        CarroClienteResponse carroClienteResponse = new CarroClienteResponse();
        carroClienteResponse.setId(carro.getId());
        carroClienteResponse.setModelo(carro.getModelo());
        carroClienteResponse.setPlaca(carro.getPlaca());
        carroClienteResponse.setCor(carro.getCor());
        carroClienteResponse.setMultas(carro.getMultas());

        // Tratar caso o cliente seja nulo
        if (carro.getCliente() != null) {
            carroClienteResponse.setNomeCliente(carro.getCliente().getNome());
        } else {
            carroClienteResponse.setNomeCliente("Cliente não associado");
        }

        return carroClienteResponse;
    }

}

