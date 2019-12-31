package br.com.sismed.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import br.com.sismed.domain.Log;

import br.com.sismed.repository.LogRepository;

@Service
public class LogService {
	
	
	@Autowired
	private LogRepository lrepository;
	
	@Transactional(readOnly = false)
	public void salvar(Log log) {
		lrepository.save(log);
		lrepository.verificarLog();
	}
	
	
}
