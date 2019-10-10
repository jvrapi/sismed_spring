package br.com.sismed.domain;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

@SuppressWarnings("serial")
@Entity
@Table(name = "sismed_procedimento")
public abstract class Procedimento extends AbstractEntity<Long> {
	
	@Column(nullable = false, length = 60)
	private String descricao;
	
	@Column(nullable = false)
	private double valor;
	
	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public double getValor() {
		return valor;
	}
	
	public void setValor(double valor) {
		this.valor = valor;
	}
	
		
	@OneToMany(mappedBy = "procedimento") // nome do atributo na classe Agenda
	private List<Agenda> agenda;
}
