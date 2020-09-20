package br.com.api.picos.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import br.com.api.picos.connection.ConnectionFactory;
import br.com.api.picos.objetos.Usuario;
import br.com.api.picos.objetos.Endereco;

public class CadastroDao {
	Connection connection;
	
	public CadastroDao() {
		this.connection = new ConnectionFactory().getConnection();
	}
	
	public void cadastroUsuario(Usuario c, Endereco e) {
		String sql = "insert into pc_enderecos (edCep, edRua, edBairro, edCidade, edUf, edNumero, edComplemento)"
				+ "values (?,?,?,?,?,?,?);";
		
		try {
			PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			stmt.setString(1, e.getCepEd());
			stmt.setString(2, e.getRuaEd());
			stmt.setString(3, e.getBairroEd());
			stmt.setString(4, e.getCidadeEd());
			stmt.setString(5, e.getUfEd());
			stmt.setString(6, e.getNumeroEd());
			stmt.setString(7, e.getComplementoEd());
			
			stmt.execute();
			
			ResultSet rs = stmt.getGeneratedKeys();
			
			if(rs.next()) {
				e.setIdEd(rs.getInt(1));
			}
			
			stmt.close();
			
		} catch (Exception erro) {
			throw new RuntimeException(erro);
		}
		                                                                                                
		sql = "insert into pc_usuarios (emailUsuario, nomeUsuario, foneUsuario, "
				+ "senhaUsuario, tipoUsuario, idFotoPerfil, idEndereco) values (?,?,?,?,?,?,?)";
		
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			stmt.setString(1, c.getEmailCadastro());
			stmt.setString(2, c.getUsuarioCadatsro());
			stmt.setString(3, c.getFoneCadastro());
			stmt.setString(4, c.getSenhaCadastro());
			stmt.setString(5, c.getTipoCadastro());
			stmt.setString(6, c.getIdImagePefil());
			stmt.setInt(7, e.getIdEd());
			
			stmt.execute();
			stmt.close();
			
		} catch (Exception erro) {
			throw new RuntimeException(erro);
		}
	}
	
	public boolean validaCadastro(String validacao, int oqValida) {
		// oqValida { 1 - EMAIL } { 2 - USUARIO }
		int valida = 0;
		
		String sql = "select *"
				+ " from pc_usuarios"
				+ " where ";
		
		if(oqValida == 1) {
			sql += "emailUsuario = '" + validacao + "'";
		}else if (oqValida == 2) {
			sql += "nomeUsuario = '" + validacao + "'";
		}
		
		try {
			
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				valida++;
			}
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		if(valida >= 1) {
			return false;
		}else {
			return true;
		}
	}
}
