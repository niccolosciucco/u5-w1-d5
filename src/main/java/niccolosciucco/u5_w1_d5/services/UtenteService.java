package niccolosciucco.u5_w1_d5.services;

import niccolosciucco.u5_w1_d5.entities.Utente;
import niccolosciucco.u5_w1_d5.exceptions.EmptyAttribute;
import niccolosciucco.u5_w1_d5.exceptions.NotFoundException;
import niccolosciucco.u5_w1_d5.repositories.UtenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
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

    public Optional<Utente> findById(UUID id) {
        if (id == null) {
            throw new EmptyAttribute("L'ID dell'utente è nullo");
        }
        return this.utenteRepository.findById(id);
    }
}
