package billcom.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import billcom.entities.dto.ClientDto;
import billcom.services.ClientService;
import jakarta.validation.Valid;

@CrossOrigin("*")
@RestController
@RequestMapping("/restClient")
public class ClientRestController {

	private ClientService clientService;

	@Autowired
	public ClientRestController(ClientService clientService) {
		super();
		this.clientService = clientService;
	}
	
	
    @GetMapping("/list")
    public ResponseEntity<List<ClientDto>> getAllClients() {
        List<ClientDto> clients = clientService.getAllClients();
        return ResponseEntity.ok(clients);
    }
    
    
    @GetMapping("/{id}")
    public ResponseEntity<ClientDto> getClientById(@PathVariable Long id) {
        ClientDto client = clientService.getClientById(id);
        if (client != null) {
            return ResponseEntity.ok(client);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    
    @PostMapping("/add")
    public ResponseEntity<ClientDto> createClient(@RequestBody ClientDto clientDto) {
        ClientDto createdClient = clientService.createClient(clientDto);
        return new ResponseEntity<>(createdClient, HttpStatus.CREATED);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<ClientDto> updateClient(@PathVariable Long id, @Valid @RequestBody ClientDto clientDto) {
        ClientDto updatedClient = clientService.updateClient(id, clientDto);
        if (updatedClient != null) {
            return ResponseEntity.ok(updatedClient);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id) {
        clientService.deleteClient(id);
        return ResponseEntity.noContent().build();
    }
    
    
	
}
