package br.com.sismed.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.com.sismed.domain.Funcionario;

@Repository
public class FuncionarioDaoImpl extends AbstractDao<Funcionario, Long> implements FuncionarioDao{
	
	@PersistenceContext
	private EntityManager entityManager;

	public List<Funcionario> ListarFuncionarioId(String dado){
		Query query = entityManager.createNativeQuery("SELECT *" + 
				" FROM sismed_funcionario" + 
				" WHERE nome LIKE ?1", Funcionario.class).setParameter(1, "%" + dado + "%");
		List<Funcionario> resultlist = query.getResultList();
		return resultlist;
	}
}
