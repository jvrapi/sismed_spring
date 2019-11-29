package br.com.sismed.domain;

public class RcId {
	
	private RegistroClinico rclinico;
	private Long qntId;
	
	public RegistroClinico getRclinico() {
		return rclinico;
	}
	public void setRclinico(RegistroClinico rclinico) {
		this.rclinico = rclinico;
	}
	public Long getQntId() {
		return qntId;
	}
	public void setQntId(Long qntId) {
		this.qntId = qntId;
	}
}
