package AS_POO.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CarroRequest {
    private String modelo;
    private String placa;
    private String cor;
    private Integer multas;
}
