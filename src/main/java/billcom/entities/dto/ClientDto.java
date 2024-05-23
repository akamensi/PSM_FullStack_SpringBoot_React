package billcom.entities.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class ClientDto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@NotBlank
	@Size(max = 20)
    private String nom;
	
	@NotBlank
	@Size(max = 20)
    private String tel;
	
	@NotBlank
	@Size(max = 20)
    private String email;
	
	@NotBlank
	@Size(max = 20)
    private String adresse;

    public ClientDto() {}
    
    
    public ClientDto(Long id, String nom, String tel, String email, String adresse) {
		super();
		this.nom = nom;
		this.tel = tel;
		this.email = email;
		this.adresse = adresse;
	}


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

}
