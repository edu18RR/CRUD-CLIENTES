package crud_clientes.controller;

import java.util.List;
//SOLO VA EL LLAMADO Y SUS VALIDACIONES TRY CACH
import java.util.Map;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import crud_clientes.dto.ClienteDto;
import crud_clientes.entity.Cliente;
import crud_clientes.service.ClienteService;

@RestController
@RequestMapping ("/api") // metodo de acceso
public class ClienteRestController {
	
	@Autowired
	private ClienteService clienteService;
	
	@GetMapping("/clientes")
	@ResponseStatus (HttpStatus.OK)
	public List<Cliente> consulta (){
		return clienteService.findAll();
		
	}
	
	//Consulta cliente x ID especifico
	@GetMapping("/clientes/{id}")
	public ResponseEntity<?> consultaPorID(@PathVariable Long id){
		
		Cliente cliente = null;
		String response = "";
		try {
			cliente = clienteService.findById(id);
					
		} catch (DataAccessException e) {
			response= "Error al realizar la consulta.";
			response= response.concat(e.getMessage().concat(e.getMostSpecificCause().toString()));
			return new ResponseEntity<String>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
		
		if(cliente== null)
		{
			response= "El cliente con el ID: ".concat(id.toString()).concat(" no existe en la base de datos");
			return new ResponseEntity<String>(response, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Cliente>(cliente, HttpStatus.OK);
	}
	
	//Elimina cliente por id especifico
	@DeleteMapping("/clientes/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		//Clave valor en el map
		Map<String, Object> response = new HashMap<>();
		
		try {
			Cliente clienteDelete = this.clienteService.findById(id);
			if(clienteDelete== null)
			{
				response.put("mensaje", "Error al eliminar. El cliente no existe en la base de datos");
				return new ResponseEntity <Map<String, Object>> (response, HttpStatus.INTERNAL_SERVER_ERROR);
			}

			clienteService.delete(id);
		} catch (DataAccessException e) {
			response.put("mensaje","Error al eliminar en la BD.");
			response.put("error", e.getMessage().concat(e.getMostSpecificCause().getLocalizedMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
			// clave valor
			response.put("mensaje", "Cliente eliminado con éxito");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
			
	}
	
	//Crearcliente
	@PostMapping("/clientes")
	public ResponseEntity<?> create(@RequestBody ClienteDto cliente) { /// recibir
        Cliente clienteNew = null;
        Map<String, Object> response = new HashMap<>();

        try {
            clienteNew = this.clienteService.createCliente(cliente); //guardar
            // Posiblemente, debas agregar más lógica aquí según tus necesidades
            // Por ejemplo, configurar el código de respuesta y el mensaje de éxito.
            
        } catch (DataAccessException e) {
            response.put("mensaje", "Error al realizar el insert");
            response.put("error", e.getMessage().concat(e.getMostSpecificCause().getLocalizedMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
        response.put("mensaje", "Cliente creado con éxito, con el ID");
        response.put("cliente", clienteNew);
        // Retornar una respuesta de éxito aquí según tus necesidades.
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }
	
	@PutMapping("/clientes/{id}")
	public ResponseEntity<?> updateCliente(@PathVariable Long id, @RequestBody ClienteDto clienteDto) {
	    Map<String, Object> response = new HashMap<>();

	    // Buscar el cliente existente por ID
	    Cliente clienteActual = clienteService.findById(id);

	    if (clienteActual == null) {
	        response.put("mensaje", "Error al actualizar. El cliente con ID " + id + " no existe en la base de datos.");
	        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
	    }

	    // Actualizar los campos del cliente con los valores proporcionados en clienteDto
	    clienteActual.setNombre(clienteDto.getNombre());
	    clienteActual.setApellido(clienteDto.getApellido());
	    clienteActual.setEmail(clienteDto.getEmail());

	    try {
	        // Llamar al método de servicio para actualizar el cliente
	        Cliente clienteActualizado = clienteService.updateCliente(id, clienteDto);

	        response.put("mensaje", "Cliente actualizado con éxito");
	        response.put("cliente", clienteActualizado);
	        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	    } catch (DataAccessException e) {
	        response.put("mensaje", "Error al actualizar el cliente.");
	        response.put("error", e.getMessage() + " - " + e.getMostSpecificCause().getLocalizedMessage());
	        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}

}
