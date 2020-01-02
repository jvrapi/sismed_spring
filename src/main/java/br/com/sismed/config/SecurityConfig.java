package br.com.sismed.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.sismed.domain.PerfilTipo;
import br.com.sismed.service.FuncionarioService;
import br.com.sismed.service.LoginService;

@EnableWebSecurity
public class SecurityConfig extends  WebSecurityConfigurerAdapter {
	
	private static final String ADMIN = PerfilTipo.ADMIN.getDesc();
    private static final String MEDICO = PerfilTipo.MEDICO.getDesc();
    private static final String PACIENTE = PerfilTipo.FUNCIONARIO.getDesc();
	
	@Autowired
	private LoginService service;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.authorizeRequests()
		// acessos públicos liberados
		.antMatchers("/webjars/**", "/css/**", "/image/**", "/js/**","/primeiroAcesso","/verificarCPF", "/login/**","/recuperar/senha","/cadastrarSenha", "/redefinir/senha","/nova/senha","/usuario/**" ).permitAll()
	
		
		
		
		//acessos privados de medico
		.antMatchers("/RegistroClinico/**").hasAnyAuthority(ADMIN,MEDICO)
		
		.antMatchers("/funcionario/cadastrar", "/funcionario/editar/**").hasAuthority(ADMIN)
		
		
		
		
		.anyRequest().authenticated()
		.and()
			.formLogin()
			.loginPage("/login")
			.defaultSuccessUrl("/", true)
			.failureUrl("/login-error")
			.permitAll()
		.and()
			.logout()
			.logoutSuccessUrl("/")
		.and()
			.exceptionHandling()
			.accessDeniedPage("/acesso-negado");
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(service).passwordEncoder(new BCryptPasswordEncoder()); // tipo de criptografia
	}

	
	
}
