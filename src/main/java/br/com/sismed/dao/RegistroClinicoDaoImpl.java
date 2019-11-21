package br.com.sismed.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.com.sismed.domain.Agenda;
import br.com.sismed.domain.RegistroClinico;

@Repository
public class RegistroClinicoDaoImpl extends AbstractDao<RegistroClinico, Long> implements RegistroClinicoDao{
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public List<RegistroClinico> ListarRegPaciente(Long id){
		Query query = entityManager.createNativeQuery("SELECT *" + 
				" FROM sismed_registro_clinico  " + 
				" WHERE paciente_id = ?1 ORDER BY id DESC LIMIT 6", RegistroClinico.class).setParameter(1, id);
		List<RegistroClinico> resultlist = query.getResultList();
		return resultlist;
	}
}