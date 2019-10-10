package br.com.sismed.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.sismed.dao.RegistroClinicoDao;
import br.com.sismed.domain.RegistroClinico;

@Service @Transactional
public class RegistroClinicoServiceImpl implements RegistroClinicoService{

	@Autowired
	private RegistroClinicoDao dao;
	
	@Override
	public void salvar(RegistroClinico registro_clinico) {
		dao.save(registro_clinico);
	}

	@Override
	public void editar(RegistroClinico registro_clinico) {
		dao.update(registro_clinico);
	}

	@Override
	public void excluir(Long id) {
		dao.delete(id);
	}

	@Override
	@Transactional(readOnly = true)
	public RegistroClinico buscarporId(Long id) {
		
		return dao.findById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<RegistroClinico> buscarTodos() {
		
		return dao.findAll();
	}
}