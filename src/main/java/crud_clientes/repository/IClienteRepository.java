package crud_clientes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import crud_clientes.entity.Cliente;

public interface IClienteRepository extends JpaRepository <Cliente, Long>{	

}
