package br.com.sismed.web.conversor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import br.com.sismed.domain.Convenio;

import br.com.sismed.service.ConvenioService;

@Component
public class stringToConvenioConverter implements Converter<String, Convenio> {
	
	@Autowired
	private ConvenioService service;

	@Override
	public Convenio convert(String text) {
		if(text.isEmpty()) {
			return null;
		}
		Long id = Long.valueOf(text);
 		return service.buscarPorId(id);
	}
}
