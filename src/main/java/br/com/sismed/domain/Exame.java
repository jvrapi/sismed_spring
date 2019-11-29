package br.com.sismed.domain;

import java.sql.Date;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@SuppressWarnings("serial")
@Entity
@Table(name = "sismed_exame")
public class Exame extends AbstractEntity<Long>{
	
	@Column(name = "nome",nullable = false)
	private String nome;
	
	@Column(name = "descricao",nullable = false)
	private String descricao;
	
	@Column(name = "data_coleta",nullable = false)
	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate data_coleta;
	
	@Column(name = "data_envio",nullable = false)
	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate data_envio;
	
	@Column(name = "data_retorno",nullable = true)
	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate data_retorno;
	
	@Column(name = "funcionario_lab",nullable = false)
	private String funcionario_lab;
	
	@ManyToOne
	@JoinColumn(name = "paciente_id") 
	private Paciente paciente_id;
	
	@ManyToOne
	@JoinColumn(name = "funcionario_id")
	private Funcionario funcionario_id;
	
	@ManyToOne
	@JoinColumn(name="tipo_id")
	private TConvenio tipo;
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public LocalDate getData_coleta() {
		return data_coleta;
	}

	public void setData_coleta(LocalDate data_coleta) {
		this.data_coleta = data_coleta;
	}

	public LocalDate getData_envio() {
		return data_envio;
	}

	public void setData_envio(LocalDate data_envio) {
		this.data_envio = data_envio;
	}

	public LocalDate getData_retorno() {
		return data_retorno;
	}

	public void setData_retorno(LocalDate data_retorno) {
		this.data_retorno = data_retorno;
	}

	public Paciente getPaciente_id() {
		return paciente_id;
	}

	public void setPaciente_id(Paciente paciente_id) {
		this.paciente_id = paciente_id;
	}

	public Funcionario getFuncionario_id() {
		return funcionario_id;
	}

	public void setFuncionario_id(Funcionario funcionario_id) {
		this.funcionario_id = funcionario_id;
	}

	public TConvenio getTipo() {
		return tipo;
	}

	public void setTipo(TConvenio tipo) {
		this.tipo = tipo;
	}

	public String getFuncionario_lab() {
		return funcionario_lab;
	}

	public void setFuncionario_lab(String funcionario_lab) {
		this.funcionario_lab = funcionario_lab;
	}

	
	
	/*public boolean VerificaDataColetaEnvio(){
		
		boolean data;
		
		if(getData_coleta().isBefore(getData_envio())) {
			
			data = true;
		}
		else {
			
			data = false;
		}

		return data;
		}
	
		public boolean VerificaDataEnvioRetorno(){
			
			boolean data;
			
			if(getData_envio().isAfter(getData_retorno())) {
				
				data = true;
			}
			else {
				
				data = false;
			}

			return data;
			}*/
}
