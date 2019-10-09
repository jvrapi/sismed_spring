package br.com.sismed.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

@SuppressWarnings("serial")
@Entity
@Table(name = "sismed_convenio")
public class Convenio extends AbstractEntity<Long>{
	
	@Column(name = "nome", nullable = false, length = 60)
	private String nome;
	
	@Column(name = "data_adesao",nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date data_adesao = new Date();
	
	@Column(name = "banco",nullable = false, length = 5)
	private String banco;
	
	@Column(name = "agencia",nullable = false, length = 5)
	private String agencia;
	
	@Column(name = "numero_conta",nullable = false, length = 8)
	private String numero_conta;
	
	@Column(name = "aceita",nullable = false, length = 4)
	private String aceita;
	
	
	@OneToMany(mappedBy = "convenio")
	private List<TipoConvenio> TiposConvenios;
	
	@OneToMany(mappedBy = "convenio")
	private List<Agenda> agenda;
	
	/*Metodos get's e set's */
	
	public String getNome() {
		return nome;
	}
	
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
	public Date getData_adesao() {
		return data_adesao;
	}
	
	
	public void setData_adesao(Date data_adesao) {
		this.data_adesao = data_adesao;
	}
	
	
	public String getBanco() {
		return banco;
	}
	
	
	public void setBanco(String banco) {
		this.banco = banco;
	}
	
	
	public String getAgencia() {
		return agencia;
	}
	
	
	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}
	
	
	public String getNumero_conta() {
		return numero_conta;
	}
	
	
	public void setNumero_conta(String numero_conta) {
		this.numero_conta = numero_conta;
	}
	
	
	public String getAceita() {
		return aceita;
	}
	
	
	public void setAceita(String aceita) {
		this.aceita = aceita;
	}
	
	
	public List<TipoConvenio> getTiposConvenios() {
		return TiposConvenios;
	}
	
	
	public void setTiposConvenios(List<TipoConvenio> tiposConvenios) {
		TiposConvenios = tiposConvenios;
	}
	
	
}
