package br.com.sismed.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.com.sismed.domain.Agenda;
import br.com.sismed.domain.Convenio;

@Repository
public class AgendaDaoImpl extends AbstractDao<Agenda, Long> implements AgendaDao{
	@PersistenceContext
	private EntityManager entityManager;

	public List<Agenda> ListarAgendamentos(){
		Query query = entityManager.createNativeQuery("SELECT * " + " FROM sismed_agenda  " 
				+ "WHERE data = (CURDATE()) ORDER BY hora ASC ",Agenda.class);
		List<Agenda> resultlist = query.getResultList();
		return resultlist;
	}
}
