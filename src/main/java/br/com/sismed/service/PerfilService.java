package br.com.sismed.service;

import org.springframework.stereotype.*;

import br.com.sismed.domain.Perfil;
import br.com.sismed.repository.PerfilRepository;

import org.springframework.beans.factory.annotation.Autowired;

@Service
public class PerfilService {

	@Autowired 
	private PerfilRepository repository;
	
	public Perfil buscarPorId(Long id) {
		return repository.getOne(id);
	}
}
