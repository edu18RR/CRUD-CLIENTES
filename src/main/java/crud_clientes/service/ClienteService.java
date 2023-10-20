package crud_clientes.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import crud_clientes.dto.ClienteDto;
import crud_clientes.entity.Cliente;
import crud_clientes.repository.IClienteRepository;

@Service
public class ClienteService {
	
	@Autowired
	private IClienteRepository clienteRepository; //biene del repository heredados JPA
	
	//consultar de todos los clientes findAll
	@Transactional(readOnly = true) //metodo transacional de lectura
	public List<Cliente> findAll(){
		//Reorta la lista de clientes
		return (List<Cliente>)clienteRepository.findAll();
	}
	
	//metodo de consulta x ID
	@Transactional(readOnly = true)
	public Cliente findById (Long id) // busqueda x metodos del JPA 
	{
		return (Cliente) clienteRepository.findById(id).orElse(null);
	}
	
	//Crear cliente
	public Cliente createCliente (ClienteDto cliente) // informacion que viene del front
	{
		Cliente clienteEntity = new Cliente ();
		clienteEntity.setNombre(cliente.getNombre());
		clienteEntity.setApellido(cliente.getApellido());
		clienteEntity.setEmail(cliente.getEmail());
		
		return clienteRepository.save(clienteEntity); // envia al metodo guardar
	}
	
	//Eliminar cliente
	public void delete (Long id)
	{
		clienteRepository.deleteById(id);
	}
	
	// Actualizar cliente
	@Transactional
	public Cliente updateCliente(Long id, ClienteDto cliente) {
	    Cliente clienteEntity = clienteRepository.findById(id)
	            .orElseThrow(() -> new NoSuchElementException("Cliente no encontrado con el ID: " + id));

	    clienteEntity.setNombre(cliente.getNombre());
	    clienteEntity.setApellido(cliente.getApellido());
	    clienteEntity.setEmail(cliente.getEmail());
	    clienteEntity.setCreateAt(cliente.getCreateAt());

	    return clienteRepository.save(clienteEntity);
	}

}
