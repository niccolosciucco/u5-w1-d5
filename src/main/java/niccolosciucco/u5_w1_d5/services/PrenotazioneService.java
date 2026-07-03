package niccolosciucco.u5_w1_d5.services;

import niccolosciucco.u5_w1_d5.entities.Prenotazione;
import niccolosciucco.u5_w1_d5.exceptions.EmptyAttribute;
import niccolosciucco.u5_w1_d5.exceptions.NotFoundException;
import niccolosciucco.u5_w1_d5.exceptions.NotValidAttribute;
import niccolosciucco.u5_w1_d5.repositories.PrenotazioneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PrenotazioneService {
    private final PrenotazioneRepository prenotazioneRepository;

    @Autowired
    public PrenotazioneService(PrenotazioneRepository prenotazioneRepository) {
        this.prenotazioneRepository = prenotazioneRepository;
    }

    public void saveNewPrenotazione(Prenotazione prenotazione) {
        if (prenotazione == null) {
            throw new NotFoundException("La prenotazione è nulla");
        }

        if (prenotazione.getData() == null) {
            throw new EmptyAttribute("La data della prenotazione è vuota");
        }
        if (prenotazione.getUtente() == null) {
            throw new EmptyAttribute("L'utente della prenotazione è vuoto");
        }
        if (prenotazione.getPostazione() == null) {
            throw new NotValidAttribute("La postazione della prenotazione è vuota");
        }

        boolean esiste = prenotazioneRepository.existsByDataAndUtenteAndPostazione(prenotazione.getData(), prenotazione.getUtente(), prenotazione.getPostazione());
        if (esiste) {
            System.out.println("Prenotazione con data " + prenotazione.getData() + " per utente " + prenotazione.getUtente() + " già presente nel database");
            return;
        }

        boolean postazioneOccupata = prenotazioneRepository.existsByPostazioneAndData(prenotazione.getPostazione(), prenotazione.getData());
        if (postazioneOccupata) {
            System.out.println("Postazione già occupata per il giorno " + prenotazione.getData());
            return;
        }

        boolean utenteHaPrenotato = prenotazioneRepository.existsByUtenteAndData(prenotazione.getUtente(), prenotazione.getData());
        if (utenteHaPrenotato) {
            System.out.println("L'utente " + prenotazione.getUtente().getUsername() + " ha già una prenotazione per il giorno " + prenotazione.getData());
            return;
        }

        this.prenotazioneRepository.save(prenotazione);
        System.out.println("Prenotazione con data " + prenotazione.getData() + " per utente " + prenotazione.getUtente() + " salvata con successo");
    }

    public Prenotazione findById(UUID id) {
        return this.prenotazioneRepository.findById(id).orElseThrow(() -> new NotFoundException("Prenotazione con ID " + id + " non trovata"));
    }
}
