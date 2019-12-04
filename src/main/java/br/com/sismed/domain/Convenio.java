package br.com.sismed.domain;


import java.time.LocalDate;
import java.util.List;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@SuppressWarnings("serial")
@Entity
@Table(name = "sismed_convenio")
public class Convenio extends AbstractEntity{
	
	@NotBlank
	@Size(min = 3, max = 50)
	@Column(name = "nome", nullable = false, length = 60)
	private String nome;
	
	
	@NotNull
	@PastOrPresent(message = "{PastOrPresent.convenio.data_adesao}")
	@DateTimeFormat(iso = ISO.DATE)
	@Column(name = "data_adesao",nullable = false)
	private LocalDate data_adesao ;
	
	@NotBlank
	@Column(nullable = false)
	private String cnpj;
	
	@Column(nullable = false)
	private String registro_ans;

	@Valid
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "dados_bancarios")
	private DadosBancarios dadosb;
	
	
	/*Metodos get's e set's */

	
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
