package billcom.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import billcom.entities.Projet;

@Repository
public interface ProjetRepository extends JpaRepository<Projet, Long> {

}
