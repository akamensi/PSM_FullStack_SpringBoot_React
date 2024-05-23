package billcom.services;

import java.util.*;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import billcom.entities.Client;
import billcom.entities.dto.ClientDto;
import billcom.exception.ResourceNotFoundException;
import billcom.repositories.ClientRepository;

@Service
public class ClientService {
	
	private ClientRepository clientRepository;
	private  ModelMapper modelMapper; 

	@Autowired
	public ClientService(ClientRepository clientRepository, ModelMapper modelMapper) {
		super();
		this.clientRepository = clientRepository;
		this.modelMapper = modelMapper;
	}
	
	
    public List<ClientDto> getAllClients() {
        List<Client> clients = clientRepository.findAll();
        return clients.stream()
                      .map(client -> modelMapper.map(client, ClientDto.class))
                      .collect(Collectors.toList());
    }
    
    public ClientDto getClientById(Long id) {
        Optional<Client> optionalClient = clientRepository.findById(id);
        if (optionalClient.isPresent()) {
            return modelMapper.map(optionalClient.get(), ClientDto.class);
        } else {
        	throw new ResourceNotFoundException("User not found with id: " + id);
        }
    }
    
    public ClientDto createClient(ClientDto clientDto) {
        Client client = modelMapper.map(clientDto, Client.class);
        client = clientRepository.save(client);
        return modelMapper.map(client, ClientDto.class);
    }
    
    
    public ClientDto updateClient(Long id, ClientDto clientDto) {
        Optional<Client> optionalClient = clientRepository.findById(id);
        if (optionalClient.isPresent()) {
            Client client = optionalClient.get();
            client.setNom(clientDto.getNom());
            client.setTel(clientDto.getTel());
            client.setEmail(clientDto.getEmail());
            client.setAdresse(clientDto.getAdresse());
            client = clientRepository.save(client);
            return modelMapper.map(client, ClientDto.class);
        } else {
        	throw new ResourceNotFoundException("User not found with id: " + id);
        }
    }
    
    
    public void deleteClient(Long id) {
        clientRepository.deleteById(id);
    }
	
	

}
