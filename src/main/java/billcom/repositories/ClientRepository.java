package billcom.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import billcom.entities.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
	

}
