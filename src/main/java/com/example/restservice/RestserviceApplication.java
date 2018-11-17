package com.example.restservice;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import lombok.Getter;
import lombok.Setter;


@SpringBootApplication
public class RestserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestserviceApplication.class, args);
	}
}

@Getter
@Setter
class Cidade {

	private Long id;
	private String nome;
	private Estado estado;
	public Cidade(Long id, String nome, Estado estado) {
		super();
		this.id = id;
		this.nome = nome;
		this.estado = estado;
	}
	
}

@Getter
@Setter
class Estado {

	private Long id;
	private String nome;
	public Estado(Long id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
	}
	
}

@RestController
class DAO {

	List<Cidade> cidades = new ArrayList<>();

	public List<Cidade> listaFake() {
		cidades.add(new Cidade(new Long(1), "SLZ", new Estado(new Long(1), "MA")));
		cidades.add(new Cidade(new Long(2), "FOR", new Estado(new Long(2), "CE")));
		cidades.add(new Cidade(new Long(3), "SP", new Estado(new Long(3), "SP")));
		return cidades;
	}

	@RequestMapping(value = "/cidade", method = RequestMethod.GET, produces = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Cidade>> buscarTodos() {

		return new ResponseEntity<>(listaFake(), HttpStatus.OK);
	}

	@RequestMapping(value = "/cidade", method = RequestMethod.POST, produces = org.springframework.http.MediaType.APPLICATION_JSON_VALUE, consumes = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Cidade> create(@RequestBody Cidade cidade) {

		return new ResponseEntity<>(cidade, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/cidade", method = RequestMethod.PUT, consumes = org.springframework.http.MediaType.APPLICATION_JSON_VALUE, produces = org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Cidade> update(@RequestBody Cidade cidade) {
		return new ResponseEntity<>(cidade, HttpStatus.OK);
	}

	@RequestMapping(value = "/cidade/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Cidade> delete(@PathVariable Long id) {
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
