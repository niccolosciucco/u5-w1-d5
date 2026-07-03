package niccolosciucco.u5_w1_d5.repositories;

import niccolosciucco.u5_w1_d5.entities.Edificio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EdificioRepository extends JpaRepository<Edificio, UUID> {
    boolean existsByNomeAndCittaAndIndirizzo(String nome, String citta, String indirizzo);
}
