package niccolosciucco.u5_w1_d5.repositories;

import niccolosciucco.u5_w1_d5.entities.Postazione;
import niccolosciucco.u5_w1_d5.enums.TipoPostazione;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PostazioneRepository extends JpaRepository<Postazione, UUID> {
    boolean existsByDescrizioneAndTipo(String descrizione, TipoPostazione tipo);

    List<Postazione> findByTipoAndEdificioCitta(TipoPostazione tipo, String citta);
}
