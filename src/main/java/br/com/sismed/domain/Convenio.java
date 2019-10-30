package br.com.sismed.domain;


import java.time.LocalDate;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@SuppressWarnings("serial")
@Entity
@Table(name = "sismed_convenio")
public class Convenio extends AbstractEntity<Long>{
	
	@Column(name = "nome", nullable = false, length = 60)
	private String nome;
	
	@DateTimeFormat(iso = ISO.DATE)
	@Column(name = "data_adesao",nullable = false)
	private LocalDate data_adesao ;
	
	@Column(nullable = false)
	private String cnpj;
	
	@Column(nullable = false)
	private String registro_ans;
	
	@OneToMany(mappedBy = "convenio")
	@Fetch(FetchMode.JOIN)
	private List<TConvenio> TiposConvenios;
	
	@OneToMany(mappedBy = "convenio")
	private List<Agenda> agenda;
	
	@OneToMany(mappedBy ="convenio")
	private List<Procedimento> procedimentos;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "dados_bancarios")
	private DadosBancarios dadosb;
	
	
	/*Metodos get's e set's */
	public List<Procedimento> getProcedimentos() {
		return procedimentos;
	}

	public void setProcedimentos(List<Procedimento> procedimentos) {
		this.procedimentos = procedimentos;
	}
	
	
	
	
	public String getNome() {
		return nome;
	}
	
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
	
	public LocalDate getData_adesao() {
		return data_adesao;
	}


	public void setData_adesao(LocalDate data_adesao) {
		this.data_adesao = data_adesao;
	}


	public List<Agenda> getAgenda() {
		return agenda;
	}


	public void setAgenda(List<Agenda> agenda) {
		this.agenda = agenda;
	}
	
	public List<TConvenio> getTiposConvenios() {
		return TiposConvenios;
	}
	
	
	public void setTiposConvenios(List<TConvenio> tiposConvenios) {
		TiposConvenios = tiposConvenios;
	}



	public String getCnpj() {
		return cnpj;
	}


	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}


	public String getRegistro_ans() {
		return registro_ans;
	}


	public void setRegistro_ans(String registro_ans) {
		this.registro_ans = registro_ans;
	}

	public DadosBancarios getDadosb() {
		return dadosb;
	}

	public void setDadosb(DadosBancarios dadosb) {
		this.dadosb = dadosb;
	}
	
	
	
}
