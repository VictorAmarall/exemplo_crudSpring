package br.com.crud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.crud.model.Cliente;
import br.com.crud.service.ClienteService;

@RestController
public class ClienteController {

	@Autowired
	private ClienteService service;

	@RequestMapping(value = "/salvar", method = RequestMethod.PUT)
	public ResponseEntity<List<Cliente>> Salvar(@RequestBody Cliente cliente) {

		List<Cliente> clientes = service.Listar();
		if (cliente==null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		service.save(cliente);
		return ResponseEntity.ok(clientes);
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
	public ResponseEntity <List<Cliente>> deleta(@PathVariable("id")Long id) {
		List<Cliente> clientes = service.Listar();
		
		
	    service.remove(id);
		return ResponseEntity.ok(clientes);
	    
	}
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Cliente> editar(@PathVariable("id") Long id, @RequestBody Cliente cliente) {
		
		Cliente clienteUser = service.buscarPorId(id);

		if (clienteUser == null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

		clienteUser.setEmail(cliente.getEmail());
		clienteUser.setNome(cliente.getNome());
		
		service.update(clienteUser);
		return new ResponseEntity<Cliente>(clienteUser, HttpStatus.OK);
	}
	
	

}
