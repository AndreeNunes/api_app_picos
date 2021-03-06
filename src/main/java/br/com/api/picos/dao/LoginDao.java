package br.com.api.picos.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.com.api.picos.connection.ConnectionFactory;
import br.com.api.picos.objetos.Usuario;

public class LoginDao {
	
	Connection connection;
	
	public LoginDao() {
		this.connection = new ConnectionFactory().getConnection();
	}
	
	public Usuario validaAcessoAoApp(String usuario) {
		Usuario c = new Usuario();
		
		String sql = "select emailUsuario, nomeUsuario,"
				+ " foneUsuario, senhaUsuario, tipoUsuario"
				+ " from pc_usuarios"
				+ " where nomeUsuario = ?";
		
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			stmt.setString(1, usuario);
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				c.setEmailCadastro(rs.getString("emailUsuario"));
				c.setUsuarioCadatsro(rs.getString("nomeUsuario"));
				c.setFoneCadastro(rs.getString("foneUsuario"));
				c.setSenhaCadastro(rs.getString("senhaUsuario"));
				c.setTipoCadastro(rs.getString("tipoUsuario"));
			}
			
			return c;
		
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
