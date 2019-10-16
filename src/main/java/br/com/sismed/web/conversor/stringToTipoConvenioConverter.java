package br.com.sismed.web.conversor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import br.com.sismed.domain.TipoConvenio;
import br.com.sismed.service.TipoConvenioService;

@Component
public class stringToTipoConvenioConverter implements Converter<String, TipoConvenio>{

	@Autowired
	private TipoConvenioService service;
	
	@Override
	public TipoConvenio convert(String text) {
		if(text.isEmpty()) {
			return null;
		}
		Long id = Long.valueOf(text);
 		return service.BuscarPorId(id);
	}
}
