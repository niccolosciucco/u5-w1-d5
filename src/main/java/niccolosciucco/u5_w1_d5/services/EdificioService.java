package niccolosciucco.u5_w1_d5.services;

import niccolosciucco.u5_w1_d5.entities.Edificio;
import niccolosciucco.u5_w1_d5.exceptions.EmptyAttribute;
import niccolosciucco.u5_w1_d5.exceptions.NotFoundException;
import niccolosciucco.u5_w1_d5.repositories.EdificioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EdificioService {
    private final EdificioRepository edificioRepository;

    @Autowired
    public EdificioService(EdificioRepository edificioRepository) {
        this.edificioRepository = edificioRepository;
    }

    public void saveNewEdificio(Edificio edificio) {
        if (edificio == null) {
            throw new NotFoundException("L'edificio è nullo");
        }

        if (edificio.getNome().isEmpty()) {
            throw new EmptyAttribute("Il nome dell'edificio è vuoto");
        }
        if (edificio.getIndirizzo().isEmpty()) {
            throw new EmptyAttribute("L'indirizzo dell'edificio è vuoto");
        }
        if (edificio.getCitta().isEmpty()) {
            throw new EmptyAttribute("La città dell'edificio è vuota");
        }

        boolean esiste = edificioRepository.existsByNomeAndCittaAndIndirizzo(edificio.getNome(), edificio.getCitta(), edificio.getIndirizzo());
        if (esiste) {
            System.out.println("Edificio '" + edificio.getNome() + "' già presente nel database");
            return;
        }

        this.edificioRepository.save(edificio);
        System.out.println("Edificio '" + edificio.getNome() + "' salvato con successo!");
    }

    public Edificio findById(UUID id) {
        return this.edificioRepository.findById(id).orElseThrow(() -> new NotFoundException("Edificio con ID " + id + " non trovato"));
    }

    public List<Edificio> findAll() {
        List<Edificio> edifici = this.edificioRepository.findAll();
        if (edifici.isEmpty()) {
            throw new NotFoundException("Non sono presenti edifici nel database.");
        }
        return edifici;
    }
}
