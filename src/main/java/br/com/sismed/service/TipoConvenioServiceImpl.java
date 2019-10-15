package br.com.sismed.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.sismed.domain.TipoConvenio;

@Service
public class TipoConvenioServiceImpl implements TipoConvenioService{

	@Override
	@Transactional(readOnly = false)
	public void salvar(TipoConvenio convenio) {
		// TODO Auto-generated method stub
		
	}

	@Override
	@Transactional(readOnly = false)
	public void editar(TipoConvenio convenio) {
		// TODO Auto-generated method stub
		
	}

	@Override
	@Transactional(readOnly = false)
	public void excluir(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	@Transactional(readOnly = true)
	public TipoConvenio buscarPorId(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(readOnly = true)
	public List<TipoConvenio> BuscarTodos() {
		// TODO Auto-generated method stub
		return null;
	}

}


