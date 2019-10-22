package br.com.sismed.web.conversor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import br.com.sismed.domain.Funcionario;
import br.com.sismed.service.FuncionarioService;

@Component
public class stringToFuncionarioConverter implements Converter<String, Funcionario> {

	@Autowired
	private FuncionarioService service;
	
	@Override
	public Funcionario convert(String text) {
		if(text.isEmpty()) {
			return null;
		}
		Long id = Long.valueOf(text);
 		return service.buscarporId(id);
	}
}
