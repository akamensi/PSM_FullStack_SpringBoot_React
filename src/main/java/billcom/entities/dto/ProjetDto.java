package billcom.entities.dto;

import jakarta.validation.constraints.*;

public class ProjetDto {
	

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
    
    @NotNull
    private Long clientId;
    
    public ProjetDto() {}


	public ProjetDto(@NotBlank @Size(max = 20) String libelle, @NotNull Double budget, @NotNull Integer duree,
			@NotBlank @Size(max = 20) String description, @NotBlank String logo, @NotNull Long clientId) {
		super();
		this.libelle = libelle;
		this.budget = budget;
		this.duree = duree;
		this.description = description;
		this.logo = logo;
		this.clientId = clientId;
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
 

	public String getLogo() {
		return logo;
	}


	public void setLogo(String logo) {
		this.logo = logo;
	}


	public Long getClientId() {
		return clientId;
	}



	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}
	
	
    
    

}
