package crud_clientes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import crud_clientes.entity.TipoCliente;
import crud_clientes.service.TipoClienteService;

import java.util.List;

@RestController
@RequestMapping("/api/tiposclientes")
public class TipoClienteRestController {

    @Autowired
    private TipoClienteService tipoClienteService;

    @GetMapping
    public ResponseEntity<List<TipoCliente>> consultarTiposClientes() {
        List<TipoCliente> tiposClientes = tipoClienteService.findAll();
        return new ResponseEntity<>(tiposClientes, HttpStatus.OK);
    }

}
