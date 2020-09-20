package br.com.api.picos.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.com.api.picos.connection.ConnectionFactory;
import br.com.api.picos.objetos.Cadastro;

public class LoginDao {
	
	Connection connection;
	
	public LoginDao() {
		System.out.println("TOMAAA");
		this.connection = new ConnectionFactory().getConnection();
	}
	
	public Cadastro validaAcessoAoApp(String usuario) {
		
		System.out.println("USUARIO: " + usuario);
		
		Cadastro c = new Cadastro();
		
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
				c.setLogadoCadastro("true");
			}
			
			return c;
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
