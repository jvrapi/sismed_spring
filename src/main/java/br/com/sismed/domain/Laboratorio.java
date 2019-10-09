package br.com.sismed.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="sismed_laboratorio")
public class Laboratorio extends AbstractEntity<Long>{
	
	@Column(name="nome", nullable=false, length=30)
	private String nome;
	
	@Column(name="responsavel", nullable=false, length=45)
	private String responsavel;
	
	@Column(name="telefone", nullable=false, length=40)
	private String telefone;
	
	@Column(name="email", nullable=false, length=15)
	private String email;
	
	@Column(name="logradouro", nullable=false, length=45)
	private String logradouro;
	
	@Column(name="numero", nullable=false, length=4)
	private String numero;
	
	@Column(name="bairro", nullable=false, length=45)
	private String bairro;
	
	@Column(name="cidade", nullable=false, length=45)
	private String cidade;
	
	@Column(name="estado", nullable=false, length=2)
	private String estado;
	
	

}
