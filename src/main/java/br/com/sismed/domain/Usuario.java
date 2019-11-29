package br.com.sismed.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@SuppressWarnings("serial")
@Entity
@Table(name = "sismed_usuario", indexes = {@Index(name = "idx_usuario_cpf", columnList = "cpf")})
public class Usuario extends AbstractEntity{

	@Column(name = "cpf", unique = true, nullable = false)
	private String cpf;
	
	@Column(name = "senha", nullable = false)
	private String senha;
	
	@Column(name = "email", nullable = false)
	private String email;
	
	@OneToOne
	@JoinColumn(name = "funcionario_id")
	private Funcionario funcionario;
	
	@ManyToMany
	@JoinTable(
		name = "sismed_usuario_perfil", 
        joinColumns = { @JoinColumn(name = "usuario_id", referencedColumnName = "id") }, 
        inverseJoinColumns = { @JoinColumn(name = "perfis_id", referencedColumnName = "id") }
	)
	private List<Perfil> perfis;
	
	@Column(name = "ativo", nullable = false, columnDefinition = "TINYINT(1)")
	private boolean ativo;
	
	@Column(name = "codigo_verificador", length = 6)
	private String codigoVerificador;
	
	
	
	public Usuario() {
		super();
	}
	
	public Usuario(Long id) {
		super.setId(id);
	}
	
	// adiciona perfis a lista
		public void addPerfil(PerfilTipo tipo) {
			if (this.perfis == null) {
				this.perfis = new ArrayList<>();
			}
			this.perfis.add(new Perfil(tipo.getCod()));
		}

		public Usuario(String cpf) {
			this.cpf = cpf;
		}

		public String getCpf() {
			return cpf;
		}

		public void setCpf(String cpf) {
			this.cpf = cpf;
		}

		public String getSenha() {
			return senha;
		}

		public void setSenha(String senha) {
			this.senha = senha;
		}

		public List<Perfil> getPerfis() {
			return perfis;
		}

		public void setPerfis(List<Perfil> perfis) {
			this.perfis = perfis;
		}

		public boolean isAtivo() {
			return ativo;
		}

		public void setAtivo(boolean ativo) {
			this.ativo = ativo;
		}

		public String getCodigoVerificador() {
			return codigoVerificador;
		}

		public void setCodigoVerificador(String codigoVerificador) {
			this.codigoVerificador = codigoVerificador;
		}

		public Funcionario getFuncionario() {
			return funcionario;
		}

		public void setFuncionario(Funcionario funcionario) {
			this.funcionario = funcionario;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}
		
		
		
}
