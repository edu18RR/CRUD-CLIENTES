package crud_clientes.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "clientes", schema = "public")
public class Cliente {
	
	//anotaciones quin va a ser la llave de la tabla
	@Id
	//como se va a generar la llave
	@GeneratedValue (strategy = GenerationType.IDENTITY) //IDENTITY INCREMENTA
    
    private Long id;
    private String nombre;
    private String apellido;
    private String email;
    private Date createAt;
    
    @ManyToOne(fetch= FetchType.LAZY)
    @JoinColumn(name="tipoCliente_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    private TipoCliente tipoCliente;
    
    public TipoCliente getTipoCliente() {
		return tipoCliente;
	}
	public void setTipoCliente(TipoCliente tipoCliente) {
		this.tipoCliente = tipoCliente;
	}
	//
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getCreateAt() {
		return createAt;
	}
	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}    
    
}