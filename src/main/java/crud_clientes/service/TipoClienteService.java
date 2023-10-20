package crud_clientes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import crud_clientes.entity.TipoCliente;
import crud_clientes.repository.ITipoClienteRepository;

import java.util.List;

@Service
public class TipoClienteService {

    @Autowired
    private ITipoClienteRepository tipoClienteRepository;

    @Transactional(readOnly = true)
    public List<TipoCliente> findAll() {
        return tipoClienteRepository.findAll();
    }

    @Transactional
    public TipoCliente findById(Long id) {
        return tipoClienteRepository.findById(id).orElse(null);
    }

}
