package br.com.api.picos.services;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.api.picos.dao.LoginDao;
import br.com.api.picos.objetos.Cadastro;

@Service
public class UsuarioService implements UserDetailsService {
	private LoginDao dao = new LoginDao();
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		try {
			System.out.println("USUARIO USUARIO: " + username);
			
			Cadastro usuario = dao.validaAcessoAoApp(username);
			
			return User		
					.builder()
					.username(usuario.getUsuarioCadatsro())
					.password(usuario.getSenhaCadastro())
					.roles("USER")
					.build();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}
}
