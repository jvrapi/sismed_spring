package br.com.sismed.web.controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.sismed.domain.Login;
import br.com.sismed.service.LoginService;

@Controller
@RequestMapping("/restore")
public class RestoreController {

	@Autowired
	private LoginService lservice;

	@Autowired
	private DataSource datasource;

	@Value("${spring.datasource.username}")
	private String host;

	@Value("${spring.datasource.password}")
	private String password;

	private String dataBase = "tresta";
	
	@GetMapping
	public String abrirPaginaRestore(ModelMap model) throws SQLException {
		Connection metaData = datasource.getConnection();
		Statement s = metaData.createStatement();
		ResultSet r = s.executeQuery("SHOW TABLES;");
		List<String> tabelas = new ArrayList<String>();
		while (r.next()) {
			tabelas.add(r.getString("Tables_in_tresta"));
		}
		model.addAttribute("tabelas", tabelas);
		return "restore/tabelas";
	}
	
	
	
	@ModelAttribute("usuarioLogado")
	public String usuarioLogado(@AuthenticationPrincipal User user, ModelMap model) {
		Login l = lservice.BuscarPorCPF(user.getUsername());
		String pattern = "\\S+";
		Pattern r = Pattern.compile(pattern);
		Matcher m = r.matcher(l.getFuncionario_id().getNome());
		String retorno = "";
		if (m.find()) {
			retorno = m.group(0);

			model.addAttribute("usuario", m.group(0));

		} else {
			// mensagem de erro
			retorno = l.getFuncionario_id().getNome();
		}

		return retorno;
	}

	String dataHoraAtual() {
		DateTimeFormatter dataFormatter =  DateTimeFormatter.ofPattern("dd/MM/yyyy");
		DateTimeFormatter horaFormatter =  DateTimeFormatter.ofPattern("HH:mm:ss");
		LocalDate data = LocalDate.now();
		LocalTime hora = LocalTime.now();
		String dataFormatada = dataFormatter.format(data);
		String horaFormatada = horaFormatter.format(hora);
		return dataFormatada + " As " + horaFormatada;
	}
}
