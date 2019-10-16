package br.com.sismed.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.sismed.dao.ProcedimentoDao;
import br.com.sismed.domain.Procedimento;

@Service 
public class ProcedimentoServiceImpl implements ProcedimentoService{

	
	@Autowired
	private ProcedimentoDao dao;
	
	@Override
	@Transactional(readOnly = false)
	public void salvar(Procedimento procedimento) {
		dao.save(procedimento);
		
	}

	@Override
	@Transactional(readOnly = false)
	public void editar(Procedimento procedimento) {
		dao.update(procedimento);
		
	}

	@Override
	@Transactional(readOnly = false)
	public void deletar(Long id) {
		dao.delete(id);
		
	}

	@Override
	@Transactional(readOnly = true)
	public Procedimento BuscarPorId(Long id) {
		
		return dao.findById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Procedimento> BuscarTodos() {
		
		return dao.findAll();
	}

}
