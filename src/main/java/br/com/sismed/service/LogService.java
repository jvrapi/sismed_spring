package br.com.sismed.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import br.com.sismed.domain.Log;

import br.com.sismed.repository.LogRepository;

@Service
public class LogService {
	
	
	@Autowired
	private LogRepository lrepository;
	
	@Transactional(readOnly = true)
	public void verificarLog() {
		 lrepository.verificarLog();
	}
	
	@Transactional(readOnly = false)
	public void salvar(Log log) {
		lrepository.save(log);
		verificarLog();
	}
	
	@Transactional(readOnly = true)
	public Page<Log> listarTodos(Pageable pageable) {
		return lrepository.findAllLogs(pageable);
		
	}
	
	
}
