package niccolosciucco.u5_w1_d5.runners;

import niccolosciucco.u5_w1_d5.entities.Edificio;
import niccolosciucco.u5_w1_d5.entities.Postazione;
import niccolosciucco.u5_w1_d5.enums.TipoPostazione;
import niccolosciucco.u5_w1_d5.exceptions.NotEnoughtRecords;
import niccolosciucco.u5_w1_d5.services.EdificioService;
import niccolosciucco.u5_w1_d5.services.PostazioneService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Order(3)
public class PostazioneRunner implements CommandLineRunner {
    private final PostazioneService postazioneService;
    private final EdificioService edificioService;

    public PostazioneRunner(PostazioneService postazioneService, EdificioService edificioService) {
        this.postazioneService = postazioneService;
        this.edificioService = edificioService;
    }

    @Override
    public void run(String... args) throws Exception {
        List<Edificio> edifici = edificioService.findAll();
        if (edifici.size() < 10) {
            throw new NotEnoughtRecords("Nel database ci sono meno di 10 edifici.");
        }

        List.of(
                new Postazione("Scrivania privata Ufficio A", TipoPostazione.PRIVATO, 1, edifici.get(0)),
                new Postazione("Sala Riunioni Alpha", TipoPostazione.SALA_RIUNIONI, 12, edifici.get(1)),
                new Postazione("Open Space Desk 4", TipoPostazione.OPEN_SPACE, 1, edifici.get(2)),
                new Postazione("Sala Riunioni Beta", TipoPostazione.SALA_RIUNIONI, 8, edifici.get(3)),
                new Postazione("Ufficio Singolo Direzione", TipoPostazione.PRIVATO, 1, edifici.get(4)),
                new Postazione("Open Space Desk 15", TipoPostazione.OPEN_SPACE, 1, edifici.get(5)),
                new Postazione("Sala Consiglio Grande", TipoPostazione.SALA_RIUNIONI, 20, edifici.get(6)),
                new Postazione("Scrivania Condivisa Finestra", TipoPostazione.OPEN_SPACE, 1, edifici.get(7)),
                new Postazione("Ufficio Sviluppatori Privato", TipoPostazione.PRIVATO, 2, edifici.get(8)),
                new Postazione("Sala Briefing Veloce", TipoPostazione.SALA_RIUNIONI, 6, edifici.get(9))
        ).forEach(this.postazioneService::saveNewPostazione);
    }
}
