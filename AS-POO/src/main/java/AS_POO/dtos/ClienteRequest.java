package AS_POO.dtos;

import AS_POO.model.Cliente;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteRequest {
        private String nome;
        private int idade;
        private String cpf;
        private Long telefone;
        private String email;
    }
