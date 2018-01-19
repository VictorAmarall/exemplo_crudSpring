package br.com.crud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.crud.model.Cliente;
import br.com.crud.service.ClienteService;

@Controller
public class ClienteController {

	@Autowired
	private ClienteService service;

	@RequestMapping(value = "/cliente", method = RequestMethod.GET)
	public void save(Cliente cliente) {
		service.save(cliente);
	}

	public void remove(Long idCliente) {
		service.remove(idCliente);
	}

	// public List<Cliente> Listar(){
	// return service.Listar();
	// }

	public Cliente buscarPorId(Long idCliente) {
		return service.buscarPorId(idCliente);
	}

	public void update(Cliente cliente) {
		service.update(cliente);
	}
	
	
	@GetMapping("/listar")
	public ResponseEntity<List<Cliente>> Listar() {

		List<Cliente> clientes = service.Listar();
		if (clientes.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return ResponseEntity.ok(clientes);
	}
	@GetMapping("/busca/{id}")
	public ResponseEntity <Cliente> pega(@PathVariable("id")Long id) {
				
	    Cliente cliente = service.buscarPorId(id);
	    
		if (cliente==null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return ResponseEntity.ok(cliente);
	}
	@GetMapping("/remove/{id}")
	public ResponseEntity <Cliente> deleta(@PathVariable("id")Long id) {
				
	    service.remove(id);
		return null;
	    
	}
	
	

}
