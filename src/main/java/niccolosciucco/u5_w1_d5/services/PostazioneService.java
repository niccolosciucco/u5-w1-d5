package niccolosciucco.u5_w1_d5.services;

import niccolosciucco.u5_w1_d5.entities.Postazione;
import niccolosciucco.u5_w1_d5.enums.TipoPostazione;
import niccolosciucco.u5_w1_d5.exceptions.EmptyAttribute;
import niccolosciucco.u5_w1_d5.exceptions.NotFoundException;
import niccolosciucco.u5_w1_d5.exceptions.NotValidAttribute;
import niccolosciucco.u5_w1_d5.repositories.PostazioneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PostazioneService {
    private final PostazioneRepository postazioneRepository;

    @Autowired
    public PostazioneService(PostazioneRepository postazioneRepository) {
        this.postazioneRepository = postazioneRepository;
    }

    public void saveNewPostazione(Postazione postazione) {
        if (postazione == null) {
            throw new NotFoundException("La postazione è nulla");
        }

        if (postazione.getDescrizione().isEmpty()) {
            throw new EmptyAttribute("La descrizione della postazione è vuota");
        }
        if (postazione.getTipo() == null) {
            throw new EmptyAttribute("Il tipo della postazione è vuoto");
        }
        if (postazione.getMaxOccupanti() <= 0) {
            throw new NotValidAttribute("Numero di occupanti della postazione non valido");
        }

        boolean esiste = postazioneRepository.existsByDescrizioneAndTipo(postazione.getDescrizione(), postazione.getTipo());
        if (esiste) {
            System.out.println("Postazione '" + postazione.getDescrizione() + "' già presente nel database");
            return;
        }

        this.postazioneRepository.save(postazione);
        System.out.println("Postazione '" + postazione.getDescrizione() + "' salvata con successo!");
    }

    public Postazione findById(UUID id) {
        return this.postazioneRepository.findById(id).orElseThrow(() -> new NotFoundException("Postazione con ID " + id + " non trovata"));
    }

    public List<Postazione> findAll() {
        List<Postazione> postazioni = this.postazioneRepository.findAll();
        if (postazioni.isEmpty()) {
            throw new NotFoundException("Non sono presenti postazioni nel database.");
        }
        return postazioni;
    }

    public List<Postazione> findByTipoAndCitta(TipoPostazione tipo, String citta) {
        if (tipo == null || citta.isEmpty()) {
            throw new EmptyAttribute("Il tipo di postazione e la città non possono essere nulli");
        }

        List<Postazione> listaTrovata = this.postazioneRepository.findByTipoAndEdificioCitta(tipo, citta);

        if (listaTrovata.isEmpty()) {
            System.out.println("Nessuna postazione trovata per il tipo " + tipo + " a " + citta);
        } else {
            System.out.println("Trovate " + listaTrovata.size() + " postazioni di tipo: " + tipo + " a " + citta);
            for (Postazione p : listaTrovata) {
                System.out.println("- " + p.getDescrizione() + " - Presso: " + p.getEdificio().getNome());
            }
        }

        return listaTrovata;
    }
}
