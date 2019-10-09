package br.com.sismed.domain;

import java.util.Date;

import javax.persistence.*;


@SuppressWarnings("serial")
@Entity
@Table(name="sismed_agenda")
public abstract class Agenda extends AbstractEntity<Long> {
	
	@ManyToOne
	@Column(nullable = false)
	@JoinColumn(name = "paciente_prontuario") // nome da chave estrangeira no banco de dados
	private Paciente paciente_prontuario;
	
	@ManyToOne
	@Column(nullable = false)
	@JoinColumn(name = "convenio") // nome da chave estrangeira no banco de dados
	private Convenio convenio;
	
	@ManyToOne
	@Column(nullable = false)
	@JoinColumn(name = "tipo_convenio_id") // nome da chave estrangeira no banco de dados
	private TipoConvenio tipo_convenio;
	
	@ManyToOne
	@Column(nullable = false)
	@JoinColumn(name = "procedimento_id") // nome da chave estrangeira no banco de dados
	private Procedimento procedimento;
	
	@ManyToOne
	@Column(nullable = false)
	@JoinColumn(name = "funcionario_id") // nome da chave estrangeira no banco de dados
	private Funcionario funcionario;
	
	@Column(nullable = false)
	private String observavao;
	
	@Column(nullable = false)
	private String compareceu;
	
	@Column(nullable = false)
	private String pagou;
	
	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date data = new Date();
	
	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date hora = new Date();

	
	
	
	public Paciente getPaciente_prontuario() {
		return paciente_prontuario;
	}

	public void setPaciente_prontuario(Paciente paciente_prontuario) {
		this.paciente_prontuario = paciente_prontuario;
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

	public Procedimento getProcedimento() {
		return procedimento;
	}

	public void setProcedimento(Procedimento procedimento) {
		this.procedimento = procedimento;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public String getObservavao() {
		return observavao;
	}

	public void setObservavao(String observavao) {
		this.observavao = observavao;
	}

	public String getCompareceu() {
		return compareceu;
	}

	public void setCompareceu(String compareceu) {
		this.compareceu = compareceu;
	}

	public String getPagou() {
		return pagou;
	}

	public void setPagou(String pagou) {
		this.pagou = pagou;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Date getHora() {
		return hora;
	}

	public void setHora(Date hora) {
		this.hora = hora;
	}
	
	
}
