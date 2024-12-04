package AS_POO.model;

import AS_POO.dtos.CarroRequest;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Carro {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String modelo;
    private String placa;
    private String cor;
    private Integer multas;

    @ManyToOne
    private Cliente cliente;

    // Métodos getters e setters

    public static Carro converteParaCarro(CarroRequest carroRequest) {
        Carro carro = new Carro();
        carro.setModelo(carroRequest.getModelo());
        carro.setPlaca(carroRequest.getPlaca());
        carro.setCor(carroRequest.getCor());
        carro.setMultas(carroRequest.getMultas());
        return carro;
    }
}
