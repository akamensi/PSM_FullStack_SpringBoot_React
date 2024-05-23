package billcom.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import billcom.entities.dto.ProjetDto;
import billcom.services.ProjetService;
import jakarta.validation.Valid;

@CrossOrigin("*")
@RestController
@RequestMapping("/restProjet")
public class ProjetRestController {

	private ProjetService projetService;

	@Autowired
	public ProjetRestController(ProjetService projetService) {
		super();
		this.projetService = projetService;
	}

	@GetMapping("/list")
	public ResponseEntity<List<ProjetDto>> getAllProjets() {
		List<ProjetDto> projets = projetService.getAllProjets();
		return ResponseEntity.ok(projets);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ProjetDto> getProjetById(@PathVariable Long id) {
		ProjetDto projet = projetService.getProjetById(id);
		if (projet != null) {
			return ResponseEntity.ok(projet);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

    @PostMapping(consumes = "multipart/form-data",value = "/add")
    public ResponseEntity<ProjetDto> createProjet(@RequestPart("projet") @Valid ProjetDto projetDto,
                                                  @RequestPart("file") MultipartFile file) throws IOException {
        ProjetDto createdProjet = projetService.createProjet(projetDto, file);
        return new ResponseEntity<>(createdProjet, HttpStatus.CREATED);
    }

}
