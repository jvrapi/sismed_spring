package br.com.sismed.dao;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;


import br.com.sismed.domain.Convenio;


@Repository
public class ConvenioDaoImpl extends AbstractDao<Convenio, Long> implements ConvenioDao{

	
	@PersistenceContext
	private EntityManager entityManager;

	public List<Convenio> ListarConvenio(){
		Query query = entityManager.createNativeQuery("SELECT * " + " FROM sismed_convenio c " 
				+ " INNER JOIN sismed_tipo_convenio tc ON c.id = tc.convenio_id  ORDER BY c.nome",Convenio.class);
		List<Convenio> resultlist = query.getResultList();
		return resultlist;
	}
	
	public List<Convenio> ListaComboBox(Long id){
		Query query = entityManager.createNativeQuery("SELECT * " + " FROM sismed_convenio c " 
				+ " WHERE c.id = ?1",Convenio.class).setParameter(1, id);
		List<Convenio> resultlist = query.getResultList();
		return resultlist;
	}
	
}
