package niccolosciucco.u5_w1_d5;

import niccolosciucco.u5_w1_d5.entities.Postazione;
import niccolosciucco.u5_w1_d5.entities.Utente;
import niccolosciucco.u5_w1_d5.enums.TipoPostazione;
import niccolosciucco.u5_w1_d5.services.PostazioneService;
import niccolosciucco.u5_w1_d5.services.PrenotazioneService;
import niccolosciucco.u5_w1_d5.services.UtenteService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class U5W1D5ApplicationTests {

    @Autowired
    private UtenteService utenteService;
    private PrenotazioneService prenotazioneService;
    private PostazioneService postazioneService;

    @Test
    void testVerificaPresenzaUtenti() {
        List<Utente> tuttiGliUtenti = utenteService.findAll();
        assertNotNull(tuttiGliUtenti);
        assertTrue(tuttiGliUtenti.size() >= 10, "Dovrebbero esserci almeno 10 utenti nel DB");
    }

    @Test
    void testRicercaCittaInesistenteRestituisceVuoto() {
        List<Postazione> risultato = postazioneService.findByTipoAndCitta(TipoPostazione.SALA_RIUNIONI, "kjsgfksh");

        assertNotNull(risultato, "La lista restituita non deve essere null");
        assertTrue(risultato.isEmpty(), "La lista dovrebbe essere vuota perchè la città non esiste");
    }

    @Test
    void testSalvataggioPrenotazioneNull() {
        assertThrows(Exception.class, () -> {
            prenotazioneService.saveNewPrenotazione(null);
        }, "Il salvataggio di una prenotazione null deve fallire");
    }
}