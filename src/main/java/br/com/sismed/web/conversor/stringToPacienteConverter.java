package br.com.sismed.web.conversor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import br.com.sismed.domain.Paciente;
import br.com.sismed.service.PacienteService;


@Component
public class stringToPacienteConverter implements Converter<String, Paciente>{

	@Autowired
	private PacienteService service;
	
	@Override
	public Paciente convert(String text) {
		if(text.isEmpty()) {
			return null;
		}
		Long id = Long.valueOf(text);
 		return service.buscarporId(id);
	}
}
