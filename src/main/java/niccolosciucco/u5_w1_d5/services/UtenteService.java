package niccolosciucco.u5_w1_d5.services;

import niccolosciucco.u5_w1_d5.entities.Utente;
import niccolosciucco.u5_w1_d5.exceptions.EmptyAttribute;
import niccolosciucco.u5_w1_d5.exceptions.NotFoundException;
import niccolosciucco.u5_w1_d5.repositories.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UtenteService {
    private final UtenteRepository utenteRepository;

    @Autowired
    public UtenteService(UtenteRepository utenteRepository) {
        this.utenteRepository = utenteRepository;
    }

    public void saveNewUtente(Utente utente) {
        if (utente == null) {
            throw new NotFoundException("L'utente è nullo");
        }

        if (utente.getUsername().isEmpty()) {
            throw new EmptyAttribute("L'username dell'utente è vuoto");
        }
        if (utente.getNomeCompleto().isEmpty()) {
            throw new EmptyAttribute("Il nome dell'utente è vuoto");
        }
        if (utente.getEmail().isEmpty()) {
            throw new EmptyAttribute("L'email dell'utente è vuota");
        }

        boolean esiste = utenteRepository.existsByUsername(utente.getUsername());
        if (esiste) {
            System.out.println("L'utente con username '" + utente.getUsername() + "' già presente nel database");
            return;
        }

        this.utenteRepository.save(utente);
        System.out.println("Utente con username '" + utente.getUsername() + "' salvato con successo!");
    }

    public Utente findById(UUID id) {
        return this.utenteRepository.findById(id).orElseThrow(() -> new NotFoundException("Utente con ID " + id + " non trovato"));
    }

    public List<Utente> findAll() {
        List<Utente> utenti = this.utenteRepository.findAll();
        if (utenti.isEmpty()) {
            throw new NotFoundException("Non sono presenti utenti nel database.");
        }
        return utenti;
    }
}
