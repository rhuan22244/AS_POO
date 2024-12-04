package AS_POO.dtos;

import AS_POO.model.Carro;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CarroResponse {
    private String modelo;
    private String placa;
    private String cor;

    // Método para converter Carro para CarroResponse
    public static CarroResponse converteCarroResponse(Carro carro) {
        CarroResponse carroResponse = new CarroResponse();
        carroResponse.setModelo(carro.getModelo());
        carroResponse.setPlaca(carro.getPlaca());
        carroResponse.setCor(carro.getCor());

        return carroResponse;
    }
}
