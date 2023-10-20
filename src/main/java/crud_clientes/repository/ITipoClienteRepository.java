package crud_clientes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import crud_clientes.entity.TipoCliente;

public interface ITipoClienteRepository extends JpaRepository<TipoCliente, Long> {
    
}