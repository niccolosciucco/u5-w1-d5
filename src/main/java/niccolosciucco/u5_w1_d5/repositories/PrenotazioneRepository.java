package niccolosciucco.u5_w1_d5.repositories;

import niccolosciucco.u5_w1_d5.entities.Postazione;
import niccolosciucco.u5_w1_d5.entities.Prenotazione;
import niccolosciucco.u5_w1_d5.entities.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.UUID;

@Repository
public interface PrenotazioneRepository extends JpaRepository<Prenotazione, UUID> {
    boolean existsByDataAndUtenteAndPostazione(LocalDate data, Utente utente, Postazione postazione);

    boolean existsByPostazioneAndData(Postazione postazione, LocalDate data);

    boolean existsByUtenteAndData(Utente utente, LocalDate data);
}
