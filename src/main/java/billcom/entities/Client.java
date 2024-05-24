package billcom.entities;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "clients")
public class Client {

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

    @OneToMany(mappedBy = "client")
    @JsonBackReference
    private Set<Projet> projets;
    
    public Client() {}
    
	public Client(@NotBlank @Size(max = 20) String nom, @NotBlank @Size(max = 20) String tel,
			@NotBlank @Size(max = 20) String email, @NotBlank @Size(max = 20) String adresse) {
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
	
	public Set<Projet> getProjets() {
		return projets;
	}

	public void setProjets(Set<Projet> projets) {
		this.projets = projets;
	}
	
	

}
