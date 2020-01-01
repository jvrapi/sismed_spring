package br.com.sismed.web.conversor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;


import br.com.sismed.domain.Login;
import br.com.sismed.domain.Perfil;
import br.com.sismed.service.LoginService;
import br.com.sismed.service.PerfilService;

@Component
public class stringToPerfilConverter implements Converter<String, Perfil>{
	
	@Autowired
	private PerfilService service;
	
	@Override
	public Perfil convert(String text) {
		if(text.isEmpty()) {
			return null;
		}
		Long id = Long.valueOf(text);
 		return service.buscarPorId(id);
	}
}
