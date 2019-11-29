package br.com.sismed.domain;

import javax.persistence.*;

@SuppressWarnings("serial")
@Entity
@Table(name = "sismed_perfis")
public class Perfil extends AbstractEntity {

	@Column(name = "descricao", nullable = false, unique = true)
	private String desc;
	
	public Perfil() {
		super();
	}

	
	public Perfil(Long id) {
		super.setId(id);
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	
}
