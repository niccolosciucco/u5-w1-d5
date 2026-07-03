package niccolosciucco.u5_w1_d5.runners;

import niccolosciucco.u5_w1_d5.entities.Utente;
import niccolosciucco.u5_w1_d5.services.UtenteService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Order(1)
public class UtenteRunner implements CommandLineRunner {
    private final UtenteService utenteService;

    public UtenteRunner(UtenteService utenteService) {
        this.utenteService = utenteService;
    }

    @Override
    public void run(String... args) throws Exception {
        List.of(
                new Utente("mrossi", "Mario Rossi", "mario.rossi@email.com"),
                new Utente("lverdi", "Luca Verdi", "luca.verdi@email.com"),
                new Utente("gbianchi", "Giulia Bianchi", "giulia.bianchi@email.com"),
                new Utente("aneri", "Anna Neri", "anna.neri@email.com"),
                new Utente("aferrari", "Alessandro Ferrari", "alessandro.ferrari@email.com"),
                new Utente("esposito90", "Ciro Esposito", "ciro.esposito@email.com"),
                new Utente("sbruni", "Sara Bruni", "sara.bruni@email.com"),
                new Utente("gcosta", "Giovanni Costa", "giovanni.costa@email.com"),
                new Utente("erizzo", "Elena Rizzo", "elena.rizzo@email.com"),
                new Utente("dmarini", "Davide Marini", "davide.marini@email.com")
        ).forEach(this.utenteService::saveNewUtente);
    }
}
