package billcom.entities;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonBackReference;

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
	
	@NotNull
    private Double budget;
	
	@NotNull
    private Integer duree;
	
	@NotBlank
	@Size(max = 20)
    private String description;
	
	@NotBlank
	private String logo;
	
	@ManyToOne
    @JoinColumn(name = "client_id")
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonBackReference
    private Client client;

	public Projet() {}


	
	public Projet(@NotBlank @Size(max = 20) String libelle, @NotNull Double budget, @NotNull Integer duree,
			@NotBlank @Size(max = 20) String description, @NotBlank String logo) {
		super();
		this.libelle = libelle;
		this.budget = budget;
		this.duree = duree;
		this.description = description;
		this.logo = logo;
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



	public String getLogo() {
		return logo;
	}



	public void setLogo(String logo) {
		this.logo = logo;
	}
	

	

	
	

}
