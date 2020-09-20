package br.com.api.picos.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import br.com.api.picos.dao.CarregaInfoUsuarioDao;
import br.com.api.picos.objetos.Usuario;

@RestController
@RequestMapping("/picos/info-usuario")
public class CarregaInfoUsuarioController {
	
	@PostMapping
	public ResponseEntity<String> carrega(@RequestParam String usuario){
		CarregaInfoUsuarioDao dao = new CarregaInfoUsuarioDao();
		Gson gson = new Gson();
		String json;
		Usuario c = new Usuario();
		
		c = dao.carregaInfo(usuario);
		
		if(c != null) {
			json = gson.toJson(c);
			return new ResponseEntity<>(json, HttpStatus.OK);
		}else {
			return new ResponseEntity<>("Ocorreu algum erro", HttpStatus.BAD_REQUEST);
		}	
	}
}
