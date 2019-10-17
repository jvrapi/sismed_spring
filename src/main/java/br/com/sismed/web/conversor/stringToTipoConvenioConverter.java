package br.com.sismed.web.conversor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import br.com.sismed.domain.TConvenio;

import br.com.sismed.service.TConvenioService;


@Component
public class stringToTipoConvenioConverter implements Converter<String, TConvenio>{

	@Autowired
	private TConvenioService service;
	
	@Override
	public TConvenio convert(String text) {
		if(text.isEmpty()) {
			return null;
		}
		Long id = Long.valueOf(text);
 		return service.buscarPorId(id);
	}
}
