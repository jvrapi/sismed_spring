package br.com.sismed.dao;



import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;


import br.com.sismed.domain.TConvenio;

@Repository
public class TConvenioDaoImpl extends AbstractDao<TConvenio, Long> implements TConvenioDao{

	@PersistenceContext
	private EntityManager entityManager;

	public List<TConvenio> ListarTipoConvenio(Long id){
		Query query = entityManager.createNativeQuery("SELECT tc.id,tc.nome, tc.convenio_id" + " FROM sismed_convenio c " 
				+ " INNER JOIN sismed_tipo_convenio tc ON c.id = tc.convenio_id WHERE c.id = ?1 ORDER BY tc.nome",TConvenio.class).setParameter(1, id);
		List<TConvenio> resultlist = query.getResultList();
		return resultlist;
	}
	
	public List<TConvenio> ListaComboBox(Long id){
		Query query = entityManager.createNativeQuery("SELECT tc.id,tc.nome, tc.convenio_id" + 
				" FROM sismed_convenio c JOIN sismed_tipo_convenio tc ON c.id = tc.convenio_id" + 
				" WHERE c.id = ?1", TConvenio.class).setParameter(1, id);
		List<TConvenio> resultlist = query.getResultList();
		return resultlist;
	}

	public List<TConvenio> ListaCad(Long id){
		Query query = entityManager.createNativeQuery("SELECT *" + 
				" FROM sismed_tipo_convenio  " + 
				" WHERE convenio_id = ?1", TConvenio.class).setParameter(1, id);
		List<TConvenio> resultlist = query.getResultList();
		return resultlist;
	}
}
