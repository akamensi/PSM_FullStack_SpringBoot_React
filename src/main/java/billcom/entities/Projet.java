package billcom.entities;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "projets")
public class Projet {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@NotBlank
	@Size(max = 20)
    private String libelle;
	
	@NotBlank
	@Size(max = 20)
    private Double budget;
	
	@NotBlank
	@Size(max = 20)
    private Integer duree;
	
	@NotBlank
	@Size(max = 20)
    private String description;
	
	@ManyToOne
    @JoinColumn(name = "client_id")
	@OnDelete(action = OnDeleteAction.CASCADE)
    private Client client;

	public Projet(@NotBlank @Size(max = 20) String libelle, @NotBlank @Size(max = 20) Double budget,
			@NotBlank @Size(max = 20) Integer duree, @NotBlank @Size(max = 20) String description) {
		super();
		this.libelle = libelle;
		this.budget = budget;
		this.duree = duree;
		this.description = description;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public Double getBudget() {
		return budget;
	}

	public void setBudget(Double budget) {
		this.budget = budget;
	}

	public Integer getDuree() {
		return duree;
	}

	public void setDuree(Integer duree) {
		this.duree = duree;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}
	

	
	

}
