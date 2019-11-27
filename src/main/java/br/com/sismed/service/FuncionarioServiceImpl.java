package br.com.sismed.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.sismed.dao.FuncionarioDao;
import br.com.sismed.domain.Funcionario;
import br.com.sismed.repository.FuncionarioRepository;

@Repository
@Transactional(readOnly = false)
public class FuncionarioServiceImpl implements FuncionarioService{
	
	@Autowired
	private FuncionarioRepository fRepository;

	@Autowired
	private FuncionarioDao dao;
	
	@Override
	public void salvar(Funcionario funcionario) {
		dao.save(funcionario);
	}

	@Override
	public void excluir(Long id) {
		fRepository.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Funcionario buscarporId(Long id) {
		return fRepository.getOne(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Funcionario> buscarTodos(Pageable pageable) {
		return fRepository.findAll(pageable);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Funcionario> findAll() {
		return fRepository.findAll();
	}
	
	@Override
	public List<Funcionario> ListarFuncionarioNome(String dado) {
		return fRepository.ListarFuncionarioNome(dado);
	}

}
