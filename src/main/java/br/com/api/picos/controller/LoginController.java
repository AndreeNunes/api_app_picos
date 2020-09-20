	package br.com.api.picos.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import br.com.api.picos.dao.LoginDao;
import br.com.api.picos.dto.UsuarioDTO;
import br.com.api.picos.objetos.Cadastro;

@RestController
@RequestMapping("/picos")
public class LoginController {
	
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String teste(@RequestBody UsuarioDTO usuario){
		Gson gson = new Gson();
		String json;
		LoginDao dao = new LoginDao();
		Cadastro c = new Cadastro();
		
		return usuario.getUsuario();
	}
}
