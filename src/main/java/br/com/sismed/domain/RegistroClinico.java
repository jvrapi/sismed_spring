package br.com.sismed.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "sismed_registro_clinico")
public class RegistroClinico extends AbstractEntity<Long>{

	@Column(name = "data", nullable = false)
	private LocalDateTime data = LocalDateTime.now();
	
	@Column(name = "hora", nullable = false)
	private LocalDateTime hora = LocalDateTime.now();
	
	@Column(name = "descricao", nullable = false)
	private String descricao;
	
	@ManyToOne
	@JoinColumn(name = "paciente_prontuario", nullable = false)
	private Paciente prontuario;
	
	@ManyToOne
	@JoinColumn(name = "convenio", nullable = false)
	private Convenio convenio;
	
	@ManyToOne
	@JoinColumn(name = "tipo_convenio", nullable = false)
	private TipoConvenio tipo_convenio;
	
	@ManyToOne
	@JoinColumn(name = "sismed_funcionario_id", nullable = false)
	private Funcionario funcionario_id;
	
	public LocalDateTime getData() {
		return data;
	}

	public void setData(LocalDateTime data) {
		this.data = data;
	}

	public LocalDateTime getHora() {
		return hora;
	}

	public void setHora(LocalDateTime hora) {
		this.hora = hora;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Paciente getProntuario() {
		return prontuario;
	}

	public void setProntuario(Paciente prontuario) {
		this.prontuario = prontuario;
	}

	public Convenio getConvenio() {
		return convenio;
	}

	public void setConvenio(Convenio convenio) {
		this.convenio = convenio;
	}

	public TipoConvenio getTipo_convenio() {
		return tipo_convenio;
	}

	public void setTipo_convenio(TipoConvenio tipo_convenio) {
		this.tipo_convenio = tipo_convenio;
	}

	public Funcionario getFuncionario_id() {
		return funcionario_id;
	}

	public void setFuncionario_id(Funcionario funcionario_id) {
		this.funcionario_id = funcionario_id;
	}
}
