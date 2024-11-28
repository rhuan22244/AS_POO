package AS_POO.repository;

import AS_POO.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

    @Repository
    public interface ClienteRepository extends JpaRepository<Cliente, Long> {

        List<Cliente> findByIdade(int idade);

        List<Cliente> findByNome(String nome);
    }

