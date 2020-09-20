package br.com.api.picos.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.com.api.picos.connection.ConnectionFactory;
import br.com.api.picos.objetos.Usuario;

public class CarregaInfoUsuarioDao {
	Connection connection;
	
	public CarregaInfoUsuarioDao() {
		this.connection = new ConnectionFactory().getConnection();
	}
	
	public Usuario carregaInfo(String usuario) {
		Usuario c = new Usuario();
		
		String sql = "select idUsuarios, emailUsuario, nomeUsuario, "
				+ "foneUsuario, senhaUsuario, tipoUsuario, "
				+ "idFotoPerfil, idEndereco, loginUsuario "
				+ "from pc_usuarios "
				+ "where loginUsuario = ?";
		
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			stmt.setString(1, usuario);
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				c.setIdCadastro(rs.getInt("idUsuarios"));
				c.setEmailCadastro(rs.getString("emailUsuario"));
				c.setUsuarioCadatsro(rs.getString("loginUsuario"));
				c.setNomeCadastro(rs.getString("nomeUsuario"));
				c.setFoneCadastro(rs.getString("foneUsuario"));
				c.setSenhaCadastro(rs.getString("senhaUsuario"));
				c.setTipoCadastro(rs.getString("tipoUsuario"));
				c.setIdImagePefil(rs.getString("idFotoPerfil"));
				c.setEnderecoUsuario(rs.getInt("idEndereco"));
			}
			
			return c;
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
