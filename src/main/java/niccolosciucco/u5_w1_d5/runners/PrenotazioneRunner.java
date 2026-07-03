package niccolosciucco.u5_w1_d5.runners;

import niccolosciucco.u5_w1_d5.entities.Postazione;
import niccolosciucco.u5_w1_d5.entities.Prenotazione;
import niccolosciucco.u5_w1_d5.entities.Utente;
import niccolosciucco.u5_w1_d5.exceptions.NotEnoughtRecords;
import niccolosciucco.u5_w1_d5.services.PostazioneService;
import niccolosciucco.u5_w1_d5.services.PrenotazioneService;
import niccolosciucco.u5_w1_d5.services.UtenteService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
@Order(4)
public class PrenotazioneRunner implements CommandLineRunner {
    private final PrenotazioneService prenotazioneService;
    private final UtenteService utenteService;
    private final PostazioneService postazioneService;

    public PrenotazioneRunner(PrenotazioneService prenotazioneService, UtenteService utenteService, PostazioneService postazioneService) {
        this.prenotazioneService = prenotazioneService;
        this.utenteService = utenteService;
        this.postazioneService = postazioneService;
    }

    @Override
    public void run(String... args) throws Exception {
        List<Utente> utenti = utenteService.findAll();
        if (utenti.size() < 10) {
            throw new NotEnoughtRecords("Nel database ci sono meno di 10 utenti.");
        }

        List<Postazione> postazioni = postazioneService.findAll();
        if (postazioni.size() < 10) {
            throw new NotEnoughtRecords("Nel database ci sono meno di 10 postazioni.");
        }

        LocalDate domani = LocalDate.now().plusDays(1);
        LocalDate dopodomani = LocalDate.now().plusDays(2);
        LocalDate traTreGiorni = LocalDate.now().plusDays(3);

        Utente u1 = utenti.get(0);
        Utente u2 = utenti.get(1);
        Postazione salaRiunioni = postazioni.get(0);
        Postazione scrivania = postazioni.get(1);


        //region TEST
        //INSERIMENTO DI PRENOTAZIONI ALLA DATA DI DOMANI - non deve dare problemi
        for (int i = 0; i < 10; i++) {
            Utente utenteCorrente = utenti.get(i);
            Postazione postazioneCorrente = postazioni.get(i);
            Prenotazione nuovaPrenotazione = new Prenotazione(domani, utenteCorrente, postazioneCorrente);
            this.prenotazioneService.saveNewPrenotazione(nuovaPrenotazione);
        }

        //INSERIMENTO PRENOTAZIONE DI UNO STESSO UTENTE SU DATA DIVERSE - non deve dare problemi
        List.of(
                new Prenotazione(dopodomani, u1, salaRiunioni),
                new Prenotazione(traTreGiorni, u1, scrivania)
        ).forEach(this.prenotazioneService::saveNewPrenotazione);

        //INSERIMENTO PRENOTAZIONE DI UNA POSTAZIONE GIA' PRENOTATA MA USANDO DATE DIVERSE - non deve dare problemi
        Prenotazione prenotazioneCorretta = new Prenotazione(traTreGiorni, u2, salaRiunioni);
        this.prenotazioneService.saveNewPrenotazione(prenotazioneCorretta);

        //INSERIMENTO DI PRENOTAZIONI CHE DEVONO DARE ERRORI - il tipo di errore viene descritto nel terminale
        List.of(
                new Prenotazione(domani, u1, scrivania),
                new Prenotazione(domani, u2, salaRiunioni),
                new Prenotazione(dopodomani, u1, salaRiunioni)
        ).forEach(this.prenotazioneService::saveNewPrenotazione);

        //endregion
    }
}