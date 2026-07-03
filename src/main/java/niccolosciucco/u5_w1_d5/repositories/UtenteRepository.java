package niccolosciucco.u5_w1_d5.repositories;

import niccolosciucco.u5_w1_d5.entities.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UtenteRepository extends JpaRepository<Utente, UUID> {
    boolean existsByUsername(String username);
}
