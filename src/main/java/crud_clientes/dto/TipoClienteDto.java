package crud_clientes.dto;

import java.io.Serializable;

public class TipoClienteDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private String tipoCliente;

	public String getTipoCliente() {
		return tipoCliente;
	}

	public void setTipoCliente(String tipoCliente) {
		this.tipoCliente = tipoCliente;
	}

   
}