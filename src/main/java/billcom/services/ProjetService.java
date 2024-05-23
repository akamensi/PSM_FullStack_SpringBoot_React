package billcom.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import billcom.entities.Client;
import billcom.entities.Projet;
import billcom.entities.dto.ProjetDto;
import billcom.exception.ResourceNotFoundException;
import billcom.repositories.ClientRepository;
import billcom.repositories.ProjetRepository;

@Service
public class ProjetService {
	
	private final Path root = Paths.get(System.getProperty("user.dir") + "/src/main/resources/static/uploads");
	
    private  ProjetRepository projetRepository;
    private  ModelMapper modelMapper;
    private  ClientRepository clientRepository;
    

    
    public ProjetService(ProjetRepository projetRepository, ModelMapper modelMapper,
			ClientRepository clientRepository) {
		super();
		this.projetRepository = projetRepository;
		this.modelMapper = modelMapper;
		this.clientRepository = clientRepository;
	}

	public List<ProjetDto> getAllProjets() {
        List<Projet> projets = projetRepository.findAll();
        return projets.stream()
                .map(projet -> modelMapper.map(projet, ProjetDto.class))
                .collect(Collectors.toList());
    }
    
    public ProjetDto getProjetById(Long id) {
        Optional<Projet> optionalProjet = projetRepository.findById(id);
        return optionalProjet.map(projet -> modelMapper.map(projet, ProjetDto.class)).orElse(null);
    }
    
    
    
    public ProjetDto createProjet(ProjetDto projetDto, MultipartFile file) throws IOException {
        // Generate new random image name
        String newImageName = getSaltString().concat(file.getOriginalFilename());

        try {
            // Ensure the uploads directory exists
            if (!Files.exists(root)) {
                Files.createDirectories(root);
            }

            // Upload the image
            Files.copy(file.getInputStream(), this.root.resolve(newImageName));
        } catch (Exception e) {
            throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
        }

        // Set the new image name in the DTO
        projetDto.setLogo(newImageName);

        // Map DTO to entity
        Projet projet = modelMapper.map(projetDto, Projet.class);

        // Fetch and set the client
        Client client = clientRepository.findById(projetDto.getClientId())
                .orElseThrow(() -> new ResourceNotFoundException("Client not found with id " + projetDto.getClientId()));
        projet.setClient(client);

        // Save the project
        projet = projetRepository.save(projet);

        // Return the mapped DTO
        return modelMapper.map(projet, ProjetDto.class);
    }

     
     
 	// rundom string to be used to the image name
 	protected static String getSaltString() {
 		String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
 		StringBuilder salt = new StringBuilder();
 		Random rnd = new Random();
 		while (salt.length() < 18) { // length of the random string.
 			int index = (int) (rnd.nextFloat() * SALTCHARS.length());
 			salt.append(SALTCHARS.charAt(index));
 		}
 		String saltStr = salt.toString();
 		return saltStr;

 	}
    
    
    
    
    
    
    
    

}
