package br.com.api.picos.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import br.com.api.picos.dao.CadastroDao;
import br.com.api.picos.objetos.Usuario;
import br.com.api.picos.objetos.Endereco;

@RestController
@RequestMapping("/picos")
public class CadastroController {
	
	Usuario c = new Usuario();
	Endereco e = new Endereco();
	private String ultimoCaracterEmail;

	@GetMapping("/valida/cadastro/{email}/{usuario}/{fone}/{senha}/")
	public ResponseEntity<String> validaCadastro(@PathVariable String email, @PathVariable String usuario,
			@PathVariable String fone, @PathVariable String senha) {
		CadastroDao dao = new CadastroDao();
		Gson gson = new Gson();
		String json;

		c.setEmailCadastro(email);
		c.setUsuarioCadatsro(usuario);
		c.setFoneCadastro(fone);
		c.setSenhaCadastro(senha);
		
		
		
		if (dao.validaCadastro(c.getEmailCadastro(), 1) && validaEmail(email)) {
			if(dao.validaCadastro(c.getUsuarioCadatsro(), 2)) {
				c.setSucessoCadastro("true");
				json = gson.toJson(c);
				return new ResponseEntity<>(json, HttpStatus.OK);
			} else {
				c.setSucessoCadastro("false");
				json = gson.toJson(c);
				return new ResponseEntity<>(json, HttpStatus.OK);
			}
		} else {
			c.setSucessoCadastro("false");
			json = gson.toJson(c);
			return new ResponseEntity<>(json, HttpStatus.OK);
		}
	}
	
	@GetMapping("/valida/endereco/{cep}/{rua}/{bairro}/{cidade}/{uf}/{numero}/{complemento}")
	public ResponseEntity<String> validaEndereco(@PathVariable String cep, @PathVariable String rua,
			@PathVariable String bairro, @PathVariable String cidade, @PathVariable String uf, 
			@PathVariable String numero, @PathVariable String complemento) {
		
		e.setCepEd(cep);
		e.setRuaEd(rua);
		e.setBairroEd(bairro);
		e.setCidadeEd(cidade);
		e.setUfEd(uf);
		e.setNumeroEd(numero);
		e.setComplementoEd(complemento);
		
		return new ResponseEntity<>("Recebido", HttpStatus.OK);
	}
	
	@GetMapping("/foto/cadastro/{foto}")
	public void fotoCadastro(@PathVariable String foto){
		try {
			c.setIdImagePefil(foto);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	@GetMapping("finaliza/cadastro/{tipo}")
	public ResponseEntity<String> cadastro(@PathVariable String tipo) {
		try {
			Gson gson = new Gson();
			CadastroDao dao = new CadastroDao();
			String json;
			
			if(tipo.equals("1")) {
				c.setTipoCadastro("cliente");
			}else {
				c.setTipoCadastro("prestador");
			}
			
			c.setSucessoCadastro("true");
			dao.cadastroUsuario(c, e);
			json = gson.toJson(c);
			
			return new ResponseEntity<>(json, HttpStatus.OK);	
		} catch (Exception e) {
			Gson gson = new Gson();
			String json;
			c.setSucessoCadastro("false");
			json = gson.toJson(c);
			System.out.println(e);
			return new ResponseEntity<>(json, HttpStatus.OK);
		}
	}
	
	@GetMapping("logar")
	public ResponseEntity<String> logado() {
		Gson gson = new Gson();
		String json;
		c.setUsuarioCadatsro("Toz");
		json = gson.toJson(c);
		return new ResponseEntity<>(json, HttpStatus.OK);
	}
	
	public boolean validaEmail(String email){
		ultimoCaracterEmail = email.substring(email.length() - 1, email.length());
		
		if(email.contains("@") && !email.substring(0).equals("@") 
				&& !ultimoCaracterEmail.equals("@")) {
			return true;
		}else {
			return false;
		}
	}
}
