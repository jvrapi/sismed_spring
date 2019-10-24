package br.com.sismed.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.com.sismed.domain.Procedimento;

@Repository
public class ProcedimentoDaoImpl extends AbstractDao<Procedimento, Long> implements ProcedimentoDao{
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public List<Procedimento> ListarProcedimento(Long id){
		Query query = entityManager.createNativeQuery("SELECT p.id, p.descricao, p.valor, p.tipo_convenio " + " FROM sismed_tipo_convenio t " 
				+ "INNER JOIN sismed_procedimento p ON t.id = p.tipo_convenio WHERE t.id = ?1 ORDER BY p.descricao ", Procedimento.class).setParameter(1, id);
		List<Procedimento> resultlist = query.getResultList();
		return resultlist;
	}
	
	
}
