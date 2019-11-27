package br.com.sismed.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.com.sismed.domain.Paciente;

@Repository
public class PacienteDaoImpl extends AbstractDao<Paciente, Long> implements PacienteDao{
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public List<Paciente> ListarRegPacienteAgen(String dado){
		Query query = entityManager.createNativeQuery("SELECT *" + 
				" FROM sismed_paciente  " + 
				" WHERE id = ?1", Paciente.class).setParameter(1, dado);
		List<Paciente> resultlist = query.getResultList();
		return resultlist;
	}
	
	public List<Paciente> ListarRegPaciente(String dado){
		Query query = entityManager.createNativeQuery("SELECT *" + 
				" FROM sismed_paciente" + 
				" WHERE nome LIKE ?1", Paciente.class).setParameter(1, "%" + dado + "%");
		List<Paciente> resultlist = query.getResultList();
		return resultlist;
	}
	

	public List<Paciente> PesquisarCPF(String dado){
		Query query = entityManager.createNativeQuery("SELECT *" + 
				" FROM sismed_paciente" + 
				" WHERE cpf LIKE ?1", Paciente.class).setParameter(1, "%" + dado + "%");
		List<Paciente> resultlist = query.getResultList();
		return resultlist;
	}
	

	public List<Paciente> PesquisarTelefone(String dado){
		Query query = entityManager.createNativeQuery("SELECT *" + 
				" FROM sismed_paciente" + 
				" WHERE telefone_fixo LIKE ?1", Paciente.class).setParameter(1, "%" + dado + "%");
		List<Paciente> resultlist = query.getResultList();
		return resultlist;
	}
}
