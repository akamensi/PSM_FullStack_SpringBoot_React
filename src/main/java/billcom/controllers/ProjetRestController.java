package billcom.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import billcom.entities.Projet;
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
	
	
    @PostMapping("/add")
    public ResponseEntity<Projet> addProjet(
            @RequestParam(name = "imageFile") MultipartFile file,
            @RequestParam String libelle,
            @RequestParam Double budget,
            @RequestParam Integer duree,
            @RequestParam String description,
            @RequestParam Long clientId) {
        try {
            ProjetDto projetDto = new ProjetDto(libelle, budget, duree, description, "", clientId);
            Projet projet = projetService.createProjet(file, projetDto);
            return ResponseEntity.ok(projet);
        } catch (IOException e) {
            return ResponseEntity.status(500).body(null);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
    
    
    @PutMapping("/{id}")
    public ResponseEntity<Projet> editProjet(
            @PathVariable("id") Long projetId,
            @RequestParam(name = "imageFile", required = false) MultipartFile file,
            @RequestParam String libelle,
            @RequestParam Double budget,
            @RequestParam Integer duree,
            @RequestParam String description,
            @RequestParam Long clientId) {
        try {
            ProjetDto projetDto = new ProjetDto(libelle, budget, duree, description, "", clientId);
            Projet updatedProjet = projetService.editProjet(projetId, projetDto, file);
            return ResponseEntity.ok(updatedProjet);
        } catch (IOException e) {
            return ResponseEntity.status(500).body(null);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProjet(@PathVariable Long id) {
        projetService.deleteProjet(id);
        return ResponseEntity.noContent().build();
    }
	
	/*
	 * @PostMapping("/add") public ResponseEntity<Projet> addProjet(
	 * 
	 * @RequestParam(name = "imageFile") MultipartFile file,
	 * 
	 * @RequestParam String libelle,
	 * 
	 * @RequestParam Double budget,
	 * 
	 * @RequestParam Integer duree,
	 * 
	 * @RequestParam String description,
	 * 
	 * @RequestParam Long clientId) { try { Projet projet =
	 * projetService.createProjet(file, libelle, budget, duree, description,
	 * clientId); return ResponseEntity.ok(projet); } catch (IOException e) { return
	 * ResponseEntity.status(500).body(null); } catch (IllegalArgumentException e) {
	 * return ResponseEntity.badRequest().body(null); } }
	 */

	/*
	 * @PostMapping(consumes = "multipart/form-data", value = "/add") public
	 * ResponseEntity<ProjetDto> createProjet(@RequestPart("projet") @Valid
	 * ProjetDto projetDto,
	 * 
	 * @RequestPart("file") MultipartFile file) throws IOException { ProjetDto
	 * createdProjet = projetService.createProjet(projetDto, file); return new
	 * ResponseEntity<>(createdProjet, HttpStatus.CREATED); }
	 */

}
