package br.com.sismed.web.conversor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import br.com.sismed.domain.Agenda;
import br.com.sismed.service.AgendaService;

@Component
public class stringToAgendaConverter implements Converter<String, Agenda>{
	
	@Autowired
	private AgendaService service;
	
	@Override
	public Agenda convert(String text) {
		if(text.isEmpty()) {
			return null;
		}
		Long id = Long.valueOf(text);
 		return service.buscarPorId(id);
	}
}
